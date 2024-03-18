package adeuxpas.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;

/**
 * Repository interface for accessing user preferred schedule data in the
 * database.
 * Defines methods to interact with the preferred schedule entity.
 * Extends JpaRepository for CRUD operations.
 * 
 * @author Léa Hadida
 */
@Repository
public interface PreferredScheduleRepository extends JpaRepository<PreferredSchedule, Long> {

    List<PreferredSchedule> findPreferredSchedulesByUser(User user);
}
