package adeuxpas.back.controller;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.service.AdService;
import adeuxpas.back.service.UserService;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
@RequestMapping("/annonce")
public class AdController {

    private final AdService adService;
    private final UserService userService;
    private final AdRepository adRepo;

    public AdController(
            @Autowired AdService adService, @Autowired UserService userService, @Autowired AdRepository adRepo) {
        this.adService = adService;
        this.userService = userService;
        this.adRepo = adRepo;
    }

    @GetMapping("/liste")
    public ResponseEntity<Object> findAllAds() {
        try {
            return ResponseEntity.ok(this.adService.findAllHomePageAds());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(AdController.class);

    @PostMapping("/creer-une-annonce")
    public ResponseEntity<?> postAd(@RequestBody AdPostDto adDto) {

        User publisher = new User();
        Ad newAd = new Ad();
        publisher = this.userService.findUserById(adDto.getPublisherId()).get();

        newAd.setPublisher(publisher);
        newAd.setStatus(AdStatus.AVAILABLE);
        newAd.setArticleDescription(adDto.getArticleDescription());
        newAd.setArticleState(adDto.getArticleState());
        newAd.setPrice(adDto.getPrice());
        newAd.setArticlePictures(adDto.getArticlePictures());

        this.adRepo.save(newAd);

        System.err.println("@@@@@@@@");
        System.out.println("newAd:");
        System.out.println("id: " + newAd.getId());
        System.out.println("title: " + newAd.getTitle());
        logger.info("Requête POST reçue pour la création d'une annonce.");
        logger.info("Contenu du corps de la requête : {}", newAd.toString());
        System.err.println("@@@@@@@@");

        // Optional<Ad> savedAd = this.adService.postAd(adDto);
        // if (savedAd.isPresent()) {
        // return ResponseEntity.ok(savedAd.get());
        // } else {
        // return ResponseEntity.status(500).body("Failed to create ad.");
        // }

        return ResponseEntity.ok(newAd);

    }
}
