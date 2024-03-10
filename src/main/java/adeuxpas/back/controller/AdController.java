package adeuxpas.back.controller;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/annonces")
public class AdController {

    private final AdService adService;

    public AdController(@Autowired AdService adService){
        this.adService = adService;
    }

    @GetMapping("/liste")
    public ResponseEntity<Object> findAllAds(){
        try {
            return ResponseEntity.ok(this.adService.findAllAds());
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
