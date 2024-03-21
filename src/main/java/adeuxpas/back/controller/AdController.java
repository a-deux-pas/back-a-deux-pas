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

    @GetMapping(value = "/liste", params = {"priceRanges", "citiesAndPostalCodes", "articleStates", "categories", "subcategories", "gender"})
    public ResponseEntity<Object> findFilteredAds(
            @RequestParam("priceRanges") List<String> selectedPriceRanges,
            @RequestParam("citiesAndPostalCodes") List<String> citiesAndPostalCodes,
            @RequestParam("articleStates") List<String> selectedArticleStates,
            @RequestParam("categories") List<String> selectedCategories,
            @RequestParam("subcategories") List<String> selectedSubcategories,
            @RequestParam("gender") List<String> selectedGender) {

        System.out.println(selectedPriceRanges + " " + citiesAndPostalCodes + " " + selectedArticleStates + " " + selectedCategories + " " + selectedSubcategories + " " + selectedGender);
        try {
            return ResponseEntity.ok(this.adService.findFilteredAds(selectedPriceRanges, citiesAndPostalCodes,
                                    selectedArticleStates, selectedCategories, selectedSubcategories, selectedGender));
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
