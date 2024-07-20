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

    List<Meeting> findByStatusAndBuyerIdOrderByDateDesc(MeetingStatus status, Long id);

    List<Meeting> findByStatusAndSellerIdOrderByDateDesc(MeetingStatus status, Long id);

    @Query("SELECT m FROM Meeting m WHERE " +
            " m.status = :status " +
            " AND (m.seller.id = :sellerId OR m.buyer.id = :buyerId) ORDER BY m.date DESC")
    List<Meeting> findByStatusAndSellerIdOrBuyerIdOrderByDateDesc(
            @Param("status") MeetingStatus status,
            @Param("sellerId") Long sellerId,
            @Param("buyerId") Long buyerId
    );

    //@Query("SELECT m FROM Meeting m WHERE (m.buyer.id = :buyerId OR m.seller.id = :sellerId) AND m.date < :date ORDER BY m.date DESC")
    //List<Meeting> findMeetings(@Param("buyerId") Long buyerId, @Param("sellerId") Long sellerId, @Param("date") LocalDateTime date);

    @Query("SELECT m FROM Meeting m WHERE " +
            "m.status = :status " +
            "AND (m.buyer.id = :userId OR m.seller.id = :userId) " +
            "AND m.date < :date " +
            "ORDER BY m.date DESC")
    List<Meeting> findMeetings(
            @Param("status") MeetingStatus status,
            @Param("userId") Long userId,
            @Param("date") LocalDateTime date
    );
}
