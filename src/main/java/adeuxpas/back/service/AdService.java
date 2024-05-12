package adeuxpas.back.service;

import adeuxpas.back.dto.AdHomeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;
import java.util.List;

public interface AdService {
    Page<AdHomeResponseDTO> findAllAdResponseDTOs(Pageable pageable);

    Page<AdHomeResponseDTO> findFilteredAdResponseDTOs(List<String> prices, List<String> cities, List<String> articleStates, String category, Pageable pageable);

    AdPostResponseDTO postAd(AdPostRequestDTO adDto);

    AdPostResponseDTO findAdById(Long adId);

    List<AdPostResponseDTO> findAdsByPublisherId(Long publisherId);

}
