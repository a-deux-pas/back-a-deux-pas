package adeuxpas.back.controller;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.dto.mapper.AdMapper;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.slf4j.Logger;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final AdService adService;
    private final UserService userService;
    private final AdRepository repo;
    private final AdMapper mapper;

    public AdController(
            @Autowired AdService adService,
            @Autowired UserService userService,
            @Autowired AdRepository repo,
            @Autowired AdMapper mapper) {
        this.adService = adService;
        this.userService = userService;
        this.repo = repo;
        this.mapper = mapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(AdController.class);

    @GetMapping("/list")
    public ResponseEntity<Object> findAllAds() {
        try {
            return ResponseEntity.ok(this.adService.findAllHomePageAds());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

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

            // Ad newAd = mapper.adPostDtoToAd(adDto);

            List<ArticlePicture> articlePictures = new ArrayList<>();
            List<ArticlePictureDTO> adPics = adDto.getArticlePictures();

            for (ArticlePictureDTO adPic : adPics) {
                ArticlePicture newArticlePicture = new ArticlePicture();
                // ArticlePicture newArticlePicture =
                // mapper.articlePictureDTOToaArticlePicture(adPic);
                newArticlePicture.setUrl(adPic.getUrl());
                newArticlePicture.setAd(newAd);
                articlePictures.add(newArticlePicture);
            }

            newAd.setArticlePictures(articlePictures);

            Ad savedAd = repo.save(newAd);

            logger.info("Requête POST reçue pour la création d'une annonce.");
            logger.info("Contenu du corps de la requête : {}", savedAd.toString());
            logger.info("Contenu du corps du DTO : {}", adDto.toString());
            // A enlever apres installation de MapStruct

            // return new ResponseEntity<>(savedAd, HttpStatus.CREATED);
            // return ResponseEntity.created(new URI("ad/" + savedAd.getId())).build();
            // return ResponseEntity.status(HttpStatus.CREATED).body(savedAd);
            return ResponseEntity.ok().body(savedAd);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create ad.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        try {
            Optional<Ad> ad = adService.findById(id);
            if (ad.isEmpty())
                return ResponseEntity.status(404).body("Not found");

            return ResponseEntity.ok(ad.get());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
