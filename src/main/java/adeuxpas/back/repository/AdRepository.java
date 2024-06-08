package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    /**
     * Find a user's ad list
     * 
     * @param user
     * @return a list of ads
     */
    List<Ad> findAdsByPublisherId(Long publisherId);

    /**
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
    // TO DO :: replace findAdsByPublisherId by this method
    Page<Ad> findAdsByPublisherIdOrderByCreationDateDesc(Long publisherId, Pageable pageable);

    /**
     * Check how many ads have been published by a user
     * 
     * @param publisherId
     * @return the number of ads
     */
    @Query("SELECT COUNT(a) FROM Ad a WHERE a.publisher.id = :publisherId")
    Long findAdsByPublisherIdCount(Long publisherId);

    /**
     * find ads sharing the same category as the current one's
     * 
     * @param category category of the current ad
     * @param pageable
     * @param userId   id of the currend ad's publisher
     * @return a list of similar ads
     */
    @Query("SELECT a FROM Ad a WHERE a.category = :category AND a.publisher.id <> :userId ORDER BY a.creationDate DESC")
    Page<Ad> findAdsByCategoryOrderByCreationDateDesc(String category, Long userId, Pageable pageable);
}
