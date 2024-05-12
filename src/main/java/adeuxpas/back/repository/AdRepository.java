package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    /**
     * Find a user's ad list
     * 
     * @param user
     * @return a list of ads
     */
    List<Ad> findAdsByPublisherId(Long publisherId);
}
