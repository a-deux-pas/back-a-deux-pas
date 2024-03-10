package adeuxpas.back.service;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.entity.Ad;

import java.util.List;
import java.util.Optional;

public interface AdService {

    List<Ad> findAllAds();

    Optional<Ad> postAd(AdPostDto adDto);
}
