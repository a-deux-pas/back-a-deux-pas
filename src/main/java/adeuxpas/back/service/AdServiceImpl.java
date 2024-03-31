package adeuxpas.back.service;

import adeuxpas.back.dto.AdResponseDTO;
import adeuxpas.back.dto.mapper.MapStructMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final MapStructMapper mapper;

    public AdServiceImpl(@Autowired AdRepository adRepository,
                         @Autowired MapStructMapper mapper) {
        this.adRepository = adRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<AdResponseDTO> findFilteredResponseAdDTOs(List<String> priceRangesFilter, List<String> citiesAndPostalCodesFilter,
                                                          List<String> articleStatesFilter, String categoryFilter, Pageable pageable) {

        // if no filter is checked, return all ads
        if (priceRangesFilter.isEmpty() && citiesAndPostalCodesFilter.isEmpty() &&
                articleStatesFilter.isEmpty() && categoryFilter.equals("Catégorie"))
            return this.findAllResponseAdDTOs(pageable);

        // extracting the postal codes
        List<String> postalCodes = citiesAndPostalCodesFilter.stream()
                                        .map(city -> city.substring(city.indexOf('(') + 1, city.indexOf(')')))
                                        .collect(Collectors.toList());

        // preparing the price ranges
        BigDecimal maxPrice1 = null;
        BigDecimal minPrice2 = null;
        BigDecimal maxPrice2 = null;
        BigDecimal minPrice3 = null;
        BigDecimal maxPrice3 = null;
        BigDecimal minPrice4 = null;
        BigDecimal maxPrice4 = null;
        BigDecimal minPrice5 = null;
        BigDecimal maxPrice5 = null;
        BigDecimal minPrice6 = BigDecimal.ZERO;

        for (String priceRange : priceRangesFilter) {
            switch (priceRange) {
                case "< 10€":
                    maxPrice1 = BigDecimal.valueOf(10);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "10€ - 20€":
                    minPrice2 = BigDecimal.valueOf(10);
                    maxPrice2 = BigDecimal.valueOf(19);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "20€ - 30€":
                    minPrice3 = BigDecimal.valueOf(20);
                    maxPrice3 = BigDecimal.valueOf(29);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "30€ - 40€":
                    minPrice4 = BigDecimal.valueOf(30);
                    maxPrice4 = BigDecimal.valueOf(39);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "40€ - 60€":
                    minPrice5 = BigDecimal.valueOf(40);
                    maxPrice5 = BigDecimal.valueOf(59);
                    if (Objects.equals(minPrice6, BigDecimal.ZERO)) minPrice6 = null;
                    break;
                case "> 60€":
                    minPrice6 = BigDecimal.valueOf(60);
                    break;
            }
        }

        // extracting the category filter criteria
        String category;
        String subcategory = null;
        String gender = null;
        if(categoryFilter.contains("▸")){
            category = categoryFilter.substring(0, categoryFilter.indexOf(" ▸"));
            if(categoryFilter.indexOf("▸") != categoryFilter.lastIndexOf("▸")){
                subcategory = categoryFilter.substring(categoryFilter.indexOf("▸ ") + 1, categoryFilter.lastIndexOf(" ▸")).trim();
                gender = categoryFilter.substring(categoryFilter.lastIndexOf("▸ ") + 1).trim();
            } else
                subcategory = categoryFilter.substring(categoryFilter.indexOf("▸ ") + 1).trim();
        } else
            category = categoryFilter.equals("Catégorie") ? null : categoryFilter;

        System.out.println(category + ", " + subcategory + ", " + gender);

        // passing the formatted filtering criteria to the query and saving the result to a page
        Page<Ad> filteredAds = this.adRepository.findFilteredAdsOrderedByCreationDateDesc(
                postalCodes.isEmpty() ? null : postalCodes,
                articleStatesFilter.isEmpty() ? null : articleStatesFilter,
                maxPrice1, minPrice2, maxPrice2, minPrice3, maxPrice3,
                minPrice4, maxPrice4, minPrice5, maxPrice5, minPrice6,
                category, subcategory, gender, AdStatus.AVAILABLE, pageable
        );

        return this.convertToPageOfResponseAdDTOs(pageable, filteredAds);
    }

    private Page<AdResponseDTO> findAllResponseAdDTOs(Pageable pageable) {
        Page<Ad> myAds = this.adRepository.findAllByStatusOrderByCreationDateDesc(AdStatus.AVAILABLE, pageable);
        return this.convertToPageOfResponseAdDTOs(pageable, myAds);
    }

    private Page<AdResponseDTO> convertToPageOfResponseAdDTOs(Pageable pageable, Page<Ad> adsPage) {
        List<AdResponseDTO> mappedAdsList = adsPage.stream()
                .map(mapper::adToResponseAdDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(mappedAdsList, pageable, adsPage.getTotalElements());
    }
}
