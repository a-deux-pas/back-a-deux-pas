package adeuxpas.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.User;

@Repository
public interface PreferredMeetingPlaceRepository extends JpaRepository<PreferredMeetingPlace, Long> {
    
    List<PreferredMeetingPlace> findPreferredMeetingPlacesByUser(User user);
}
