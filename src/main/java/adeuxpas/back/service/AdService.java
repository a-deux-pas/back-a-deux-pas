package adeuxpas.back.service;

import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;

public interface AdService {
    AdPostResponseDTO postAd(AdPostRequestDTO adDto);

    AdPostResponseDTO findAdById(Long adId);
}
