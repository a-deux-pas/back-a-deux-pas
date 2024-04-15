package adeuxpas.back.service;

import adeuxpas.back.dto.AdResponseDTO;
import adeuxpas.back.dto.mapper.MapStructMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Mock
    private AdRepository adRepositoryMock;
    @Mock
    private MapStructMapper mapper;
    @InjectMocks
    private AdServiceImpl adService;

    @BeforeEach
    public void setUp() {
        // Arrange
        adService = new AdServiceImpl(adRepositoryMock, mapper);
        adsList = this.createMockAdsSorted();
        adsPage = new PageImpl<>(adsList);
        pageable = PageRequest.of(0, 8);
        priceRanges = List.of("< 10€", "10€ - 20€", "20€ - 30€", "30€ - 40€", "40€ - 60€", "> 60€");
        citiesAndPostalCodes = List.of((adsList.get(0).getPublisher().getCity() + " (" + adsList.get(0).getPublisher().getPostalCode() + ")"),
                (adsList.get(1).getPublisher().getCity() + " (" + adsList.get(1).getPublisher().getPostalCode() + ")"));
        articleStates = List.of("Neuf avec étiquette", "Neuf sans étiquette", "Très bon état", "Bon état", "Satisfaisant");
    }

    @Test
    public void testFindFilteredResponseAdDTOs_NoFiltersApplied() {
        // Additional set-up
        this.mockRepositoryFindByAcceptedStatusesMethod();
        this.mockMapperBehaviour();

        // Act
        Page<AdResponseDTO> result = adService.findFilteredAdResponseDTOs(new ArrayList<>(), new ArrayList<>(),
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
                 // that the returned result is not null
        assertNotNull(result);
                 // that the returned page contains all the Ads
        assertEquals(adsList.size(), result.getContent().size());
                 // descending order by creation date
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }

    @Test
    public void testFindFilteredResponseAdDTOs_AllFiltersExceptCategoryApplied() {
        // Additional set-up
        this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();
        this.mockMapperBehaviour();

        // Act
        Page<AdResponseDTO> result = adService.findFilteredAdResponseDTOs(priceRanges, citiesAndPostalCodes,
                articleStates, "Catégorie", pageable);

        // Assert:
                 // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                anyList(), anyList(),
                any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(),
                any(), any(), any(),
                anyList(), anyList(),
                any()
        );
                 // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());

                 // that the returned result is not null
        assertNotNull(result);
                 // that the returned page contains all Ads
        assertEquals(adsList.size(), result.getContent().size());
                 // descending order by creation date
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }

    @Test
    public void testFindFilteredResponseAdDTOs_CategoryAndSingleCriteriaForEachFilterApplied() {
        // Additional set-up
        this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();
        this.mockMapperBehaviour();

        // Act
        Page<AdResponseDTO> result = adService.findFilteredAdResponseDTOs(List.of(priceRanges.getFirst()), List.of(citiesAndPostalCodes.getFirst()),
                List.of(articleStates.getFirst()), "Mode", pageable);

        // Assert:
        // that this method was called inside the tested filter method
        verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                anyList(), anyList(),
                any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(),
                any(), any(), any(),
                anyList(), anyList(),
                any()
        );
        // that this method was never called
        verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any());

        // that the returned result is not null
        assertNotNull(result);
        // that the returned page contains only Ads with a price < 10, that belong to the Fashion category and are new (with price tag)
        List<Ad> filteredAds = adsList.stream().
                filter(ad -> ad.getPrice().compareTo(BigDecimal.TEN) < 0).
                filter(ad -> ad.getCategory().equals("Mode")).
                filter(ad -> ad.getArticleState().equals("Neuf avec étiquette"))
                .toList();
        assertEquals(filteredAds.size(), result.getContent().size());
        // that the returned page doesn't contain any Ads with a price > 9
        assertTrue(result.stream().filter(ad -> ad.getPrice().compareTo(BigDecimal.valueOf(9)) > 0).toList().isEmpty());
        // descending order by creation date
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }

    // Mock behavior for adRepository methods
    private void mockRepositoryFindByAcceptedStatusesMethod() {
        when(adRepositoryMock.findByAcceptedStatusesOrderedByCreationDateDesc(any(), any(), any())).
                thenReturn(this.adsPage);
    }
    private void mockRepositoryFindByFiltersAndAcceptedStatusesMethod(){
        when(adRepositoryMock.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(anyList(), anyList(),
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
                anyList(), anyList(), any())).
                thenReturn(this.adsPage);
    }


    // Mock behavior for mapper
    private void mockMapperBehaviour() {
        when(mapper.adToAdResponseDTO(any(Ad.class))).thenAnswer(invocation -> {
            Ad ad = invocation.getArgument(0);
            AdResponseDTO adResponseDTO = new AdResponseDTO();
            adResponseDTO.setTitle(ad.getTitle());
            adResponseDTO.setPrice(ad.getPrice());
            adResponseDTO.setPublisher(ad.getPublisher().getAlias());
            adResponseDTO.setCreationDate(ad.getCreationDate());
            return adResponseDTO;
        });
    }

    /*@Test
    public void testFindFilteredResponseAdDTOs_NoFiltersApplied_ReturnsAllAvailableAdsWithActiveAccounts() {
        // Arrange
        List<Ad> mockAds = createMockAdsSorted();
        Page<Ad> mockPage = new PageImpl<>(mockAds);

        // set up the behaviour of the adRepo method to return a page of mock ads
        when(adRepositoryMock.findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any(Pageable.class)))
                .thenReturn(mockPage);

        // Act
        Page<AdResponseDTO> result = adService.findFilteredAdResponseDTOs(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "Catégorie", pageableMock);

        // Assert
        assertEquals(mockAds.size(), result.getContent().size());
        assertEquals(mockAds.get(0).getTitle(), result.getContent().get(0).getTitle());
        assertEquals(mockAds.get(1).getTitle(), result.getContent().get(1).getTitle());
        // Ensure descending order by creation date
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }*/

    private List<Ad> createMockAdsSorted() {
        List<Ad> ads = new ArrayList<>();

        User firstUser = new User();
        firstUser.setAccountStatus(AccountStatus.ACTIVE);
        firstUser.setPostalCode("75000");
        firstUser.setCity("Paris");
        
        User secondUser = new User();
        secondUser.setAccountStatus(AccountStatus.SUSPENDED);
        secondUser.setPostalCode("69000");
        secondUser.setCity("Lyon");

        Ad firstAd = new Ad();
        firstAd.setTitle("First available ad with active account");
        firstAd.setPrice(BigDecimal.ONE);
        firstAd.setArticleState("Neuf avec étiquette");
        firstAd.setStatus(AdStatus.AVAILABLE);
        firstAd.setPublisher(firstUser);
        firstAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
        firstAd.setCategory("Autre");
        firstAd.setSubcategory("Autre");

        Ad secondAd = new Ad();
        secondAd.setTitle("Second available ad with active account");
        secondAd.setPrice(BigDecimal.valueOf(11));
        secondAd.setArticleState("Neuf sans étiquette");
        secondAd.setStatus(AdStatus.AVAILABLE);
        secondAd.setPublisher(firstUser);
        secondAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        secondAd.setCategory("Maison");
        secondAd.setSubcategory("Jardin");

        Ad thirdAd = new Ad();
        thirdAd.setTitle("Third available ad with active account");
        thirdAd.setPrice(BigDecimal.ONE);
        thirdAd.setArticleState("Neuf avec étiquette");
        thirdAd.setStatus(AdStatus.AVAILABLE);
        thirdAd.setPublisher(secondUser);
        thirdAd.setCreationDate(LocalDateTime.now().plusMinutes(2));
        thirdAd.setCategory("Mode");
        thirdAd.setSubcategory("Hauts");
        thirdAd.setArticleGender("Homme");


        Ad fourthAd = new Ad();
        fourthAd.setTitle("Fourth available ad with active account");
        fourthAd.setPrice(BigDecimal.TWO);
        fourthAd.setArticleState("Satisfaisant");
        fourthAd.setStatus(AdStatus.AVAILABLE);
        fourthAd.setPublisher(secondUser);
        fourthAd.setCreationDate(LocalDateTime.now());
        fourthAd.setCategory("Mode");
        fourthAd.setSubcategory("Hauts");
        fourthAd.setArticleGender("Femme");

        ads.add(firstAd);
        ads.add(secondAd);
        ads.add(thirdAd);
        ads.add(fourthAd);

        ads.sort((a, b) -> {
            if (a.getCreationDate().isAfter(b.getCreationDate())) return -1;
            if (a.getCreationDate().isBefore(b.getCreationDate())) return 1;
            return 0;
        });

        return ads;
    }
}
