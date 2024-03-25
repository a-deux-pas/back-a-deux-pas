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
    @Query( "SELECT a FROM Ad a JOIN User u ON a.publisher = u WHERE " +
            "( :postalCodes IS NULL OR u.postalCode IN :postalCodes ) AND " +
            "( :articleStates IS NULL OR a.articleState IN :articleStates ) AND " +
            "( ( a.price < :maxPrice1 ) OR " +
            "  ( a.price BETWEEN :minPrice2 AND :maxPrice2 ) OR" +
            "  ( a.price BETWEEN :minPrice3 AND :maxPrice3 ) OR" +
            "  ( a.price BETWEEN :minPrice4 AND :maxPrice4 ) OR" +
            "  ( a.price BETWEEN :minPrice5 AND :maxPrice5 ) OR" +
            "  ( a.price > :minPrice6 ) ) AND " +
            "( ( :subcategory IS NULL AND :gender IS NULL AND :category IS NULL ) OR" +
            "  ( :subcategory IS NULL AND :gender IS NULL AND a.category = :category) OR " +
            "  ( :gender IS NULL AND a.category = :category AND a.subcategory = :subcategory) OR " +
            "  ( a.subcategory = :subcategory AND a.articleGender = :gender ) )" )
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
                             @Param("minPrice6") BigDecimal minPrice6,
                             @Param("category") String category,
                             @Param("subcategory") String subcategory,
                             @Param("gender") String gender
                            );
}
