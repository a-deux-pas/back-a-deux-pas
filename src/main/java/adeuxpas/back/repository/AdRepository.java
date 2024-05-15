package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
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
     * @param pageable
     * @return
     */
    // TO DO : replace findAdsByPublisherId by this method
    Page<Ad> findAdsByPublisherIdOrderByCreationDateDesc(Long publisherId, Pageable pageable);
}
