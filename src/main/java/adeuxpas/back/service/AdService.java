package adeuxpas.back.service;

import adeuxpas.back.dto.AdResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdService {
    Page<AdResponseDTO> findFilteredResponseAdDTOs(List<String> prices, List<String> cities, List<String> articleStates, String category, Pageable pageable);
}
