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

@Service
public class AdServiceImpl implements AdService {

    private AdMapper mapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AdServiceImpl(
            @Autowired AdMapper mapper,
            @Autowired AdRepository adRepository,
            @Autowired UserRepository userRepository) {
        this.mapper = mapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Ad postAd(AdPostRequestDTO adDto) {
        // TODO:: A revoir apres implémentation du processus de connexion pour l'
        // utilisation de MapStruct (Ad newAd = mapper.adPostDtoToAd(adDto);) =>
        User publisher = userRepository.findById(adDto.getPublisherId())
                .orElseThrow(() -> new UsernameNotFoundException("Publisher not found"));

        Ad newAd = new Ad();
        newAd.setTitle(adDto.getTitle());
        newAd.setArticleDescription(adDto.getArticleDescription());
        newAd.setCreationDate(LocalDateTime.now());
        newAd.setPrice(adDto.getPrice());
        newAd.setCategory(adDto.getCategory());
        newAd.setSubcategory(adDto.getSubcategory());
        newAd.setArticleGender(adDto.getArticleGender());
        newAd.setPublisher(publisher);
        newAd.setArticleState(adDto.getArticleState());
        newAd.setStatus(AdStatus.AVAILABLE);

        List<ArticlePicture> articlePictures = new ArrayList<>();
        List<ArticlePictureDTO> adPics = adDto.getArticlePictures();

        for (ArticlePictureDTO adPic : adPics) {
            ArticlePicture newArticlePicture = new ArticlePicture();
            newArticlePicture.setUrl(adPic.getUrl());
            newArticlePicture.setAd(newAd);
            articlePictures.add(newArticlePicture);
        }

        newAd.setArticlePictures(articlePictures);
        // TODO:: A revoir apres implémentation du processus de connexion pour l'
        // utilisation de MapStruct (Ad newAd = mapper.adPostDtoToAd(adDto);)

        return adRepository.save(newAd);
    }

    @Override
    public AdPostResponseDTO findAdById(Long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            return mapper.adToAdPostResponeDTO(ad);

        } else {
            throw new EntityNotFoundException();
        }
    }
}
