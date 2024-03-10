package adeuxpas.back.controller;

import adeuxpas.back.dto.AdDto;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.service.AdService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(
        @Autowired AdService adService){
        this.adService = adService;
    }

    @GetMapping("/list")
    public ResponseEntity<String> findAllAds(){
        try {
            return ResponseEntity.ok(this.adService.findAllAds().toString());
        } catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/creation")
    public ResponseEntity<?> postAd(@RequestBody AdDto adDto) {
        try {
            Optional<Ad> savedAd = this.adService.postAd(adDto);
            if (savedAd.isPresent()) {
                return ResponseEntity.ok(savedAd.get());
            } else {
                return ResponseEntity.status(500).body("Failed to create ad.");
            }
        }  catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
