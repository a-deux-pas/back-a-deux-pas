
package adeuxpas.back.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
import adeuxpas.back.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    /**
     * Test for postAd method in AdServiceImpl.
     */
    @Test
    void testPostAd() {
        // Mock Dto data
        AdPostRequestDTO adDto = new AdPostRequestDTO();
        adDto.setTitle("Test Ad");
        adDto.setArticleDescription("Test description");
        adDto.setCreationDate(LocalDateTime.now().toString());
        adDto.setPrice(BigDecimal.valueOf(100));
        adDto.setCategory("Test Category");
        adDto.setSubcategory("Test Subcategory");
        adDto.setArticleGender("Test Gender");
        adDto.setPublisherId(1L);
        adDto.setArticleState("Test State");

        List<ArticlePictureDTO> articlePictures = new ArrayList<>();
        ArticlePictureDTO singleAdPic = new ArticlePictureDTO();
        singleAdPic.setUrl("https://www.pexels.com/fr-fr/photo/mer-vacances-femme-ocean-16953646/");
        articlePictures.add(singleAdPic);
        adDto.setArticlePictures(articlePictures);

        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn((Optional.of(user)));

        Ad createdAd = adService.postAd(adDto);

        assertEquals(adDto.getTitle(), createdAd.getTitle());
        assertEquals(adDto.getArticleDescription(), createdAd.getArticleDescription());
        assertEquals(AdStatus.AVAILABLE, createdAd.getStatus());
        assertNotNull(createdAd.getArticlePictures());
    }
}
