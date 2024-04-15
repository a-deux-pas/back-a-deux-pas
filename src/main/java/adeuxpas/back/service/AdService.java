package adeuxpas.back.service;

import adeuxpas.back.dto.AdResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdService {
    Page<AdResponseDTO> findAllAdResponseDTOs(Pageable pageable);
    Page<AdResponseDTO> findFilteredAdResponseDTOs(List<String> prices, List<String> cities, List<String> articleStates, String category, Pageable pageable);
}
