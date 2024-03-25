package adeuxpas.back.controller;

import adeuxpas.back.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            return ResponseEntity.ok(this.adService.findAllHomePageAds());
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/liste", params = {"priceRanges", "citiesAndPostalCodes", "articleStates", "category"})
    public ResponseEntity<Object> findFilteredAds(
            @RequestParam("priceRanges") List<String> priceRangesFilter,
            @RequestParam("citiesAndPostalCodes") List<String> citiesAndPostalCodesFilter,
            @RequestParam("articleStates") List<String> articleStatesFilter,
            @RequestParam("category") String categoryFilter) {

        System.out.println(priceRangesFilter + " " + citiesAndPostalCodesFilter + " " + articleStatesFilter + " " + categoryFilter);
        try {
            return ResponseEntity.ok(this.adService.findFilteredAds(priceRangesFilter, citiesAndPostalCodesFilter,
                                    articleStatesFilter, categoryFilter));
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
