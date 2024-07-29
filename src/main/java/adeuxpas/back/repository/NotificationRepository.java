package adeuxpas.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.Notification;

/**
 * Interface for accessing user's notifications preferences data stored in the
 * database.
 * <p>
 * This repository interface defines methods to interact with the notification
 * entity in the database.
 * It extends the JpaRepository interface, providing out-of-the-box CRUD
 * (Create, Read, Update, Delete) operations.
 *
 * @author Léa Hadida
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
