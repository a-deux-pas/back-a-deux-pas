package adeuxpas.back.repository;

import adeuxpas.back.entity.Meeting;
import adeuxpas.back.enums.MeetingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

import static adeuxpas.back.enums.MeetingStatus.ACCEPTED;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByBuyerIdOrderByDateDesc(Long id);

    List<Meeting> findBySellerIdOrderByDateDesc(Long id);

    List<Meeting> findByStatusAndSellerIdOrderByDateDesc(MeetingStatus status, Long id);

    @Query("SELECT m FROM Meeting m WHERE (m.buyer.id = :buyerId OR m.seller.id = :sellerId) AND m.date < :date ORDER BY m.date DESC")
    List<Meeting> findMeetings(Long buyerId, Long sellerId, LocalDateTime date);


    //List<Meeting> findMeetings(@Param("id") Long id, @Param("date") LocalDateTime date);
}
