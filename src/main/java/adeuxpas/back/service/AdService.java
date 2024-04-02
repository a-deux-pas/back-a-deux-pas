package adeuxpas.back.service;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;

import java.util.List;
import java.util.Optional;

public interface AdService {

    Optional<Ad> postAd(AdPostDto adDto);

    List<HomePageAdDTO> findAllHomePageAds();
}
