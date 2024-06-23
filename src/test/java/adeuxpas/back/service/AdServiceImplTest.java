package adeuxpas.back.service;

import static org.mockito.ArgumentMatchers.any;

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

import adeuxpas.back.dto.AdHomeResponseDTO;
import adeuxpas.back.utils.UnitTestUtils;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Test class for AdServiceImpl.
 * <p>
 * This class tests the functionalities of the AdServiceImpl class.
 * It uses Mockito for mocking dependencies and runtime parameter access.
 * </p>
 *
 */
@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {
    private List<Ad> adsList;
    private Page<Ad> adsPage;
    private final List<String> priceRanges = List.of("< 10€", "10€ - 20€", "20€ - 30€", "30€ - 40€", "40€ - 60€",
            "> 60€");
    private final List<String> articleStates = List.of("Neuf avec étiquette", "Neuf sans étiquette", "Très bon état",
            "Bon état", "Satisfaisant");
    private final List<String> citiesAndPostalCodes = new ArrayList<>();
    private final Pageable pageable = PageRequest.of(0, 8);

    @Captor
    private ArgumentCaptor<List<String>> postalCodesCaptor;
    @Captor
    private ArgumentCaptor<List<String>> articleStatesCaptor;
    @Captor
    private ArgumentCaptor<BigDecimal> maxPrice1Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> minPrice2Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> maxPrice2Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> minPrice3Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> maxPrice3Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> minPrice4Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> maxPrice4Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> minPrice5Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> maxPrice5Captor;
    @Captor
    private ArgumentCaptor<BigDecimal> minPrice6Captor;
    @Captor
    private ArgumentCaptor<String> categoryCaptor;
    @Captor
    private ArgumentCaptor<String> subCategoryCaptor;
    @Captor
    private ArgumentCaptor<String> genderCaptor;

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AdRepository adRepositoryMock;
    @Mock
    private AdMapper adMapperMock;
    @InjectMocks
    private AdServiceImpl adService;

    /**
     * This method tests the
     * {@link AdServiceImpl#findFilteredAdHomeResponseDTOs(List, List, List, String, Pageable)}
     * method with no filters applied.
     * </p>
     */
    @Test
    void testFindFilteredAdHomeResponseDTOs_NoFiltersApplied() {
        // Set-up
        this.setUp();
        this.mockRepositoryFindByAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdHomeResponseDTOs(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), "Catégorie", pageable);

        // Assert :
        // that this method was called by the repository mock inside the tested filter
        // method
        verify(adRepositoryMock).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
        // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                anyList(), anyList(),
                any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(),
                any(), any(), any(),
                anyList(), anyList(),
                any());
    }

    /**
     * This method tests the
     * {@link AdServiceImpl#findFilteredAdHomeResponseDTOs(List, List, List, String, Pageable)}
     * method with all filters applied, except category.
     * </p>
     */
    @Test
    void testFindFilteredAdHomeResponseDTOs_AllFiltersExceptCategoryApplied() {
        // Set-up
        this.setUp();
        this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdHomeResponseDTOs(priceRanges, citiesAndPostalCodes,
                articleStates, "Catégorie", pageable);

        // Assert:
        // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(),
                minPrice3Captor.capture(), maxPrice3Captor.capture(),
                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(),
                maxPrice5Captor.capture(), minPrice6Captor.capture(),
                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                anyList(), anyList(), any());
        // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
        // that all the parameters passed to the repository method are correctly
        // formatted
        // using Captors to capture and check the params' values at runtime
        assertNull(categoryCaptor.getValue());
        assertNull(subCategoryCaptor.getValue());
        assertNull(genderCaptor.getValue());
        assertEquals(BigDecimal.TEN, maxPrice1Captor.getValue());
        assertEquals(BigDecimal.TEN, minPrice2Captor.getValue());
        assertEquals(BigDecimal.valueOf(19), maxPrice2Captor.getValue());
        assertEquals(BigDecimal.valueOf(20), minPrice3Captor.getValue());
        assertEquals(BigDecimal.valueOf(29), maxPrice3Captor.getValue());
        assertEquals(BigDecimal.valueOf(30), minPrice4Captor.getValue());
        assertEquals(BigDecimal.valueOf(39), maxPrice4Captor.getValue());
        assertEquals(BigDecimal.valueOf(40), minPrice5Captor.getValue());
        assertEquals(BigDecimal.valueOf(59), maxPrice5Captor.getValue());
        assertEquals(BigDecimal.valueOf(60), minPrice6Captor.getValue());
        assertEquals(this.articleStates, articleStatesCaptor.getValue());
        assertTrue(postalCodesCaptor.getValue().containsAll(List.of(this.adsList.get(0).getPublisher().getPostalCode(),
                this.adsList.get(1).getPublisher().getPostalCode())));
    }

    /**
     * This method tests the
     * {@link AdServiceImpl#findFilteredAdHomeResponseDTOs(List, List, List, String, Pageable)}
     * method with all filters, including category, applied.
     * </p>
     */
    @Test
    void testFindFilteredAdHomeResponseDTOs_CategoryAndCriteriaForEachFilterApplied() {
        // Set-up
        this.setUp();
        mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdHomeResponseDTOs(List.of(priceRanges.getFirst()),
                List.of(citiesAndPostalCodes.getFirst()),
                List.of(articleStates.getFirst()), "Mode", pageable);

        // Assert:
        // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(),
                minPrice3Captor.capture(), maxPrice3Captor.capture(),
                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(),
                maxPrice5Captor.capture(), minPrice6Captor.capture(),
                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                anyList(), anyList(), any());
        // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
        // that all the parameters passed to the repository method are correctly
        // formatted
        assertEquals(BigDecimal.TEN, maxPrice1Captor.getValue());
        assertNull(minPrice2Captor.getValue());
        assertNull(maxPrice2Captor.getValue());
        assertNull(minPrice3Captor.getValue());
        assertNull(maxPrice3Captor.getValue());
        assertNull(minPrice4Captor.getValue());
        assertNull(maxPrice4Captor.getValue());
        assertNull(minPrice5Captor.getValue());
        assertNull(maxPrice5Captor.getValue());
        assertNull(minPrice6Captor.getValue());
        assertEquals(List.of(this.adsList.getFirst().getPublisher().getPostalCode()), postalCodesCaptor.getValue());
        assertEquals(List.of(this.articleStates.getFirst()), articleStatesCaptor.getValue());
        assertEquals("Mode", categoryCaptor.getValue());
        assertNull(subCategoryCaptor.getValue());
        assertNull(genderCaptor.getValue());
    }

    /**
     * This method tests the
     * {@link AdServiceImpl#findFilteredAdHomeResponseDTOs(List, List, List, String, Pageable)}
     * method with all filters, including category, subcategory and gender, applied.
     * </p>
     */
    @Test
    void testFindFilteredAdHomeResponseDTOs_CategorySubcategoryGenderAndCriteriaForEachFilterApplied() {
        // Set-up
        this.setUp();
        this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdHomeResponseDTOs(List.of(priceRanges.getFirst()),
                List.of(citiesAndPostalCodes.getFirst()),
                List.of(articleStates.getFirst()), "Mode ▸ Hauts ▸ Homme", pageable);

        // Assert:
        // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(),
                minPrice3Captor.capture(), maxPrice3Captor.capture(),
                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(),
                maxPrice5Captor.capture(), minPrice6Captor.capture(),
                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                anyList(), anyList(), any());
        // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
        // that all the parameters passed to the repository method are correctly
        // formatted
        assertEquals(BigDecimal.TEN, maxPrice1Captor.getValue());
        assertNull(minPrice2Captor.getValue());
        assertNull(maxPrice2Captor.getValue());
        assertNull(minPrice3Captor.getValue());
        assertNull(maxPrice3Captor.getValue());
        assertNull(minPrice4Captor.getValue());
        assertNull(maxPrice4Captor.getValue());
        assertNull(minPrice5Captor.getValue());
        assertNull(maxPrice5Captor.getValue());
        assertNull(minPrice6Captor.getValue());
        assertEquals(List.of(this.adsList.getFirst().getPublisher().getPostalCode()), postalCodesCaptor.getValue());
        assertEquals(List.of(this.articleStates.getFirst()), articleStatesCaptor.getValue());
        assertEquals("Mode", categoryCaptor.getValue());
        assertEquals("Hauts", subCategoryCaptor.getValue());
        assertEquals("Homme", genderCaptor.getValue());
    }

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
        when(userRepositoryMock.findById(1L)).thenReturn((Optional.of(user)));

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

        when(adRepositoryMock.save(any(Ad.class))).thenReturn(expectedAd);

        AdPostResponseDTO adResponse = new AdPostResponseDTO();
        adResponse.setTitle(expectedAd.getTitle());
        adResponse.setArticleDescription(expectedAd.getArticleDescription());
        adResponse.setPrice(expectedAd.getPrice());
        adResponse.setPublisherId(expectedAd.getPublisher().getId());
        adResponse.setArticleState(expectedAd.getArticleState());
        adResponse.setStatus(expectedAd.getStatus());

        when(adMapperMock.adToAdPostResponseDTO(any(Ad.class))).thenReturn(adResponse);

        AdPostResponseDTO result = this.adService.postAd(adPostRequestDTO);

        assertEquals(result.getTitle(), adResponse.getTitle());
        assertEquals(result.getArticleDescription(), adResponse.getArticleDescription());
        assertEquals(result.getStatus(), adResponse.getStatus());
    }

    /**
     * Test for findAdsByPublisher method in AdServiceImpl.
     */
    @Test
    void testfindPageOfUserAdsListIfUserExists() {
        Long publisherId = 1L;
        User user = new User();
        when(userRepositoryMock.findById(publisherId)).thenReturn(Optional.of(user));

        Ad ad1 = new Ad();
        Ad ad2 = new Ad();
        List<Ad> adList = List.of(ad1, ad2);
        adsPage = new PageImpl<>(adList);
        when(adRepositoryMock.findAdsByPublisherIdOrderByCreationDateDesc(publisherId, pageable)).thenReturn(adsPage);

        AdPostResponseDTO dto1 = new AdPostResponseDTO();
        AdPostResponseDTO dto2 = new AdPostResponseDTO();
        when(adMapperMock.adToAdPostResponseDTO(ad1)).thenReturn(dto1);
        when(adMapperMock.adToAdPostResponseDTO(ad2)).thenReturn(dto2);

        Page<AdPostResponseDTO> result = adService.findPageOfUserAdsList(publisherId, pageable);

        assertEquals(2, result.getNumberOfElements());
        assertEquals(dto1, result.getContent().get(0));
        assertEquals(dto2, result.getContent().get(1));

        verify(userRepositoryMock).findById(publisherId);
        verify(adRepositoryMock).findAdsByPublisherIdOrderByCreationDateDesc(publisherId, pageable);
    }

    // common set-up, used by several test methods
    private void setUp() {
        adsList = UnitTestUtils.createMockAds();
        adsPage = new PageImpl<>(adsList);
        adsList.forEach(ad -> {
            citiesAndPostalCodes.add(ad.getPublisher().getCity() + " (" + ad.getPublisher().getPostalCode() + ")");
        });
        this.mockAdMapperBehaviour();
    }

    // Mock behavior for adRepository methods
    private void mockRepositoryFindByAcceptedStatusesMethod() {
        when(adRepositoryMock.findByAcceptedStatusesOrderedByCreationDateDesc(any(), any(), any()))
                .thenReturn(this.adsPage);
    }

    private void mockRepositoryFindByFiltersAndAcceptedStatusesMethod() {
        when(adRepositoryMock.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(anyList(), anyList(),
                any(), any(), any(), any(), any(),
                any(), any(), any(), any(), any(),
                any(), any(), any(),
                anyList(), anyList(), any())).thenReturn(this.adsPage);
    }

    // Mock behavior for mapper
    private void mockAdMapperBehaviour() {
        when(adMapperMock.adToAdHomeResponseDTO(any(Ad.class))).thenAnswer(invocation -> {
            Ad ad = invocation.getArgument(0);
            AdHomeResponseDTO adHomeResponseDTO = new AdHomeResponseDTO();
            adHomeResponseDTO.setTitle(ad.getTitle());
            adHomeResponseDTO.setPrice(ad.getPrice());
            adHomeResponseDTO.setPublisherAlias(ad.getPublisher().getAlias());
            return adHomeResponseDTO;
        });
    }

}
