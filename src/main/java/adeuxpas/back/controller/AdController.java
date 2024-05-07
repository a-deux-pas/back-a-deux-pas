package adeuxpas.back.controller;

import adeuxpas.back.dto.AdHomeResponseDTO;
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
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(@Autowired AdService adService){
        this.adService = adService;
    }

    @GetMapping(value = "/list", params = {"priceRanges", "citiesAndPostalCodes", "articleStates", "category"})
    public ResponseEntity<Object> getAdsPage(
            @RequestParam("priceRanges") List<String> priceRangesFilter,
            @RequestParam("citiesAndPostalCodes") List<String> citiesAndPostalCodesFilter,
            @RequestParam("articleStates") List<String> articleStatesFilter,
            @RequestParam("category") String categoryFilter,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "8") int pageSize ) {

       try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdHomeResponseDTO> adsPage = this.adService.findFilteredAdResponseDTOs(priceRangesFilter, citiesAndPostalCodesFilter,
                                                                                    articleStatesFilter, categoryFilter, pageable);
            return ResponseEntity.ok(adsPage.getContent());
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
