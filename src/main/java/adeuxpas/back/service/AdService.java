package adeuxpas.back.service;

import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.entity.Ad;

import java.util.Optional;

public interface AdService {
    Ad postAd(AdPostRequestDTO adDto);

    Optional<Ad> findById(Long adId);

}
