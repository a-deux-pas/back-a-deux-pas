package adeuxpas.back.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import adeuxpas.back.dto.AdPostDto;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    /**
     * Creates a ad entity with a dto
     *
     * @return The ad entity.
     */
    @Test
    private void postAd(AdPostDto adDto) {
        // ... To implement
    }
}