package adeuxpas.back.service;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.dto.mapper.AdMapper;
import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.dto.HomePageAdDTO;

import adeuxpas.back.repository.AdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private AdMapper mapper;
    private final AdRepository adRepository;
    private final UserService userService;

    public AdServiceImpl(
            @Autowired AdMapper mapper,
            @Autowired AdRepository adRepository,
            @Autowired UserService userService) {
        this.mapper = mapper;
        this.adRepository = adRepository;
        this.userService = userService;
    }

    @Override
    public List<HomePageAdDTO> findAllHomePageAds() {
        List<Ad> myAds = this.adRepository.findAll();
        List<HomePageAdDTO> mappedAdsList = myAds.stream().map(mapper::adToHomePageAdDTO)
                .collect(Collectors.toList());
        if (mappedAdsList.size() > 1)
            this.sortByCreationDateDesc(mappedAdsList);
        return mappedAdsList;
    }

    private void sortByCreationDateDesc(List<HomePageAdDTO> myAds) {
        myAds.sort((a, b) -> {
            if (a.getCreationDate().isBefore(b.getCreationDate()))
                return 1;
            else if (a.getCreationDate().isAfter(b.getCreationDate()))
                return -1;
            else
                return 0;
        });
    }

    @Override
    public Ad postAd(AdPostDto adDto) {
        // TODO:: A revoir apres utilisation de MapStruct ET implémentation du processus
        // de connexion
        User publisher = userService.findUserById(adDto.getPublisherId())
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

        // Ad newAd = mapper.adPostDtoToAd(adDto);

        List<ArticlePicture> articlePictures = new ArrayList<>();
        List<ArticlePictureDTO> adPics = adDto.getArticlePictures();

        for (ArticlePictureDTO adPic : adPics) {
            ArticlePicture newArticlePicture = new ArticlePicture();
            newArticlePicture.setUrl(adPic.getUrl());
            newArticlePicture.setAd(newAd);
            articlePictures.add(newArticlePicture);
        }

        newAd.setArticlePictures(articlePictures);
        return newAd;
    }

    @Override
    public Optional<Ad> findById(Long id) {
        try {
            return this.adRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }
}