package adeuxpas.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.PreferredScheduleRepository;
import adeuxpas.back.repository.UserRepository;

@RestController
public class UserController {

    private PreferredScheduleRepository preferredScheduleRepository;
    private UserRepository userRepository;

    public UserController(PreferredScheduleRepository preferredScheduleRepository, UserRepository userRepository) {
        this.preferredScheduleRepository = preferredScheduleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("compte/profil/{id}")
    public ResponseEntity<List<PreferredSchedule>> getPreferredShedule(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<PreferredSchedule> PreferredSchedule = preferredScheduleRepository.findAllByUser(user);
            if (!PreferredSchedule.isEmpty()) {
                return ResponseEntity.ok(PreferredSchedule);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


