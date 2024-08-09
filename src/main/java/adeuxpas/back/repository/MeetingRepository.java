package adeuxpas.back.repository;

import adeuxpas.back.entity.Meeting;
import adeuxpas.back.enums.MeetingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.*;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

        List<Meeting> findByStatusAndBuyerIdOrderByDate(MeetingStatus status, Long id);

        List<Meeting> findByStatusAndSellerIdOrderByDate(MeetingStatus status, Long id);

        Optional<Meeting> findByAdsId(Long adId);

        @Query("SELECT m FROM Meeting m WHERE " +
                        " m.status = :status " +
                        " AND (m.seller.id = :sellerId OR m.buyer.id = :buyerId) ORDER BY m.date")
        List<Meeting> findByStatusAndSellerIdOrBuyerIdOrderByDate(
                        @Param("status") MeetingStatus status,
                        @Param("sellerId") Long sellerId,
                        @Param("buyerId") Long buyerId);

        @Query("SELECT m FROM Meeting m WHERE " +
                        "(m.buyer.id = :userId OR m.seller.id = :userId) " +
                        "AND m.date < :date " +
                        "AND m.status <> 'FINALIZED'")
        List<Meeting> findPastMeetings(
                        @Param("userId") Long userId,
                        @Param("date") LocalDateTime date);
}
