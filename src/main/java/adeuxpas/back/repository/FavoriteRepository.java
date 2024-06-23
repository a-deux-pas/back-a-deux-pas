package adeuxpas.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.Favorite;
import adeuxpas.back.entity.FavoriteKey;
import adeuxpas.back.entity.User;

/**
 * Repository interface for accessing user's favorite ads in the database.
 * <p>
 * This repository defines methods to interact with the favorite entity,
 * using {@link FavoriteKey} as the composite key.
 * It extends JpaRepository for CRUD (Create, Read, Update, Delete) operations.
 * </p>
 * <p>
 * The methods defined in this repository allow querying and managing favorite
 * ad
 * data.
 * </p>
 *
 * @author Léa Hadida
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteKey> {
    /**
     * Finds ads added as favorite by a user.
     *
     * @param user The user who has added the ads as favorites.
     * @return A list of favorites ads order by descending date
     */
    List<Favorite> findByUserOrderByAddedAtDesc(User user);
}
