package adeuxpas.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.User;
/**
 * Repository interface for accessing user preferred meeting place data in the database.
 * <p>
 * This repository defines methods to interact with the preferred meeting place entity.
 * It extends JpaRepository for CRUD (Create, Read, Update, Delete) operations.
 * </p>
 * <p>
 * The methods defined in this repository allow querying and managing preferred meeting place data.
 * </p>
 *
 * @author Léa Hadida
 */
@Repository
public interface PreferredMeetingPlaceRepository extends JpaRepository<PreferredMeetingPlace, Long> {
    /**
     * Finds preferred meeting places associated with the given user.
     *
     * @param user The user for whom preferred meeting places are to be retrieved.
     * @return A list of preferred meeting places associated with the specified user.
     */
    List<PreferredMeetingPlace> findPreferredMeetingPlacesByUser(User user);
}
