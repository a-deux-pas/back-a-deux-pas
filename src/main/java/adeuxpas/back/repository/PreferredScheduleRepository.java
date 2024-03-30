package adeuxpas.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;

/**
 * Repository interface for accessing user preferred schedule data in the database.
 * <p>
 * This repository defines methods to interact with the preferred schedule entity.
 * It extends JpaRepository for CRUD (Create, Read, Update, Delete) operations.
 * </p>
 * <p>
 * The methods defined in this repository allow querying and managing preferred schedule data.
 * </p>
 *
 * @author Léa Hadida
 */
@Repository
public interface PreferredScheduleRepository extends JpaRepository<PreferredSchedule, Long> {
    /**
     * Finds preferred schedules associated with the given user.
     *
     * @param user The user for whom preferred schedules are to be retrieved.
     * @return A list of preferred schedules associated with the specified user.
     */
    List<PreferredSchedule> findPreferredSchedulesByUser(User user);
}
