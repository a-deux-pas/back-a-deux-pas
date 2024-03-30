package adeuxpas.back.service;

import adeuxpas.back.dto.ResponseAdDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdService {

    Page<ResponseAdDTO> findAllResponseAdDTOs(Pageable pageable);

    Page<ResponseAdDTO> findFilteredResponseAdDTOs(List<String> prices, List<String> cities, List<String> articleStates, String category, Pageable pageable);
}
