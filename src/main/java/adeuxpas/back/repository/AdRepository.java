package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    @Query("SELECT a FROM Ad a JOIN User u ON a.publisher = u WHERE (:postalCodes IS NULL OR u.postalCode IN :postalCodes) AND (:articleStates IS NULL OR a.articleState IN :articleStates)"
            )
  /*@Query(nativeQuery = true, value = "SELECT a.* FROM ad a JOIN user u ON a.publisher_id = u.id WHERE " +
           "((:postalCodes is null OR u.postal_code IN :postalCodes) AND :articleStates is null ) OR" +
          "((:postalCodes is null OR u.postal_code IN :postalCodes) AND a.article_state IN :articleStates )"

   )*/

    List<Ad> findFilteredAds(@Param("postalCodes") List<String> postalCodes,
                            // @Param("priceRanges") List<String> priceRanges,
                             @Param("articleStates") List<String> articleStates/*,
                             @Param("categories") List<String> categories,
                             @Param("subcategories") List<String> subcategories,
                             @Param("gender") List<String> gender*/);
}
