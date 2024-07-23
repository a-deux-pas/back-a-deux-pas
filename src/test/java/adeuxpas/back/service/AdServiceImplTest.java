package adeuxpas.back.service;

import static org.mockito.ArgumentMatchers.any;

import adeuxpas.back.dto.mapper.AdMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UserRepository;
import adeuxpas.back.repository.UsersFavoriteAdsRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import adeuxpas.back.dto.AdCardResponseDTO;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;
import adeuxpas.back.utils.UnitTestUtils;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Map;

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
        private final List<String> articleStates = List.of("Neuf avec étiquette", "Neuf sans étiquette",
                        "Très bon état",
                        "Bon état", "Satisfaisant");
        private final List<String> citiesAndPostalCodes = new ArrayList<>();
        private int pageNumber = 0;
        private int pageSize = 8;
        private final Pageable pageable = PageRequest.of(pageNumber, pageSize);

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
        @Mock
        private UsersFavoriteAdsRepository favoriteRepositoryMock;
        @Mock
        private CloudinaryService cloudinaryService;

        /**
         * This method tests the
         * {@link AdServiceImpl#findFilteredAdCardResponseDTOs(List, List, List, String, Long, int, int)}
         * method with no filters applied.
         * </p>
         */
        @Test
        void testFindFilteredAdCardResponseDTOs_NoFiltersApplied() {
                // Set-up
                this.setUp();
                this.mockRepositoryFindByAcceptedStatusesMethod();

                // Act
                adService.findFilteredAdCardResponseDTOs(new ArrayList<>(), new ArrayList<>(),
                                new ArrayList<>(), "Catégorie", null, pageNumber, pageSize);

                // Assert :
                // that this method was called by the repository mock inside the tested filter
                // method
                verify(adRepositoryMock).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(), any(),
                                any());
                // that this method was never called
                verify(adRepositoryMock, times(0)).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                                anyList(), anyList(),
                                any(), any(), any(), any(), any(), any(),
                                any(), any(), any(), any(),
                                any(), any(), any(),
                                anyList(), anyList(),
                                any(),
                                any());
        }

        /**
         * This method tests the
         * {@link AdServiceImpl#findFilteredAdCardResponseDTOs(List, List, List, String, int, int)}
         * method with all filters applied, except category.
         * </p>
         */
        @Test
        void testFindFilteredAdCardResponseDTOs_AllFiltersExceptCategoryApplied() {
                // Set-up
                this.setUp();
                this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

                // Act
                adService.findFilteredAdCardResponseDTOs(priceRanges, citiesAndPostalCodes,
                                articleStates, "Catégorie", null, pageNumber, pageSize);

                // Assert:
                // that this method was called inside the tested filter method
                verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(),
                                minPrice3Captor.capture(), maxPrice3Captor.capture(),
                                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(),
                                maxPrice5Captor.capture(), minPrice6Captor.capture(),
                                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                                anyList(), anyList(), any(), any());
                // that this method was never called
                verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(),
                                any(),
                                any());
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
                assertTrue(postalCodesCaptor.getValue()
                                .containsAll(List.of(this.adsList.get(0).getPublisher().getPostalCode(),
                                                this.adsList.get(1).getPublisher().getPostalCode())));
        }

        /**
         * This method tests the
         * {@link AdServiceImpl#findFilteredAdCardResponseDTOs(List, List, List, String, int, int)}
         * method with all filters, including category, applied.
         * </p>
         */
        @Test
        void testFindFilteredAdCardResponseDTOs_CategoryAndCriteriaForEachFilterApplied() {
                // Set-up
                this.setUp();
                mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

                // Act
                adService.findFilteredAdCardResponseDTOs(List.of(priceRanges.getFirst()),
                                List.of(citiesAndPostalCodes.getFirst()),
                                List.of(articleStates.getFirst()), "Mode", null, pageNumber, pageSize);

                // Assert:
                // that this method was called inside the tested filter method
                verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(),
                                minPrice3Captor.capture(), maxPrice3Captor.capture(),
                                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(),
                                maxPrice5Captor.capture(), minPrice6Captor.capture(),
                                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                                anyList(), anyList(), any(), any());
                // that this method was never called
                verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(),
                                any(),
                                any());
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
                assertEquals(List.of(this.adsList.getFirst().getPublisher().getPostalCode()),
                                postalCodesCaptor.getValue());
                assertEquals(List.of(this.articleStates.getFirst()), articleStatesCaptor.getValue());
                assertEquals("Mode", categoryCaptor.getValue());
                assertNull(subCategoryCaptor.getValue());
                assertNull(genderCaptor.getValue());
        }

        /**
         * This method tests the
         * {@link AdServiceImpl#findFilteredAdCardResponseDTOs(List, List, List, String, int, int)}
         * method with all filters, including category, subcategory and gender, applied.
         * </p>
         */
        @Test
        void testFindFilteredAdCardResponseDTOs_CategorySubcategoryGenderAndCriteriaForEachFilterApplied() {
                // Set-up
                this.setUp();
                this.mockRepositoryFindByFiltersAndAcceptedStatusesMethod();

                // Act
                adService.findFilteredAdCardResponseDTOs(List.of(priceRanges.getFirst()),
                                List.of(citiesAndPostalCodes.getFirst()),
                                List.of(articleStates.getFirst()), "Mode ▸ Hauts ▸ Homme", null, pageNumber, pageSize);

                // Assert:
                // that this method was called inside the tested filter method
                verify(adRepositoryMock).findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                                postalCodesCaptor.capture(), articleStatesCaptor.capture(),
                                maxPrice1Captor.capture(), minPrice2Captor.capture(), maxPrice2Captor.capture(),
                                minPrice3Captor.capture(), maxPrice3Captor.capture(),
                                minPrice4Captor.capture(), maxPrice4Captor.capture(), minPrice5Captor.capture(),
                                maxPrice5Captor.capture(), minPrice6Captor.capture(),
                                categoryCaptor.capture(), subCategoryCaptor.capture(), genderCaptor.capture(),
                                anyList(), anyList(), any(), any());
                // that this method was never called
                verify(adRepositoryMock, times(0)).findByAcceptedStatusesOrderedByCreationDateDesc(anyList(), anyList(),
                                any(),
                                any());
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
                assertEquals(List.of(this.adsList.getFirst().getPublisher().getPostalCode()),
                                postalCodesCaptor.getValue());
                assertEquals(List.of(this.articleStates.getFirst()), articleStatesCaptor.getValue());
                assertEquals("Mode", categoryCaptor.getValue());
                assertEquals("Hauts", subCategoryCaptor.getValue());
                assertEquals("Homme", genderCaptor.getValue());
        }

        /**
         * Test for postAd method in AdServiceImpl.
         */
        @Test
        void testPostAd() throws IOException {
                // Mock DTO data
                AdPostRequestDTO adPostRequestDTO = new AdPostRequestDTO();
                adPostRequestDTO.setTitle("Test Ad");
                adPostRequestDTO.setArticleDescription("Test description");
                adPostRequestDTO.setPrice(BigDecimal.valueOf(100));
                adPostRequestDTO.setCategory("Test Category");
                adPostRequestDTO.setSubcategory("Test Subcategory");
                adPostRequestDTO.setArticleGender("Test Gender");
                adPostRequestDTO.setPublisherId(1L);
                adPostRequestDTO.setArticleState("Test State");

                // Simulate a MultipartFile
                MultipartFile singleAdPic = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test".getBytes());
                List<MultipartFile> articlePictures = new ArrayList<>();
                articlePictures.add(singleAdPic);

                // Mock User
                User user = new User();
                user.setId(1L);
                when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(user));

                // Mock Cloudinary upload method
                Map<String, Object> cloudinaryResponse = new HashMap<>();
                cloudinaryResponse.put("url", "https://example.com/image.webp");
                when(cloudinaryService.upload(any(String.class), any(MultipartFile.class)))
                                .thenReturn(cloudinaryResponse);

                // Mock AdMapper
                Ad newAd = new Ad();
                newAd.setTitle(adPostRequestDTO.getTitle());
                newAd.setArticleDescription(adPostRequestDTO.getArticleDescription());
                newAd.setPrice(adPostRequestDTO.getPrice());
                newAd.setCategory(adPostRequestDTO.getCategory());
                newAd.setSubcategory(adPostRequestDTO.getSubcategory());
                newAd.setArticleGender(adPostRequestDTO.getArticleGender());
                newAd.setPublisher(user);
                newAd.setArticleState(adPostRequestDTO.getArticleState());
                newAd.setStatus(AdStatus.AVAILABLE);

                when(adMapperMock.adPostRequestDTOToAd(adPostRequestDTO)).thenReturn(newAd);

                Ad savedAd = newAd; // Assuming the savedAd is the same as newAd for simplicity
                when(adRepositoryMock.save(any(Ad.class))).thenReturn(savedAd);

                AdPostResponseDTO adResponse = new AdPostResponseDTO();
                adResponse.setTitle(newAd.getTitle());
                adResponse.setArticleDescription(newAd.getArticleDescription());
                adResponse.setPrice(newAd.getPrice());
                adResponse.setPublisherId(newAd.getPublisher().getId());
                adResponse.setArticleState(newAd.getArticleState());
                adResponse.setStatus(newAd.getStatus());

                when(adMapperMock.adToAdPostResponseDTO(any(Ad.class))).thenReturn(adResponse);

                // Call the method
                AdPostResponseDTO result = adService.postAd(adPostRequestDTO, articlePictures);

                // Verify results
                assertEquals(adResponse.getTitle(), result.getTitle());
                assertEquals(adResponse.getArticleDescription(), result.getArticleDescription());
                assertEquals(adResponse.getPrice(), result.getPrice());
                assertEquals(adResponse.getPublisherId(), result.getPublisherId());
                assertEquals(adResponse.getArticleState(), result.getArticleState());
                assertEquals(adResponse.getStatus(), result.getStatus());

                // Verify interactions with mocks
                verify(userRepositoryMock).findById(adPostRequestDTO.getPublisherId());
                verify(adRepositoryMock).save(any(Ad.class));
                verify(adMapperMock).adToAdPostResponseDTO(any(Ad.class));
                verify(cloudinaryService, times(articlePictures.size())).upload(any(String.class),
                                any(MultipartFile.class));
        }

        /**
         * Test for findAdsByPublisher method in AdServiceImpl.
         */
        @Test
        void testFindPageOfUserAdsListIfUserExists() {
                Long publisherId = 1L;
                User user = new User();
                Long loggedInUserId = 1L;
                Long adId = 1L;
                when(userRepositoryMock.findById(publisherId)).thenReturn(Optional.of(user));

                Ad ad1 = new Ad();
                Ad ad2 = new Ad();
                List<Ad> adList = List.of(ad1, ad2);
                adsPage = new PageImpl<>(adList);
                when(adRepositoryMock.findAvailableAdsByPublisherId(publisherId, pageable, adId))
                                .thenReturn(adsPage);

                AdCardResponseDTO dto1 = new AdCardResponseDTO();
                AdCardResponseDTO dto2 = new AdCardResponseDTO();
                when(adMapperMock.adToAdCardResponseDTO(ad1)).thenReturn(dto1);
                when(adMapperMock.adToAdCardResponseDTO(ad2)).thenReturn(dto2);

                Page<AdCardResponseDTO> result = adService.findPageOfUserAdsList(publisherId,
                                pageNumber, pageSize, loggedInUserId, adId);

                assertEquals(2, result.getNumberOfElements());
                assertEquals(dto1, result.getContent().get(0));
                assertEquals(dto2, result.getContent().get(1));

                verify(userRepositoryMock).findById(publisherId);
                verify(adRepositoryMock).findAvailableAdsByPublisherId(publisherId,
                                pageable, adId);
        }

        // common set-up, used by several test methods
        private void setUp() {
                adsList = UnitTestUtils.createMockAds();
                adsPage = new PageImpl<>(adsList);
                adsList.forEach(ad -> {
                        citiesAndPostalCodes.add(
                                        ad.getPublisher().getCity() + " (" + ad.getPublisher().getPostalCode() + ")");
                });
                this.mockAdMapperBehaviour();
        }

        // Mock behavior for adRepository methods
        private void mockRepositoryFindByAcceptedStatusesMethod() {
                when(adRepositoryMock.findByAcceptedStatusesOrderedByCreationDateDesc(any(), any(), any(), any()))
                                .thenReturn(this.adsPage);
        }

        private void mockRepositoryFindByFiltersAndAcceptedStatusesMethod() {
                when(adRepositoryMock.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(anyList(), anyList(),
                                any(), any(), any(), any(), any(),
                                any(), any(), any(), any(), any(),
                                any(), any(), any(),
                                anyList(), anyList(), any(), any())).thenReturn(this.adsPage);
        }

        // Mock behavior for mapper
        private void mockAdMapperBehaviour() {
                when(adMapperMock.adToAdCardResponseDTO(any(Ad.class))).thenAnswer(invocation -> {
                        Ad ad = invocation.getArgument(0);
                        AdCardResponseDTO adCardResponseDTO = new AdCardResponseDTO();
                        adCardResponseDTO.setTitle(ad.getTitle());
                        adCardResponseDTO.setPrice(ad.getPrice());
                        adCardResponseDTO.setPublisherAlias(ad.getPublisher().getAlias());
                        return adCardResponseDTO;
                });
        }

}
