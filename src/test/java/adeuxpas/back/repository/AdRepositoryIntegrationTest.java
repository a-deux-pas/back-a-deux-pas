package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.AdStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test") // Activates the 'test' profile, telling Spring to load the application-test.properties configuration
@SpringBootTest
@Transactional // Ensures each test runs within a transaction, which is rolled back after the test
public class AdRepositoryIntegrationTest {
    private final List<String> postalCodes = List.of("94700", "75000", "75018", "69000", "66000");
    private final List <String> articleStates =  List.of("Neuf avec étiquette", "Neuf sans étiquette", "Très bon état", "Bon état", "Satisfaisant");
    private final List<AdStatus> acceptedAdStatuses = List.of(AdStatus.AVAILABLE);
    private final List<AccountStatus> acceptedAccountStatuses = List.of(AccountStatus.ACTIVE, AccountStatus.REPORTED);

    @Autowired
    AdRepository adRepository;

    @Test
    public void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_allFiltersExceptCategoryApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19), BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59), BigDecimal.valueOf(60), null, null, null,
                acceptedAdStatuses, acceptedAccountStatuses, PageRequest.of(0, 8)
        );

        // Assert
            // that the result is not null
        assertNotNull(result);
            // that the result is not empty
        assertFalse(result.isEmpty());
            // that the result contains all the available ads from the DB
        assertEquals(17, result.getTotalElements());
            // that the first page displays only the requested number of ads
        assertEquals(8, result.getContent().size());
            // sorting in descending order by creation date
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }

    @Test
    public void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_filtersWithCategoryApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19), BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59), BigDecimal.valueOf(60),
                "Électronique", null, null,
                acceptedAdStatuses, acceptedAccountStatuses, PageRequest.of(0, 8)
        );

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals("Phone case",result.getContent().getFirst().getTitle());
        assertEquals("Électronique",result.getContent().getFirst().getCategory());
    }

    @Test
    public void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_filtersWithCategoryAndSubcategoryApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19), BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59), BigDecimal.valueOf(60),
                "Mode", "Hauts", null,
                acceptedAdStatuses, acceptedAccountStatuses, PageRequest.of(0, 8)
        );

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("Mode",result.getContent().getFirst().getCategory());
        assertEquals("Hauts",result.getContent().getFirst().getSubcategory());
        assertEquals("Mode",result.getContent().getLast().getCategory());
        assertEquals("Hauts",result.getContent().getLast().getSubcategory());
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }

    @Test
    public void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_filtersWithCategorySubcategoryAndGenderApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19), BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59), BigDecimal.valueOf(60),
                "Mode", "Hauts", "Homme",
                acceptedAdStatuses, acceptedAccountStatuses, PageRequest.of(0, 8)
        );

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals("Mode",result.getContent().getFirst().getCategory());
        assertEquals("Hauts",result.getContent().getFirst().getSubcategory());
        assertEquals("Homme",result.getContent().getFirst().getArticleGender());
    }

    @Test
    public void testFindByAcceptedStatusesOrderedByCreationDateDesc_AKANoFiltersAppliedFromService(){
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesOrderedByCreationDateDesc(acceptedAdStatuses,
                acceptedAccountStatuses,
                PageRequest.of(0, 8));

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(17, result.getTotalElements());
        assertEquals(8, result.getContent().size());
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }
}
