package adeuxpas.back.service;

import java.util.List;

import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdService {
    AdPostResponseDTO postAd(AdPostRequestDTO adDto);

    AdPostResponseDTO findAdById(Long adId);

    List<AdPostResponseDTO> findAdsByPublisherId(Long publisherId);

    Page<AdPostResponseDTO> findPageOfUserAdsList(Long publisherId, Pageable pageable);

    Long getUserAdsListLength(Long publisherId);

    Page<AdPostResponseDTO> findSimilarAds(String adCategory, Pageable pageable);
}
