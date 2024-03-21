package adeuxpas.back.service;

import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;

import java.util.List;

public interface AdService {

    List<HomePageAdDTO> findAllHomePageAds();

    List<HomePageAdDTO> findFilteredAds(List<String> selectedPrices, List<String> selectedCities, List<String> selectedArticleStates, List<String> selectedCategories, List<String> selectedSubcategories, List<String> selectedGender);
}
