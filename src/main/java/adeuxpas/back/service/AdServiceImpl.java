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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AdServiceImpl(
            @Autowired AdMapper adMapper,
            @Autowired AdRepository adRepository,
            @Autowired UserRepository userRepository) {
        this.adMapper = adMapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
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
        Long publisherIdValue = 1L;
        Optional<User> optionalUser = userRepository.findById(publisherIdValue);
        if (optionalUser.isPresent()) {
            return adRepository.findAdsByPublisherIdCount(publisherIdValue);
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
}
