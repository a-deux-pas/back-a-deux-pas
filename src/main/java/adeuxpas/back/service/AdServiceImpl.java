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
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UsersFavoriteAdsRepository;
import adeuxpas.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

import adeuxpas.back.dto.AdCardResponseDTO;
import adeuxpas.back.enums.AccountStatus;
import java.math.BigDecimal;

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
    private final UsersFavoriteAdsRepository favoriteRepository;
    private final AdMapper adMapper;

    public AdServiceImpl(@Autowired UserRepository userRepository,
            @Autowired AdRepository adRepository,
            @Autowired UsersFavoriteAdsRepository favoriteRepository,
            @Autowired AdMapper adMapper) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.favoriteRepository = favoriteRepository;
        this.adMapper = adMapper;
    }

    /**
     * Finds filtered ads and maps them to AdCardResponseDTOs.
     *
     * @param priceRangesFilter          The list of price range filters.
     * @param citiesAndPostalCodesFilter The list of city and postal code filters.
     * @param articleStatesFilter        The list of article state filters.
     * @param categoryFilter             The category filter.
     * @param loggedInUserId             The ID of the logged-in user.
     * @param pageable                   The pagination information.
     * @return The page of AdCardResponseDTOs.
     */
    @Override
    public Page<AdCardResponseDTO> findFilteredAdCardResponseDTOs(List<String> priceRangesFilter,
            List<String> citiesAndPostalCodesFilter,
            List<String> articleStatesFilter, String categoryFilter, Long loggedInUserId, Pageable pageable) {

        // check if the user exists, if not set loggedInUserId to null
        if (loggedInUserId != null) {
            Optional<User> optionalUser = userRepository.findById(loggedInUserId);
            if (optionalUser.isEmpty()) {
                loggedInUserId = null;
            }
        }

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
     * @param loggedInUserId The current user's id
     * @return The page of AdCardResponseDTOs.
     */
    private Page<AdCardResponseDTO> convertToPageOfAdCardResponseDTOs(Pageable pageable, Page<Ad> adsPage,
            Long loggedInUserId) {

        Set<Long> favoriteAdIds = Collections.emptySet();

        // Retrieve favorite ad IDs if logged in user ID is provided
        if (loggedInUserId != null) {
            favoriteAdIds = favoriteRepository.findFavoriteAdIdsByUserId(loggedInUserId);
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
     * persist an Ad object in database
     *
     * @param adPostRequestDTO coming from the front end application
     * @return an adPostResponseDTO to the front
     */
    @Override
    public AdPostResponseDTO postAd(AdPostRequestDTO adPostRequestDTO) {
        // TODO:: A revoir (fix cloudinary branch)
        User publisher = userRepository.findById(adPostRequestDTO.getPublisherId())
                .orElseThrow(() -> new UsernameNotFoundException("Publisher not found"));
        Ad newAd = new Ad();
        newAd.setTitle(adPostRequestDTO.getTitle());
        newAd.setArticleDescription(adPostRequestDTO.getArticleDescription());
        newAd.setCreationDate(LocalDateTime.now());
        newAd.setPrice(adPostRequestDTO.getPrice());
        newAd.setCategory(adPostRequestDTO.getCategory());
        newAd.setSubcategory(adPostRequestDTO.getSubcategory());
        newAd.setArticleGender(adPostRequestDTO.getArticleGender());
        newAd.setPublisher(publisher);
        newAd.setArticleState(adPostRequestDTO.getArticleState());
        newAd.setStatus(AdStatus.AVAILABLE);

        List<ArticlePicture> articlePictures = new ArrayList<>();
        List<String> adPics = adPostRequestDTO.getArticlePictures();

        for (String adPic : adPics) {
            ArticlePicture newArticlePicture = new ArticlePicture();
            newArticlePicture.setUrl(adPic);
            newArticlePicture.setAd(newAd);
            articlePictures.add(newArticlePicture);
        }

        newAd.setArticlePictures(articlePictures);

        Ad savedAd = adRepository.save(newAd);
        return adMapper.adToAdPostResponseDTO(savedAd);
    }

    // TO DO :: (fix cloudinary branch) should return an adDetail / adCard ?
    /**
     * Retrieves an ad information by a its id
     *
     * @param id the said ad.
     * @return an ad
     */
    @Override
    public AdPostResponseDTO findAdById(long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            return adMapper.adToAdPostResponseDTO(ad);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Finds ads and maps them into an AdCardResponseDTO in order to display
     * them on an adpage content
     * 
     * @param publisherId    The ad's publisher's id
     * @param pageable       The pagination information.
     * @param loggedInUserId The current user's id
     * 
     * @return The page of AdCardResponseDTO.
     */
    @Override
    public Page<AdCardResponseDTO> findPageOfUserAdsList(long publisherId, Pageable pageable, Long loggedInUserId,
            Long adId) {
        Optional<User> optionalUser = userRepository.findById(publisherId);
        if (optionalUser.isPresent()) {
            Page<Ad> adsPage = adRepository.findAvailableAdsByPublisherId(publisherId, pageable, adId);
            Page<AdCardResponseDTO> mappedAdsPage = this.convertToPageOfAdCardResponseDTOs(pageable, adsPage);
            if (loggedInUserId != null) {
                Set<Long> favoriteAdsIds = favoriteRepository.findUserPublisherFavoriteAdsIds(loggedInUserId,
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
     * @param pageable    The pagination information.
     * @param publisherId The ad's publisher's id
     * 
     * @return The page of AdCardResponseDTO.
     */
    @Override
    public Page<AdCardResponseDTO> getUserAdsTab(long publisherId, Pageable pageable) {
        Optional<User> optionalUser = userRepository.findById(publisherId);
        if (optionalUser.isPresent()) {
            Page<Ad> adsPage = adRepository.findSortedAdsByPublisherIdOrderByCreationDateDesc(publisherId, pageable);
            return this.convertToPageOfAdCardResponseDTOs(pageable, adsPage);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * find a list containing the last four ads sharing the same category as the
     * current ad's
     * 
     * @param category    The current ad's category
     * @param publisherId The ad's publisher's id
     * @param userId      The current user's id
     * @param pageable    The pagination information.
     * 
     * @return a list of similar ads sharing the same category
     */
    @Override
    public Page<AdCardResponseDTO> findSimilarAds(String category, Long publisherId, Long userId, Pageable pageable) {
        Page<Ad> adsPage = this.adRepository.findAdsByCategoryOrderByCreationDateDesc(category, publisherId, userId,
                pageable);
        Page<AdCardResponseDTO> mappedAdsPage = this.convertToPageOfAdCardResponseDTOs(pageable, adsPage);
        if (userId != null) {
            for (AdCardResponseDTO dto : mappedAdsPage) {
                Set<Long> favoriteAdsIds = favoriteRepository.findUserPublisherFavoriteAdsIds(userId,
                        dto.getPublisherId());
                if (favoriteAdsIds.contains(dto.getId())) {
                    dto.setFavorite(true);
                }
            }
        }
        return mappedAdsPage;
    }

    /**
     * converts a page of Ad into a page of AdPostResponseDTO
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
     * @param userId The ID of the user.
     * @return The list of favorites ads.
     */
    @Override
    public Page<AdCardResponseDTO> findFavoriteAdsByUserId(long userId, Pageable pageable) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<UsersFavoriteAds> favoritesAds = this.favoriteRepository.findByUserOrderByAddedAtDesc(user, pageable);
            return this.convertFavoritesToPageOfAdCardResponseDTOs(pageable, favoritesAds);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Update the ad favorite status added by a user.
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
            Optional<UsersFavoriteAds> optionalFavorite = favoriteRepository.findById(favoriteKey);
            if (!optionalFavorite.isPresent()) {
                UsersFavoriteAds newFavorite = new UsersFavoriteAds(favoriteKey, optionalUser.get(), optionalAd.get(),
                        LocalDateTime.now());
                favoriteRepository.save(newFavorite);
            } else {
                favoriteRepository.delete(optionalFavorite.get());
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public long checkFavoriteCount(long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            return favoriteRepository.checksFavoriteCount(adId);
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
