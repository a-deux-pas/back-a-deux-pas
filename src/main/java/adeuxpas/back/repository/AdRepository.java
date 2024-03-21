package adeuxpas.back.repository;

import adeuxpas.back.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    @Query("SELECT a FROM Ad a JOIN User u WHERE " +
                "(:postalCodes IS NULL OR u.postalCode IN :postalCodes) AND " +
                "(:articleStates IS NULL OR a.articleState IN :articleStates) AND " +
                "(:categories IS NULL OR a.category IN :categories) AND " +
                "(:subcategories IS NULL OR a.subcategory IN :subcategories) AND " +
                "(:gender IS NULL OR a.articleGender IN :gender) AND" +
                "(:priceRanges IS NULL OR " +
                    "a.price >= :minPrice1 AND a.price < :maxPrice1 OR " +
                    "a.price >= :minPrice2 AND a.price < :maxPrice2 OR " +
                    "a.price >= :minPrice3 AND a.price < :maxPrice3 OR " +
                    "a.price >= :minPrice4 AND a.price < :maxPrice4 OR " +
                    "a.price >= :minPrice5 AND a.price < :maxPrice5)"
            )
    List<Ad> findFilteredAds(@Param("postalCodes") List<String> postalCodes,
                             @Param("priceRanges") List<String> priceRanges,
                             @Param("articleStates") List<String> articleStates,
                             @Param("categories") List<String> categories,
                             @Param("subcategories") List<String> subcategories,
                             @Param("gender") List<String> gender);
}
