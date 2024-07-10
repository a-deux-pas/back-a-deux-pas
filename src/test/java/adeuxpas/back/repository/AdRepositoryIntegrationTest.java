package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.testdatainit.IntegrationTestDatabaseSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the {@link AdRepository}.
 * <p>
 * These tests ensure that the repository methods interact correctly with the
 * underlying database,
 * and that the custom queries return the expected results based on various
 * filter criteria.
 * </p>
 * <p>
 * The tests use a remote Docker-managed MySQL container (@Testcontainers) for
 * database interactions, and the database is seeded
 * with test data before each test method is executed.
 * </p>
 * <p>
 * Test methods cover different scenarios of filtering ads based on postal
 * codes, article states,
 * price ranges, categories, subcategories, genders, accepted ad statuses, and
 * accepted account statuses.
 * </p>
 *
 * @author Mircea Bardan
 */
@DataJpaTest
@Import({ IntegrationTestDatabaseSeeder.class, BCryptPasswordEncoder.class })
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdRepositoryIntegrationTest {

    /**
     * List of postal codes used for filtering ads in the tests.
     */
    private final List<String> postalCodes = List.of("94700", "75000", "75018", "69000", "66000");

    /**
     * List of article states used for filtering ads in the tests.
     */
    private final List<String> articleStates = List.of("Neuf avec étiquette", "Neuf sans étiquette", "Très bon état",
            "Bon état", "Satisfaisant");

    /**
     * List of accepted ad statuses used for filtering ads in the tests.
     */
    private final List<AdStatus> acceptedAdStatuses = List.of(AdStatus.AVAILABLE);

    /**
     * List of accepted account statuses used for filtering ads in the tests.
     */
    private final List<AccountStatus> acceptedAccountStatuses = List.of(AccountStatus.ACTIVE, AccountStatus.REPORTED);

    @Autowired
    AdRepository adRepository;
    @Autowired
    IntegrationTestDatabaseSeeder seeder;

    /**
     * Static MySQLContainer instance for running a MySQL database in a Docker
     * container.
     */
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0");

    /**
     * Method executed before each test method to seed the test database with data.
     */
    @BeforeEach
    void seedTestDatabase() {
        this.seeder.seedTestDatabase();
    }

    /**
     * Test method to verify that the database connection is established
     * successfully.
     */
    @Test
    void testDatabaseConnectionIsEstablished() {
        assertThat(mySQLContainer.isCreated()).isTrue();
        assertThat(mySQLContainer.isRunning()).isTrue();
    }

    /**
     * This method tests the
     * {@link AdRepository#findByAcceptedStatusesFilteredOrderedByCreationDateDesc(List, List, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, String, String, String, List, List, Pageable)}
     * method with all filters applied except category.
     * </p>
     */
    @Test
    void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_allFiltersExceptCategoryApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19),
                BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59),
                BigDecimal.valueOf(60), null, null, null,
                acceptedAdStatuses, acceptedAccountStatuses, null, PageRequest.of(0, 8));

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

    /**
     * This method tests the
     * {@link AdRepository#findByAcceptedStatusesFilteredOrderedByCreationDateDesc(List, List, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, String, String, String, List, List, Pageable)}
     * method with all filters, including category, applied.
     * </p>
     */
    @Test
    void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_filtersWithCategoryApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19),
                BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59),
                BigDecimal.valueOf(60),
                "Électronique", null, null,
                acceptedAdStatuses, acceptedAccountStatuses, null, PageRequest.of(0, 8));

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals("Phone case", result.getContent().getFirst().getTitle());
        assertEquals("Électronique", result.getContent().getFirst().getCategory());
    }

    /**
     * This method tests the
     * {@link AdRepository#findByAcceptedStatusesFilteredOrderedByCreationDateDesc(List, List, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, String, String, String, List, List, Pageable)}
     * method with category and subcategory filters applied.
     * </p>
     */
    @Test
    void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_filtersWithCategoryAndSubcategoryApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19),
                BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59),
                BigDecimal.valueOf(60),
                "Mode", "Hauts", null,
                acceptedAdStatuses, acceptedAccountStatuses, null, PageRequest.of(0, 8));

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("Mode", result.getContent().getFirst().getCategory());
        assertEquals("Hauts", result.getContent().getFirst().getSubcategory());
        assertEquals("Mode", result.getContent().getLast().getCategory());
        assertEquals("Hauts", result.getContent().getLast().getSubcategory());
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }

    /**
     * This method tests the
     * {@link AdRepository#findByAcceptedStatusesFilteredOrderedByCreationDateDesc(List, List, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, String, String, String, List, List, Pageable)}
     * method with all filters, including category, subcategory and gender, applied.
     * </p>
     */
    @Test
    void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc_filtersWithCategorySubcategoryAndGenderApplied() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                postalCodes, articleStates, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(19),
                BigDecimal.valueOf(20), BigDecimal.valueOf(29),
                BigDecimal.valueOf(30), BigDecimal.valueOf(39), BigDecimal.valueOf(40), BigDecimal.valueOf(59),
                BigDecimal.valueOf(60),
                "Mode", "Hauts", "Homme",
                acceptedAdStatuses, acceptedAccountStatuses, null, PageRequest.of(0, 8));

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals("Mode", result.getContent().getFirst().getCategory());
        assertEquals("Hauts", result.getContent().getFirst().getSubcategory());
        assertEquals("Homme", result.getContent().getFirst().getArticleGender());
    }

    /**
     * This method tests the
     * {@link AdRepository#findByAcceptedStatusesOrderedByCreationDateDesc(List, List, Pageable)}
     * method (which is also called when no filters are applied)
     */
    @Test
    void testFindByAcceptedStatusesOrderedByCreationDateDesc_AKANoFiltersAppliedFromService() {
        // Act
        Page<Ad> result = adRepository.findByAcceptedStatusesOrderedByCreationDateDesc(acceptedAdStatuses,
                acceptedAccountStatuses,
                null,
                PageRequest.of(0, 8));

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(17, result.getTotalElements());
        assertEquals(8, result.getContent().size());
        assertTrue(result.getContent().get(0).getCreationDate().isAfter(result.getContent().get(1).getCreationDate()));
    }
}
