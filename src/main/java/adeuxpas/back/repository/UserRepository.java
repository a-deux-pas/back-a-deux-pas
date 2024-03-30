package adeuxpas.back.repository;

import adeuxpas.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface for accessing user-related data stored in the database.
 * <p>
 * This repository interface defines methods to interact with the user entity in
 * the database.
 * It extends the JpaRepository interface, providing out-of-the-box CRUD
 * (Create, Read, Update, Delete) operations
 * for managing user entities.
 * </p>
 * <p>
 * Additionally, it includes a custom query method {@code findByEmail} to
 * retrieve a user entity
 * by its email address.
 * </p>
 *
 * @author Mircea Bardan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves an optional user entity from the database by its email address.
     * <p>
     * This method queries the database for a user entity with the specified email
     * address.
     * If a matching user is found, it returns an optional containing the user
     * entity,
     * otherwise, it returns an empty optional.
     * </p>
     *
     * @param email The email address of the user to retrieve.
     * @return An optional containing the user entity if found, or an empty optional
     *         otherwise.
     */
    Optional<User> findByEmail(String email);

    Optional<User> findUserById(Long userId);
}
