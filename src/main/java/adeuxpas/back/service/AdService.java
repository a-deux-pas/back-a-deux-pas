package adeuxpas.back.service;

import java.util.List;

import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;

public interface AdService {
    AdPostResponseDTO postAd(AdPostRequestDTO adDto);

    AdPostResponseDTO findAdById(Long adId);

    List<AdPostResponseDTO> findAdsByPublisherId(Long publisherId);
}
