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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import adeuxpas.back.dto.AdHomeResponseDTO;
import adeuxpas.back.enums.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Implementation class for the AdService interface.
 * <p>
 * This service class provides implementations for ad-related operations.
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

    @Override
    public Page<AdHomeResponseDTO> findFilteredAdResponseDTOs(List<String> priceRangesFilter, List<String> citiesAndPostalCodesFilter,
                                                              List<String> articleStatesFilter, String categoryFilter, Pageable pageable) {

        // if no filter is checked, return all ads
        if (priceRangesFilter.isEmpty() && citiesAndPostalCodesFilter.isEmpty() &&
                articleStatesFilter.isEmpty() && categoryFilter.equals("Catégorie"))
            return this.findAllAdResponseDTOs(pageable);

        // extracting the postal codes
        List<String> postalCodes = citiesAndPostalCodesFilter.stream()
                .map(city -> city.substring(city.indexOf('(') + 1, city.indexOf(')')))
                .toList();

        // preparing the price ranges
        BigDecimal maxPrice1 = null;
        BigDecimal minPrice2 = null;
        BigDecimal maxPrice2 = null;
        BigDecimal minPrice3 = null;
        BigDecimal maxPrice3 = null;
        BigDecimal minPrice4 = null;
        BigDecimal maxPrice4 = null;
        BigDecimal minPrice5 = null;
        BigDecimal maxPrice5 = null;
        BigDecimal minPrice6 = BigDecimal.ZERO;

        for (String priceRange : priceRangesFilter)
            switch (priceRange) {
                case "< 10€":
                    maxPrice1 = BigDecimal.valueOf(10);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "10€ - 20€":
                    minPrice2 = BigDecimal.valueOf(10);
                    maxPrice2 = BigDecimal.valueOf(19);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "20€ - 30€":
                    minPrice3 = BigDecimal.valueOf(20);
                    maxPrice3 = BigDecimal.valueOf(29);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "30€ - 40€":
                    minPrice4 = BigDecimal.valueOf(30);
                    maxPrice4 = BigDecimal.valueOf(39);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "40€ - 60€":
                    minPrice5 = BigDecimal.valueOf(40);
                    maxPrice5 = BigDecimal.valueOf(59);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "> 60€":
                    minPrice6 = BigDecimal.valueOf(60);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + priceRange);
            }

        // extracting the category filter criteria
        String category;
        String subcategory = null;
        String gender = null;
        if (categoryFilter.contains("▸")) {
            category = categoryFilter.substring(0, categoryFilter.indexOf(" ▸"));
            if (categoryFilter.indexOf("▸") != categoryFilter.lastIndexOf("▸")) {
                subcategory = categoryFilter.substring(categoryFilter.indexOf("▸ ") + 1, categoryFilter.lastIndexOf(" ▸")).trim();
                gender = categoryFilter.substring(categoryFilter.lastIndexOf("▸ ") + 1).trim();
            } else
                subcategory = categoryFilter.substring(categoryFilter.indexOf("▸ ") + 1).trim();
        } else
            category = categoryFilter.equals("Catégorie") ? null : categoryFilter;

        // passing the formatted filtering criteria to the query and saving the result to a page
        Page<Ad> filteredAds = this.adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes.isEmpty() ? null : postalCodes,
                articleStatesFilter.isEmpty() ? null : articleStatesFilter,
                maxPrice1, minPrice2, maxPrice2, minPrice3, maxPrice3,
                minPrice4, maxPrice4, minPrice5, maxPrice5, minPrice6,
                category, subcategory, gender, acceptedAdStatuses, acceptedAccountStatuses, pageable
        );

        return this.convertToPageOfAdResponseDTOs(pageable, filteredAds);
    }

    @Override
    public Page<AdHomeResponseDTO> findAllAdResponseDTOs(Pageable pageable) {
        Page<Ad> myAds = this.adRepository.findByAcceptedStatusesOrderedByCreationDateDesc(acceptedAdStatuses, acceptedAccountStatuses, pageable);
        return this.convertToPageOfAdResponseDTOs(pageable, myAds);
    }

    private Page<AdHomeResponseDTO> convertToPageOfAdResponseDTOs(Pageable pageable, Page<Ad> adsPage) {
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
        // TODO:: A revoir apres implémentation du processus de connexion pour l'
        // utilisation de MapStruct (Ad newAd = mapper.adPostDtoToAd(adPostRequestDTO);)
        // =>
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
        // TODO:: A revoir apres implémentation du processus de connexion pour l'
        // utilisation de MapStruct (Ad newAd = mapper.adPostDtoToAd(adPostRequestDTO);)

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
     * Retrieves a user's ads list
     *
     * @param publisherId the concerned user's id.
     * @return a list of ads.
     */
    @Override
    public List<AdPostResponseDTO> findAdsByPublisherId(Long publisherId) {
        Optional<User> optionalUser = userRepository.findById(publisherId);
        if (optionalUser.isPresent()) {
            List<Ad> adList = adRepository.findAdsByPublisherId(publisherId);
            return adList.stream().map(adMapper::adToAdPostResponseDTO).toList();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
