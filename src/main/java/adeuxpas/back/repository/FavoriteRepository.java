package adeuxpas.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.Favorite;
import adeuxpas.back.entity.FavoriteKey;

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

}
