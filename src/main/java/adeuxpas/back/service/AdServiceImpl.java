package adeuxpas.back.service;

import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.dto.mapper.MapStructMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<HomePageAdDTO> findAllHomePageAds() {
        List<Ad> myAds = this.adRepository.findAll();
        List<HomePageAdDTO> mappedAdsList = myAds.stream().map(mapper::adToHomePageAdDTO).collect(Collectors.toList());
        if (mappedAdsList.size() > 1) this.sortByCreationDateDesc(mappedAdsList);

        return mappedAdsList;
    }

    @Override
    public List<HomePageAdDTO> findFilteredAds(List<String> priceRangesFilter, List<String> citiesAndPostalCodesFilter,
                                               List<String> articleStatesFilter, String categoryFilter) {

        // if no filter is checked, return all ads
        if (priceRangesFilter.isEmpty() && citiesAndPostalCodesFilter.isEmpty() &&
                articleStatesFilter.isEmpty() && categoryFilter.equals("Catégorie"))
            return this.findAllHomePageAds();

        // extracting the postal codes
        List<String> postalCodes = citiesAndPostalCodesFilter.stream().map(city -> city.substring(city.indexOf('(') + 1, city.indexOf(')')))
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
        BigDecimal minPrice6 = BigDecimal.valueOf(0);

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
        if(categoryFilter.contains("/")){
            category = categoryFilter.substring(0,categoryFilter.indexOf(" /"));
            if(categoryFilter.indexOf("/") != categoryFilter.lastIndexOf("/")){
                subcategory = categoryFilter.substring(categoryFilter.indexOf("/ ") + 1, categoryFilter.lastIndexOf(" /")).trim();
                gender = categoryFilter.substring(categoryFilter.lastIndexOf("/ ") + 1).trim();
            } else
                subcategory = categoryFilter.substring(categoryFilter.indexOf("/ ") + 1).trim();
        } else
            category = categoryFilter.equals("Catégorie") ? null : categoryFilter;

        System.out.println(category + ", " + subcategory + ", " + gender);

        // passing the necessary formatted filter criteria to the query and returning the result
        List<Ad> filteredAds = this.adRepository.findFilteredAds(
                postalCodes.isEmpty() ? null : postalCodes,
                articleStatesFilter.isEmpty() ? null : articleStatesFilter,
                maxPrice1, minPrice2, maxPrice2, minPrice3, maxPrice3,
                minPrice4, maxPrice4, minPrice5, maxPrice5, minPrice6,
                category, subcategory, gender
        );

        // converting the returned list of Ad entities into a list of HomePAgeAdDTOs
        List<HomePageAdDTO> mappedFilteredAds = filteredAds.stream().map(mapper::adToHomePageAdDTO).collect(Collectors.toList());
        if (mappedFilteredAds.size() > 1) this.sortByCreationDateDesc(mappedFilteredAds);

        return mappedFilteredAds;

    }
    private void sortByCreationDateDesc (List < HomePageAdDTO > myAds) {
        myAds.sort((a, b) -> {
            if (a.getCreationDate().isBefore(b.getCreationDate())) return 1;
            else if (a.getCreationDate().isAfter(b.getCreationDate())) return -1;
            else return 0;
        });
    }
}
