package adeuxpas.back.repository;

import adeuxpas.back.entity.ArticlePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for accessing article picture-related data stored in the database.
 * <p>
 * This repository interface defines methods to interact with the user entity in the database.
 * It extends the JpaRepository interface, providing out-of-the-box CRUD (Create, Read, Update, Delete) operations
 * for managing article picture entities.
 *
 * @author Mircea Bardan
 */
@Repository
public interface ArticlePictureRepository extends JpaRepository<ArticlePicture, Long> {
}
