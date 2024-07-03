package adeuxpas.back.service;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.dto.mapper.AdMapper;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import adeuxpas.back.dto.AdHomeResponseDTO;
import adeuxpas.back.enums.AccountStatus;
import java.math.BigDecimal;
import java.util.Objects;

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
    private final AdMapper adMapper;

    public AdServiceImpl(@Autowired UserRepository userRepository,
            @Autowired AdRepository adRepository,
            @Autowired AdMapper adMapper) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.adMapper = adMapper;
    }

    /**
     * Finds filtered ads and maps them to AdHomeResponseDTOs.
     *
     * @param priceRangesFilter          The list of price range filters.
     * @param citiesAndPostalCodesFilter The list of city and postal code filters.
     * @param articleStatesFilter        The list of article state filters.
     * @param categoryFilter             The category filter.
     * @param pageable                   The pagination information.
     * @return The page of AdHomeResponseDTOs.
     */
    @Override
    public Page<AdHomeResponseDTO> findFilteredAdHomeResponseDTOs(List<String> priceRangesFilter,
            List<String> citiesAndPostalCodesFilter,
            List<String> articleStatesFilter, String categoryFilter, Pageable pageable) {

        // if no filter is checked, return all ads
        if (shouldReturnAllAds(priceRangesFilter, citiesAndPostalCodesFilter, articleStatesFilter, categoryFilter)) {
            return findAllAdHomeResponseDTOs(pageable);
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
                category, subcategory, gender, acceptedAdStatuses, acceptedAccountStatuses, pageable);
        return this.convertToPageOfAdHomeResponseDTOs(pageable, filteredAds);
    }

    /**
     * Finds all ads and maps them to AdHomeResponseDTOs.
     *
     * @param pageable The pagination information.
     * @return The page of AdHomeResponseDTOs.
     */
    @Override
    public Page<AdHomeResponseDTO> findAllAdHomeResponseDTOs(Pageable pageable) {
        Page<Ad> myAds = this.adRepository.findByAcceptedStatusesOrderedByCreationDateDesc(acceptedAdStatuses,
                acceptedAccountStatuses, pageable);
        return this.convertToPageOfAdHomeResponseDTOs(pageable, myAds);
    }

    /**
     * Converts a page of Ad entities to a page of AdHomeResponseDTOs.
     *
     * @param pageable The pagination information.
     * @param adsPage  The page of Ad entities.
     * @return The page of AdHomeResponseDTOs.
     */
    private Page<AdHomeResponseDTO> convertToPageOfAdHomeResponseDTOs(Pageable pageable, Page<Ad> adsPage) {
        List<AdHomeResponseDTO> mappedAdsList = adsPage.stream()
                .map(adMapper::adToAdHomeResponseDTO)
                .toList();
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
        List<ArticlePictureDTO> adPics = adPostRequestDTO.getArticlePictures();

        for (ArticlePictureDTO adPic : adPics) {
            ArticlePicture newArticlePicture = new ArticlePicture();
            newArticlePicture.setUrl(adPic.getUrl());
            newArticlePicture.setAd(newAd);
            articlePictures.add(newArticlePicture);
        }

        newAd.setArticlePictures(articlePictures);

        Ad savedAd = adRepository.save(newAd);
        return adMapper.adToAdPostResponseDTO(savedAd);
    }

    /**
     * Retrieves an ad information by a user's id
     *
     * @param id the concerned user.
     * @return an ad
     */
    @Override
    public AdPostResponseDTO findAdById(Long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            return adMapper.adToAdPostResponseDTO(ad);

        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Finds ads and maps them to AdPostResponseDTOs.
     * 
     * @param pageable    The pagination information.
     * @param publisherId
     * @return The page of AdHomeResponseDTOs.
     */
    @Override
    public Page<AdPostResponseDTO> findPageOfUserAdsList(Long publisherId, Pageable pageable) {
        Optional<User> optionalUser = userRepository.findById(publisherId);
        if (optionalUser.isPresent()) {
            Page<Ad> adsPage = adRepository.findAdsByPublisherIdOrderByCreationDateDesc(publisherId, pageable);
            return this.convertToPageOfAdPostResponseDTOs(pageable, adsPage);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * converts a page of Ad into a page of AdPostResponseDTO
     *
     * @param pageable The pagination information.
     * @param adsPage  The page of Ad entities.
     * @return The page of AdHomeResponseDTOs.
     */
    private Page<AdPostResponseDTO> convertToPageOfAdPostResponseDTOs(Pageable pageable, Page<Ad> adsPage) {
        List<AdPostResponseDTO> mappedAdsList = adsPage.stream()
                .map(adMapper::adToAdPostResponseDTO)
                .toList();
        return new PageImpl<>(mappedAdsList, pageable, adsPage.getTotalElements());
    }

    /**
     * Checks how many ads have been published by a user
     * 
     * @param publisherId
     * @return The number of ads published by a user
     */
    @Override
    public Long getUserAdsListLength(Long publisherId) {
        Optional<User> optionalUser = userRepository.findById(publisherId);
        if (optionalUser.isPresent()) {
            return adRepository.findAdsCountByPublisherId(publisherId);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * find a list containing the last four ads sharing the same category as the
     * current ad's
     * 
     * @param category
     * @param pageable
     * @return a list of similar ads sharing the same category
     */
    @Override
    public Page<AdPostResponseDTO> findSimilarAds(String category, Long publisherId, Long userId, Pageable pageable) {
        Page<Ad> adsPage = this.adRepository.findAdsByCategoryOrderByCreationDateDesc(category, publisherId, userId,
                pageable);
        return this.convertToPageOfAdPostResponseDTOs(pageable, adsPage);
    }

    // ==================== parameter extraction and formatting helper methods
    // =======================
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
