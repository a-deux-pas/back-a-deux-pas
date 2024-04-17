package adeuxpas.back.service;

import adeuxpas.back.dto.AdResponseDTO;
import adeuxpas.back.dto.mapper.AdMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceImplTest {
    private List<Ad> adsList;
    private Page<Ad> adsPage;
    private Pageable pageable;
    private List<String> priceRanges;
    private List<String> citiesAndPostalCodes;
    private List<String> articleStates;

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
    private AdRepository adRepositoryMock;
    @Mock
    private AdMapper adMapperMock;
    @InjectMocks
    private AdServiceImpl adService;

    @BeforeEach
    public void setUp() {
        // Arrange
        adsList = TestUtils.createMockAds();
        adsPage = new PageImpl<>(adsList);
        pageable = PageRequest.of(0, 8);
        priceRanges = List.of("< 10€", "10€ - 20€", "20€ - 30€", "30€ - 40€", "40€ - 60€", "> 60€");
        citiesAndPostalCodes = List.of((adsList.get(0).getPublisher().getCity() + " (" + adsList.get(0).getPublisher().getPostalCode() + ")"),
                (adsList.get(1).getPublisher().getCity() + " (" + adsList.get(1).getPublisher().getPostalCode() + ")"));
        articleStates = List.of("Neuf avec étiquette", "Neuf sans étiquette", "Très bon état", "Bon état", "Satisfaisant");
        this.mockAdMapperBehaviour();
    }

    @Test
    public void testFindFilteredResponseAdDTOs_NoFiltersApplied() {
        // Additional set-up
        this.mockRepositoryFindByAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdResponseDTOs(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), "Catégorie", pageable);

        // Assert :
            // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
            // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                anyList(), anyList(),
                any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(),
                any(), any(), any(),
                anyList(), anyList(),
                any()
        );
    }

    @Test
    public void testFindFilteredResponseAdDTOs_AllFiltersExceptCategoryApplied() {
        // Additional set-up
        this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdResponseDTOs(priceRanges, citiesAndPostalCodes,
                articleStates, "Catégorie", pageable);

        // Assert:
            // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(), minPrice3Captor.capture(), maxPrice3Captor.capture(),
                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(), maxPrice5Captor.capture(), minPrice6Captor.capture(),
                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                anyList(), anyList(), any()
        );
            // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
            // that all the parameters passed to the repository method are correctly formatted
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

    @Test
    public void testFindFilteredResponseAdDTOs_CategoryAndCriteriaForEachFilterApplied() {
        // Additional set-up
        mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdResponseDTOs(List.of(priceRanges.getFirst()), List.of(citiesAndPostalCodes.getFirst()),
                List.of(articleStates.getFirst()), "Mode", pageable);

        // Assert:
            // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(), minPrice3Captor.capture(), maxPrice3Captor.capture(),
                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(), maxPrice5Captor.capture(), minPrice6Captor.capture(),
                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                anyList(), anyList(), any()
        );
            // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
            // that all the parameters passed to the repository method are correctly formatted
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

    @Test
    public void testFindFilteredResponseAdDTOs_CategorySubcategoryGenderAndCriteriaForEachFilterApplied() {
        // Additional set-up
        this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

        // Act
        adService.findFilteredAdResponseDTOs(List.of(priceRanges.getFirst()), List.of(citiesAndPostalCodes.getFirst()),
                List.of(articleStates.getFirst()), "Mode ▸ Hauts ▸ Homme", pageable);

        // Assert:
        // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(), minPrice3Captor.capture(), maxPrice3Captor.capture(),
                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(), maxPrice5Captor.capture(), minPrice6Captor.capture(),
                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                anyList(), anyList(), any()
        );
        // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());
        // that all the parameters passed to the repository method are correctly formatted
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

    // Mock behavior for adRepository methods
    private void mockRepositoryFindByAcceptedStatusesMethod() {
        when(adRepositoryMock.findByAcceptedStatusesOrderedByCreationDateDesc(any(), any(), any())).
                thenReturn(this.adsPage);
    }
    private void mockRepositoryFindByFiltersAndAcceptedStatusesMethod(){
        when(adRepositoryMock.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(anyList(), anyList(),
                any(), any(), any(), any(), any(),
                any(), any(), any(), any(), any(),
                any(), any(), any(),
                anyList(), anyList(), any())).
                thenReturn(this.adsPage);
    }

    // Mock behavior for mapper
    private void mockAdMapperBehaviour() {
        when(adMapperMock.adToAdResponseDTO(any(Ad.class))).thenAnswer(invocation -> {
            Ad ad = invocation.getArgument(0);
            AdResponseDTO adResponseDTO = new AdResponseDTO();
            adResponseDTO.setTitle(ad.getTitle());
            adResponseDTO.setPrice(ad.getPrice());
            adResponseDTO.setPublisher(ad.getPublisher().getAlias());
            return adResponseDTO;
        });
    }
}
