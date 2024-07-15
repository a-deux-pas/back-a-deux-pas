package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.AdStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for managing Ad entities.
 * <p>
 * This repository provides methods for performing CRUD operations on Ad
 * entities,
 * as well as custom queries for retrieving ads based on various criteria.
 * </p>
 * <p>
 * It extends JpaRepository interface, which provides basic CRUD operations, and
 * allows
 * querying based on the entity type.
 * </p>
 *
 * @author Mircea Bardan
 */
@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

        /**
         * Custom query for retrieving ads based on various filters and criteria.
         * <p>
         * This method retrieves ads based on the provided filters such as postal codes,
         * article states,
         * price ranges, categories, subcategories, genders, accepted ad statuses, and
         * accepted account statuses.
         * If a user is logged in, it excludes their ads from the result.
         * It orders the results by creation date in descending order.
         * </p>
         *
         * @param postalCodes     List of postal codes to filter ads by publisher's
         *                        postal code.
         * @param articleStates   List of article states to filter ads by.
         * @param maxPrice1       Maximum price for filtering ads.
         * @param minPrice2       Minimum price for the first price range.
         * @param maxPrice2       Maximum price for the first price range.
         * @param minPrice3       Minimum price for the second price range.
         * @param maxPrice3       Maximum price for the second price range.
         * @param minPrice4       Minimum price for the third price range.
         * @param maxPrice4       Maximum price for the third price range.
         * @param minPrice5       Minimum price for the fourth price range.
         * @param maxPrice5       Maximum price for the fourth price range.
         * @param minPrice6       Minimum price for filtering ads.
         * @param category        Category to filter ads by.
         * @param subcategory     Subcategory to filter ads by.
         * @param gender          Gender to filter ads by.
         * @param adStatuses      List of accepted ad statuses.
         * @param accountStatuses List of accepted user account statuses.
         * @param loggedInUserId  The ID of the logged-in user
         * @param pageable        Pageable object to specify page number, page size, and
         *                        sorting.
         * @return Page of Ad entities matching the specified criteria.
         */
        @Query("SELECT a FROM Ad a JOIN User u ON a.publisher = u WHERE " +
                        "  ( :postalCodes IS NULL OR u.postalCode IN :postalCodes ) AND " +
                        "  ( :articleStates IS NULL OR a.articleState IN :articleStates ) AND " +
                        "  ( ( a.price < :maxPrice1 ) OR " +
                        "    ( a.price BETWEEN :minPrice2 AND :maxPrice2 ) OR" +
                        "    ( a.price BETWEEN :minPrice3 AND :maxPrice3 ) OR" +
                        "    ( a.price BETWEEN :minPrice4 AND :maxPrice4 ) OR" +
                        "    ( a.price BETWEEN :minPrice5 AND :maxPrice5 ) OR" +
                        "    ( a.price > :minPrice6 ) ) AND " +
                        "  ( ( :subcategory IS NULL AND :gender IS NULL AND :category IS NULL ) OR" +
                        "    ( :subcategory IS NULL AND :gender IS NULL AND a.category = :category) OR " +
                        "    ( :gender IS NULL AND a.category = :category AND a.subcategory = :subcategory) OR " +
                        "    ( a.subcategory = :subcategory AND a.articleGender = :gender ) ) AND " +
                        "  ( :acceptedAdStatuses IS NULL OR a.status IN :acceptedAdStatuses ) AND" +
                        "  ( :acceptedAccountStatuses IS NULL OR u.accountStatus IN :acceptedAccountStatuses ) AND " +
                        "  ( :loggedInUserId IS NULL OR a.publisher.id != :loggedInUserId ) " +
                        "ORDER BY a.creationDate DESC")
        Page<Ad> findByAcceptedStatusesFilteredOrderedByCreationDateDesc(
                        @Param("postalCodes") List<String> postalCodes,
                        @Param("articleStates") List<String> articleStates,
                        @Param("maxPrice1") BigDecimal maxPrice1,
                        @Param("minPrice2") BigDecimal minPrice2,
                        @Param("maxPrice2") BigDecimal maxPrice2,
                        @Param("minPrice3") BigDecimal minPrice3,
                        @Param("maxPrice3") BigDecimal maxPrice3,
                        @Param("minPrice4") BigDecimal minPrice4,
                        @Param("maxPrice4") BigDecimal maxPrice4,
                        @Param("minPrice5") BigDecimal minPrice5,
                        @Param("maxPrice5") BigDecimal maxPrice5,
                        @Param("minPrice6") BigDecimal minPrice6,
                        @Param("category") String category,
                        @Param("subcategory") String subcategory,
                        @Param("gender") String gender,
                        @Param("acceptedAdStatuses") List<AdStatus> adStatuses,
                        @Param("acceptedAccountStatuses") List<AccountStatus> accountStatuses,
                        @Param("loggedInUserId") Long loggedInUserId,
                        Pageable pageable);

        /**
         * Custom query for retrieving ads based on accepted ad statuses and accepted
         * account statuses. If a user is logged in, it excludes their ads from the
         * result.
         * It orders the results by creation date in descending order
         *
         * @param adStatuses      List of accepted ad statuses.
         * @param accountStatuses List of accepted account statuses.
         * @param loggedInUserId  The ID of the logged-in user
         * @param pageable        Pageable object to specify page number, page size, and
         *                        sorting.
         * @return Page of Ad entities matching the specified criteria.
         */
        @Query("SELECT ad FROM Ad ad JOIN ad.publisher user " +
                        "WHERE ad.status IN :adStatuses AND user.accountStatus IN :accountStatuses AND " +
                        "( :loggedInUserId IS NULL OR user.id != :loggedInUserId ) " +
                        "ORDER BY ad.creationDate DESC")
        Page<Ad> findByAcceptedStatusesOrderedByCreationDateDesc(
                        List<AdStatus> adStatuses,
                        List<AccountStatus> accountStatuses,
                        Long loggedInUserId,
                        Pageable pageable);

        /**
         * Custom query that retrieves ads by sorting them in order for the ones
         * having a 'reserved' or 'sold' to be called at the very end
         * 
         * @param publisherId
         * @param pageable    carries information about the process and pagination and
         *                    sorting
         *                    such as the page number, its size or how it's sorted (asc
         *                    or desc). It equivalates the sql's LIMIT clause that is
         *                    not available
         *                    in JPQL(Java Persistence Query Language).
         * @return a page of Ad
         */
        @Query(value = "SELECT ad FROM Ad ad WHERE ad.publisher.id = :publisherId ORDER BY CASE WHEN ad.status = 'AVAILABLE' THEN 0 ELSE 1 END, ad.creationDate DESC")
        Page<Ad> findSortedAdsByPublisherIdOrderByCreationDateDesc(
                        @Param("publisherId") Long publisherId,
                        Pageable pageable);

        /**
         * Custom query that gets a page of available ads created by a user
         * but excluding the sold and reserved ones, and excluding a specific adId
         *
         * @param publisherId
         * @param pageable
         * @param adId        the id of the ad to be excluded from the results
         * @return a page of Ad
         */
        @Query("SELECT ad FROM Ad ad WHERE ad.publisher.id = :publisherId AND ad.status = 'AVAILABLE' AND ad.id <> :adId ORDER BY ad.creationDate DESC")

        Page<Ad> findAvailableAdsByPublisherId(
                        @Param("publisherId") Long publisherId,
                        Pageable pageable,
                        @Param("adId") Long adId);

        /**
         * Find ads sharing the same category as the current one's
         * 
         * @param category    category of the current ad
         * @param publisherId ID of the current ad's publisher
         * @param userId      optionally , the current user's ID
         * @param pageable
         * @return a list of similar ads
         */
        @Query("SELECT a FROM Ad a WHERE a.category = :category AND a.status = 'AVAILABLE' AND a.publisher.id <> :publisherId AND a.publisher.id <> :userId ORDER BY a.creationDate DESC")
        Page<Ad> findAdsByCategoryOrderByCreationDateDesc(
                        @Param("category") String category,
                        @Param("publisherId") Long publisherId,
                        @Param("userId") Long userId,
                        Pageable pageable);
}
