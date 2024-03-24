package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    @Query("SELECT a FROM Ad a JOIN User u ON a.publisher = u WHERE " +
            "( :postalCodes IS NULL OR u.postalCode IN :postalCodes ) AND " +
            "( :articleStates IS NULL OR a.articleState IN :articleStates ) AND " +
            "( ( :maxPrice1 IS NOT NULL AND a.price < :maxPrice1 ) OR " +
            "( :minPrice2  IS NOT NULL AND a.price BETWEEN :minPrice2 AND :maxPrice2 ) OR" +
            "( :minPrice3  IS NOT NULL AND a.price BETWEEN :minPrice3 AND :maxPrice3 ) OR" +
            "( :minPrice4  IS NOT NULL AND a.price BETWEEN :minPrice4 AND :maxPrice4 ) OR" +
            "( :minPrice5  IS NOT NULL AND a.price BETWEEN :minPrice5 AND :maxPrice5 ) OR" +
            "( :minPrice6  IS NOT NULL AND a.price > :minPrice6 ) )"
    )
  /*@Query(nativeQuery = true, value = "SELECT a.* FROM ad a JOIN user u ON a.publisher_id = u.id WHERE " +
           "((:postalCodes is null OR u.postal_code IN :postalCodes) AND :articleStates is null ) OR" +
          "((:postalCodes is null OR u.postal_code IN :postalCodes) AND a.article_state IN :articleStates )"

   )*/

    List<Ad> findFilteredAds(@Param("postalCodes") List<String> postalCodes,
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
                             @Param("minPrice6") BigDecimal minPrice6/*,
                             @Param("categories") List<String> categories,
                             @Param("subcategories") List<String> subcategories,
                             @Param("gender") List<String> gender*/);
}
