package adeuxpas.back.controller;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.ArticlePictureRepository;
import adeuxpas.back.service.AdService;
import adeuxpas.back.service.UserService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final AdService adService;
    private final UserService userService;
    private final AdRepository adRepo;
    private final ArticlePictureRepository adPicRepo;

    public AdController(
            @Autowired AdService adService, @Autowired UserService userService, @Autowired AdRepository adRepo,
            @Autowired ArticlePictureRepository adPicRepo) {
        this.adService = adService;
        this.userService = userService;
        this.adRepo = adRepo;
        this.adPicRepo = adPicRepo;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> findAllAds() {
        try {
            return ResponseEntity.ok(this.adService.findAllHomePageAds());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(AdController.class);

    @PostMapping("/create")
    public ResponseEntity<?> postAd(@RequestBody AdPostDto adDto) {
        try {
            // A enlever apres installation de MapStruct
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

            List<ArticlePicture> articlePictures = new ArrayList<>();

            List<ArticlePictureDTO> adPics = adDto.getArticlePictures();

            for (ArticlePictureDTO adPic : adPics) {
                ArticlePicture newArticlePicture = new ArticlePicture();
                newArticlePicture.setUrl(adPic.getUrl());
                newArticlePicture.setAd(newAd);
                articlePictures.add(newArticlePicture);
            }

            newAd.setArticlePictures(articlePictures);

            Ad savedAd = adRepo.save(newAd);

            logger.info("Requête POST reçue pour la création d'une annonce.");
            logger.info("Contenu du corps de la requête : {}", savedAd.toString());
            logger.info("Contenu du corps du DTO : {}", adDto.toString());
            // A enlever apres installation de MapStruct

            return ResponseEntity.created(new URI("/ads/" + savedAd.getId())).build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create ad.");
        }
    }

}
