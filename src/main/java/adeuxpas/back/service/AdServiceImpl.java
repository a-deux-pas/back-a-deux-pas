package adeuxpas.back.service;

import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.dto.mapper.MapStructMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService{

    private final AdRepository adRepository;
    private final MapStructMapper mapper;

    public AdServiceImpl(@Autowired AdRepository adRepository,
                         @Autowired MapStructMapper mapper){
        this.adRepository = adRepository;
        this.mapper = mapper;
    }

    @Override
    public List<HomePageAdDTO> findAllHomePageAds() {
        List<Ad> myAds = this.adRepository.findAll();
        List<HomePageAdDTO> mappedAdsList = myAds.stream().map(mapper::adToHomePageAdDTO).collect(Collectors.toList());
        if(mappedAdsList.size() > 1) this.sortByCreationDateDesc(mappedAdsList);

        return mappedAdsList;
    }

    @Override
    public List<HomePageAdDTO> findFilteredAds(List<String> selectedPriceRanges, List<String> citiesAndPostalCodes, List<String> selectedArticleStates, List<String> selectedCategories, List<String> selectedSubcategories, List<String> selectedGender) {
        // Formatting the request parameters (received from the controller) to be passed as query parameters (to the repository)
        // extracting the postal codes :
        List<String> postalCodes = citiesAndPostalCodes.stream().map(city -> city.substring(city.indexOf('(') + 1, city.indexOf(')')))
                .collect(Collectors.toList());
        postalCodes.forEach(System.out::println);

        List<Ad> myAds = this.adRepository.findFilteredAds(selectedPriceRanges, postalCodes, selectedArticleStates, selectedCategories, selectedSubcategories, selectedGender);
        List<HomePageAdDTO> mappedAdsList = myAds.stream().map(mapper::adToHomePageAdDTO).collect(Collectors.toList());
        if(mappedAdsList.size() > 1) this.sortByCreationDateDesc(mappedAdsList);

        return mappedAdsList;
    }

    private void sortByCreationDateDesc(List<HomePageAdDTO> myAds) {
        myAds.sort((a, b) -> {
           if (a.getCreationDate().isBefore(b.getCreationDate())) return 1;
           else if (a.getCreationDate().isAfter(b.getCreationDate())) return -1;
           else return 0;
        });
    }
}
