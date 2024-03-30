package adeuxpas.back.controller;

import adeuxpas.back.dto.ResponseAdDTO;
import adeuxpas.back.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Object> findAllAds(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "8") int pageSize){
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<ResponseAdDTO> adPage = this.adService.findAllResponseAdDTOs(pageable);
            return ResponseEntity.ok(adPage.getContent());
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/liste", params = {"priceRanges", "citiesAndPostalCodes", "articleStates", "category"})
    public ResponseEntity<Object> findFilteredAds(
            @RequestParam("priceRanges") List<String> priceRangesFilter,
            @RequestParam("citiesAndPostalCodes") List<String> citiesAndPostalCodesFilter,
            @RequestParam("articleStates") List<String> articleStatesFilter,
            @RequestParam("category") String categoryFilter,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "8") int pageSize ) {

        System.out.println(priceRangesFilter + " " + citiesAndPostalCodesFilter + " " + articleStatesFilter + " " + categoryFilter);
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<ResponseAdDTO> adPage = this.adService
                                            .findFilteredResponseAdDTOs(priceRangesFilter, citiesAndPostalCodesFilter,
                                                                        articleStatesFilter, categoryFilter, pageable);
            return ResponseEntity.ok(adPage.getContent());
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
