package adeuxpas.back.service;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.UsersFavoriteAds;
import adeuxpas.back.entity.UsersFavoriteAdsKey;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.dto.mapper.AdMapper;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UsersFavoriteAdsRepository;
import adeuxpas.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

import adeuxpas.back.dto.AdCardResponseDTO;
import adeuxpas.back.enums.AccountStatus;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation class for the AdService interface.
 * <p>
 * Provides implementations for ad-related operations.
 * </p>
 * <p>
 * It interacts with the AdRepository to perform database operations related to
 * ads.
 * </p>
 */

@Service
public class AdServiceImpl implements AdService {
    private static final Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);
    private final List<AdStatus> acceptedAdStatuses = List.of(AdStatus.AVAILABLE);
    private final List<AccountStatus> acceptedAccountStatuses = List.of(AccountStatus.ACTIVE, AccountStatus.REPORTED);
    private BigDecimal maxPrice1 = null;
    private BigDecimal minPrice2 = null;
    private BigDecimal maxPrice2 = null;
    private BigDecimal minPrice3 = null;
    private BigDecimal maxPrice3 = null;
    private BigDecimal minPrice4 = null;
    private BigDecimal maxPrice4 = null;
    private BigDecimal minPrice5 = null;
    private BigDecimal maxPrice5 = null;
    private BigDecimal minPrice6 = BigDecimal.ZERO;
    private String category;
    private String subcategory = null;
    private String gender = null;

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final UsersFavoriteAdsRepository usersFavoriteAdsRepository;
    private final AdMapper adMapper;
    private final CloudinaryService cloudinaryService;

    public AdServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired AdRepository adRepository,
            @Autowired UsersFavoriteAdsRepository usersFavoriteAdsRepository,
            @Autowired AdMapper adMapper,
            @Autowired CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.usersFavoriteAdsRepository = usersFavoriteAdsRepository;
        this.adMapper = adMapper;
        this.cloudinaryService = cloudinaryService;
    }

    /**
     * Finds filtered ads and maps them to AdCardResponseDTOs.
     *
     * @param priceRangesFilter          The list of price range filters.
     * @param citiesAndPostalCodesFilter The list of city and postal code filters.
     * @param articleStatesFilter        The list of article state filters.
     * @param categoryFilter             The category filter.
     * @param loggedInUserId             The ID of the logged-in user.
     * @param pageNumber                 The page number for pagination.
     * @param pageSize                   The page size for pagination.
     * @return The page of AdCardResponseDTOs.
     */
    @Override
    public Page<AdCardResponseDTO> findFilteredAdCardResponseDTOs(List<String> priceRangesFilter,
            List<String> citiesAndPostalCodesFilter, List<String> articleStatesFilter, String categoryFilter,
            Long loggedInUserId, int pageNumber, int pageSize) {

        // check if the user exists, if not set loggedInUserId to null
        if (loggedInUserId != null) {
            Optional<User> optionalUser = userRepository.findById(loggedInUserId);
            if (optionalUser.isEmpty()) {
                loggedInUserId = null;
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // if no filter is checked, return all ads
        if (shouldReturnAllAds(priceRangesFilter, citiesAndPostalCodesFilter, articleStatesFilter, categoryFilter)) {
            return findAllAdCardResponseDTOs(loggedInUserId, pageable);
        }

        // extracting the postal codes
        List<String> postalCodes = citiesAndPostalCodesFilter.stream()
                .map(city -> city.substring(city.indexOf('(') + 1, city.indexOf(')')))
                .toList();

        // preparing the price ranges
        maxPrice1 = null;
        minPrice2 = null;
        maxPrice2 = null;
        minPrice3 = null;
        maxPrice3 = null;
        minPrice4 = null;
        maxPrice4 = null;
        minPrice5 = null;
        maxPrice5 = null;
        minPrice6 = BigDecimal.ZERO;
        assignPriceRangeParameters(priceRangesFilter);

        // extracting the category filter criteria
        subcategory = null;
        gender = null;
        extractAndAssignCategoryFilterCriteria(categoryFilter);

        // passing the formatted filtering criteria to the query and saving the result
        // to a page
        Page<Ad> filteredAds = this.adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes.isEmpty() ? null : postalCodes,
                articleStatesFilter.isEmpty() ? null : articleStatesFilter,
                maxPrice1, minPrice2, maxPrice2, minPrice3, maxPrice3,
                minPrice4, maxPrice4, minPrice5, maxPrice5, minPrice6,
                category, subcategory, gender, acceptedAdStatuses, acceptedAccountStatuses, loggedInUserId, pageable);
        return this.convertToPageOfAdCardResponseDTOs(pageable, filteredAds, loggedInUserId);
    }

    /**
     * Finds all ads and maps them to AdCardResponseDTOs.
     *
     * @param pageable       The pagination information.
     * @param loggedInUserId The ID of the logged-in user.
     * @return The page of AdCardResponseDTOs.
     */
    @Override
    public Page<AdCardResponseDTO> findAllAdCardResponseDTOs(Long loggedInUserId, Pageable pageable) {
        Page<Ad> myAds = this.adRepository.findByAcceptedStatusesOrderedByCreationDateDesc(acceptedAdStatuses,
                acceptedAccountStatuses, loggedInUserId, pageable);
        return this.convertToPageOfAdCardResponseDTOs(pageable, myAds, loggedInUserId);
    }

    /**
     * Converts a page of Ad entities to a page of AdCardResponseDTOs.
     *
     * @param pageable       The pagination information.
     * @param adsPage        The page of Ad entities.
     * @param loggedInUserId The current user's ID.
     * @return The page of AdCardResponseDTOs.
     */
    private Page<AdCardResponseDTO> convertToPageOfAdCardResponseDTOs(Pageable pageable, Page<Ad> adsPage,
            Long loggedInUserId) {

        Set<Long> favoriteAdIds = Collections.emptySet();

        // Retrieve favorite ad IDs if logged in user ID is provided
        if (loggedInUserId != null) {
            favoriteAdIds = usersFavoriteAdsRepository.findFavoriteAdIdsByUserId(loggedInUserId);
        }

        List<AdCardResponseDTO> mappedAdsList = new ArrayList<>();

        for (Ad ad : adsPage) {
            AdCardResponseDTO dto = this.adMapper.adToAdCardResponseDTO(ad);
            // If the set of favorite ad IDs is not empty and contains the current ad's ID,
            // set the 'favorite' flag to true in the DTO
            if (!favoriteAdIds.isEmpty() && favoriteAdIds.contains(ad.getId())) {
                dto.setFavorite(true);
            }
            mappedAdsList.add(dto);
        }

        return new PageImpl<>(mappedAdsList, pageable, adsPage.getTotalElements());
    }

    /**
     * Persists an Ad object in database.
     *
     * @param adPostRequestDTO coming from the front end application.
     * @return An AdPostResponseDTO.
     */
    @Override
    public AdPostResponseDTO postAd(AdPostRequestDTO adPostRequestDTO, List<MultipartFile> articlePictures) {
        Optional<User> optionalUser = userRepository.findById(adPostRequestDTO.getPublisherId());
        if (!optionalUser.isPresent()) {
            throw new EntityNotFoundException("Invalid credentials");
        }
        User publisher = optionalUser.get();
        List<String> articlePictureUrls = new ArrayList<>();
        if (articlePictures != null) {
            try {
                for (int index = 0; index < articlePictures.size(); index++) {
                    MultipartFile picture = articlePictures.get(index);
                    String publicId = adPostRequestDTO.getTitle() + "-" + index;
                    Map<String, Object> profilePictureObject = cloudinaryService.upload(publicId, picture);
                    String articlePictureUrl = (String) profilePictureObject.get("url");
                    articlePictureUrls.add(articlePictureUrl);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload article picture", e);
            }
        }
        adPostRequestDTO.setArticlePictures(articlePictureUrls);
        Ad newAd = adMapper.adPostRequestDTOToAd(adPostRequestDTO);
        newAd.setPublisher(publisher);
        newAd.setCreationDate(LocalDateTime.now());
        for (String url : articlePictureUrls) {
            ArticlePicture articlePicture = new ArticlePicture();
            articlePicture.setUrl(url);
            articlePicture.setAd(newAd);
            newAd.addArticlePicture(articlePicture);
        }
        Ad savedAd;
        try {
            savedAd = adRepository.save(newAd);
        } catch (Exception e) {
            logger.error("Error during saving Ad to repository: ", e);
            throw new RuntimeException("Failed to save Ad", e);
        }
        AdPostResponseDTO responseDTO;
        try {
            responseDTO = adMapper.adToAdPostResponseDTO(savedAd);
        } catch (Exception e) {
            logger.error("Error during mapping savedAd to AdPostResponseDTO: ", e);
            throw new RuntimeException("Failed to map Ad to ResponseDTO", e);
        }
        return responseDTO;
    }

    /**
     * Retrieves an ad information by its ID.
     *
     * @param adId           The concerned ad ID.
     * @param loggedInUserId The logged in User ID.
     * @return An AdPostResponseDTO.
     */
    @Override
    public AdPostResponseDTO findAdById(long adId, Long loggedInUserId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        Optional<User> optionalUser = userRepository.findById(loggedInUserId);

        if (optionalAd.isPresent()) {
            AdPostResponseDTO dto = adMapper.adToAdPostResponseDTO(optionalAd.get());
            if (optionalUser.isPresent()) {
                // Check if the ad is a favorite for the given user
                boolean isFavorite = usersFavoriteAdsRepository.existsByUserIdAndAdId(loggedInUserId, adId);
                dto.setFavorite(isFavorite);
            }

            return dto;
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Finds ads and maps them into an AdCardResponseDTO in order to display
     * them on an ad page content.
     * 
     * @param publisherId    The ad's publisher's ID.
     * @param pageNumber     The page number for pagination.
     * @param pageSize       The page size for pagination.
     * @param pageable       The pagination information.
     * @param loggedInUserId The current user's ID.
     * @param adId           The ID of ad to exclude from the result list.
     * 
     * @return The page of AdCardResponseDTO.
     */
    @Override
    public Page<AdCardResponseDTO> findPageOfUserAdsList(long publisherId, int pageNumber, int pageSize,
            Long loggedInUserId, Long adId) {
        Optional<User> optionalUser = userRepository.findById(publisherId);
        if (optionalUser.isPresent()) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Ad> adsPage = adRepository.findAvailableAdsByPublisherId(publisherId, pageable, adId);
            Page<AdCardResponseDTO> mappedAdsPage = this.convertToPageOfAdCardResponseDTOs(pageable, adsPage);
            if (loggedInUserId != null) {
                Set<Long> favoriteAdsIds = usersFavoriteAdsRepository.findUserPublisherFavoriteAdsIds(loggedInUserId,
                        publisherId);
                for (AdCardResponseDTO dto : mappedAdsPage) {
                    if (favoriteAdsIds.contains(dto.getId())) {
                        dto.setFavorite(true);
                    }
                }
            }
            return mappedAdsPage;
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Finds ads that are sorted by their status and maps them into
     * AdCardResponseDTOs in order to display them on a user's ad tab.
     * 
     * @param publisherId The ad's publisher's ID.
     * @param pageNumber  The page number for pagination.
     * @param pageSize    The page size for pagination.
     * 
     * @return The page of AdCardResponseDTO.
     */
    @Override
    public Page<AdCardResponseDTO> getUserAdsTab(long publisherId, int pageNumber, int pageSize) {
        Optional<User> optionalUser = userRepository.findById(publisherId);
        if (optionalUser.isPresent()) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Ad> adsPage = adRepository.findSortedAdsByPublisherIdOrderByCreationDateDesc(publisherId, pageable);
            return this.convertToPageOfAdCardResponseDTOs(pageable, adsPage);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Finds a list containing the last four ads sharing the same category as the
     * current ad's.
     * 
     * @param category    The current ad's category.
     * @param publisherId The ad's publisher's ID.
     * @param userId      The current user's ID.
     * @param pageNumber  The page number for pagination.
     * @param pageSize    The page size for pagination.
     * 
     * @return a list of similar ads sharing the same category.
     */
    @Override
    public Page<AdCardResponseDTO> findSimilarAds(String category, Long publisherId, Long userId, int pageNumber,
            int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Ad> adsPage = this.adRepository.findAdsByCategoryOrderByCreationDateDesc(category, publisherId, userId,
                pageable);
        Page<AdCardResponseDTO> mappedAdsPage = this.convertToPageOfAdCardResponseDTOs(pageable, adsPage);
        if (userId != null) {
            for (AdCardResponseDTO dto : mappedAdsPage) {
                Set<Long> favoriteAdsIds = usersFavoriteAdsRepository.findUserPublisherFavoriteAdsIds(userId,
                        dto.getPublisherId());
                if (favoriteAdsIds.contains(dto.getId())) {
                    dto.setFavorite(true);
                }
            }
        }
        return mappedAdsPage;
    }

    /**
     * Converts a page of Ad into a page of AdPostResponseDTO.
     *
     * @param pageable The pagination information.
     * @param adsPage  The page of Ad entities.
     * @return The page of AdCardResponseDTOs.
     */
    private Page<AdCardResponseDTO> convertToPageOfAdCardResponseDTOs(Pageable pageable, Page<Ad> adsPage) {
        List<AdCardResponseDTO> mappedAdsList = adsPage.stream()
                .map(adMapper::adToAdCardResponseDTO)
                .toList();
        return new PageImpl<>(mappedAdsList, pageable, adsPage.getTotalElements());
    }

    /**
     * Converts a page of UsersFavoriteAds entities to a page of AdCardResponseDTOs.
     *
     * @param pageable     The pagination information.
     * @param favoritesAds The page of UsersFavoriteAds entities.
     * @return The page of AdCardResponseDTOs.
     */
    private Page<AdCardResponseDTO> convertFavoritesToPageOfAdCardResponseDTOs(Pageable pageable,
            Page<UsersFavoriteAds> favoritesAds) {
        List<AdCardResponseDTO> mappedFavoritesAds = favoritesAds.stream()
                .map(favoriteAd -> {
                    AdCardResponseDTO dto = this.adMapper.adToAdCardResponseDTO(favoriteAd.getAd());
                    dto.setFavorite(true);
                    return dto;
                }).toList();
        return new PageImpl<>(mappedFavoritesAds, pageable, favoritesAds.getTotalElements());
    }

    /**
     * Retrieves a user's favorites ads list.
     *
     * @param userId     The ID of the user.
     * @param pageNumber The page number for pagination.
     * @param pageSize   The page size for pagination.
     * @return The list of favorites ads.
     */
    @Override
    public Page<AdCardResponseDTO> findFavoriteAdsByUserId(long userId, int pageNumber, int pageSize) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<UsersFavoriteAds> favoritesAds = this.usersFavoriteAdsRepository.findByUserOrderByAddedAtDesc(user,
                    pageable);
            return this.convertFavoritesToPageOfAdCardResponseDTOs(pageable, favoritesAds);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Updates the ad favorite status added by a user.
     *
     * @param adId       The ID of the ad.
     * @param userId     The ID of the user.
     * @param isFavorite The new status of the ad favorite.
     */
    @Override
    public void updateAdFavoriteStatus(long adId, long userId, boolean isFavorite) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent() && optionalAd.isPresent()) {
            UsersFavoriteAdsKey favoriteKey = new UsersFavoriteAdsKey(userId, adId);
            Optional<UsersFavoriteAds> optionalFavorite = usersFavoriteAdsRepository.findById(favoriteKey);
            if (!optionalFavorite.isPresent()) {
                UsersFavoriteAds newFavorite = new UsersFavoriteAds(favoriteKey, optionalUser.get(), optionalAd.get(),
                        LocalDateTime.now());
                usersFavoriteAdsRepository.save(newFavorite);
            } else {
                usersFavoriteAdsRepository.delete(optionalFavorite.get());
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public long checkFavoriteCount(long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            return usersFavoriteAdsRepository.checksFavoriteCount(adId);
        } else {
            throw new EntityNotFoundException();
        }
    }

    // ============ parameter extraction and formatting helper methods ============
    private boolean shouldReturnAllAds(List<String> priceRangesFilter, List<String> citiesAndPostalCodesFilter,
            List<String> articleStatesFilter, String categoryFilter) {
        return priceRangesFilter.isEmpty() && citiesAndPostalCodesFilter.isEmpty() &&
                articleStatesFilter.isEmpty() && categoryFilter.equals("Catégorie");
    }

    private void extractAndAssignCategoryFilterCriteria(String categoryFilter) {
        if (categoryFilter.contains("▸")) {
            category = categoryFilter.substring(0, categoryFilter.indexOf(" ▸"));
            if (categoryFilter.indexOf("▸") != categoryFilter.lastIndexOf("▸")) {
                subcategory = categoryFilter
                        .substring(categoryFilter.indexOf("▸ ") + 1, categoryFilter.lastIndexOf(" ▸")).trim();
                gender = categoryFilter.substring(categoryFilter.lastIndexOf("▸ ") + 1).trim();
            } else
                subcategory = categoryFilter.substring(categoryFilter.indexOf("▸ ") + 1).trim();
        } else
            category = categoryFilter.equals("Catégorie") ? null : categoryFilter;
    }

    private void assignPriceRangeParameters(List<String> priceRangesFilter) {
        for (String priceRange : priceRangesFilter) {
            switch (priceRange) {
                case "< 10€":
                    maxPrice1 = BigDecimal.valueOf(10);
                    break;
                case "10€ - 20€":
                    minPrice2 = BigDecimal.valueOf(10);
                    maxPrice2 = BigDecimal.valueOf(19);
                    break;
                case "20€ - 30€":
                    minPrice3 = BigDecimal.valueOf(20);
                    maxPrice3 = BigDecimal.valueOf(29);
                    break;
                case "30€ - 40€":
                    minPrice4 = BigDecimal.valueOf(30);
                    maxPrice4 = BigDecimal.valueOf(39);
                    break;
                case "40€ - 60€":
                    minPrice5 = BigDecimal.valueOf(40);
                    maxPrice5 = BigDecimal.valueOf(59);
                    break;
                case "> 60€":
                    minPrice6 = BigDecimal.valueOf(60);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + priceRange);
            }
            if (Objects.equals(minPrice6, BigDecimal.ZERO))
                minPrice6 = null;
        }
    }
}
