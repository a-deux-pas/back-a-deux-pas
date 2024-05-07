package adeuxpas.back.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import adeuxpas.back.dto.AdPostResponseDTO;
import adeuxpas.back.dto.mapper.AdMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdRepository adRepository;

    @Mock
    private AdMapper adMapper;

    @InjectMocks
    private AdServiceImpl adService;

    /**
     * Test for postAd method in AdServiceImpl.
     */
    @Test
    void testPostAd() {
        // Mock Dto data
        AdPostRequestDTO adPostRequestDTO = new AdPostRequestDTO();
        adPostRequestDTO.setTitle("Test Ad");
        adPostRequestDTO.setArticleDescription("Test description");
        adPostRequestDTO.setCreationDate(LocalDateTime.now().toString());
        adPostRequestDTO.setPrice(BigDecimal.valueOf(100));
        adPostRequestDTO.setCategory("Test Category");
        adPostRequestDTO.setSubcategory("Test Subcategory");
        adPostRequestDTO.setArticleGender("Test Gender");
        adPostRequestDTO.setPublisherId(1L);
        adPostRequestDTO.setArticleState("Test State");

        List<ArticlePictureDTO> articlePictures = new ArrayList<>();
        ArticlePictureDTO singleAdPic = new ArticlePictureDTO();
        singleAdPic.setUrl("https://www.pexels.com/fr-fr/photo/mer-vacances-femme-ocean-16953646/");
        articlePictures.add(singleAdPic);
        adPostRequestDTO.setArticlePictures(articlePictures);

        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn((Optional.of(user)));

        Ad expectedAd = new Ad();
        expectedAd.setTitle(adPostRequestDTO.getTitle());
        expectedAd.setArticleDescription(adPostRequestDTO.getArticleDescription());
        expectedAd.setPrice(adPostRequestDTO.getPrice());
        expectedAd.setCategory(adPostRequestDTO.getCategory());
        expectedAd.setSubcategory(adPostRequestDTO.getSubcategory());
        expectedAd.setArticleGender(adPostRequestDTO.getArticleGender());
        expectedAd.setPublisher(user);
        expectedAd.setArticleState(adPostRequestDTO.getArticleState());
        expectedAd.setStatus(AdStatus.AVAILABLE);

        when(adRepository.save(any(Ad.class))).thenReturn(expectedAd);

        AdPostResponseDTO adResponse = new AdPostResponseDTO();
        adResponse.setTitle(expectedAd.getTitle());
        adResponse.setArticleDescription(expectedAd.getArticleDescription());
        adResponse.setPrice(expectedAd.getPrice());
        adResponse.setPublisher(expectedAd.getPublisher().getAlias());
        adResponse.setArticleState(expectedAd.getArticleState());
        adResponse.setStatus(expectedAd.getStatus());

        when(adMapper.adToAdPostResponseDTO(any(Ad.class))).thenReturn(adResponse);

        AdPostResponseDTO result = this.adService.postAd(adPostRequestDTO);

        assertEquals(result.getTitle(), adResponse.getTitle());
        assertEquals(result.getArticleDescription(), adResponse.getArticleDescription());
        assertEquals(result.getStatus(), adResponse.getStatus());
    }
}
