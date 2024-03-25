package adeuxpas.back.service;

import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;

import java.util.List;

public interface AdService {

    List<HomePageAdDTO> findAllHomePageAds();

    List<HomePageAdDTO> findFilteredAds(List<String> prices, List<String> cities, List<String> articleStates, String category);
}
