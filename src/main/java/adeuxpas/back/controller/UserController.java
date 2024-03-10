package adeuxpas.back.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adeuxpas.back.dto.PreferredScheduleDto;
import adeuxpas.back.dto.PreferredScheduleMapperService;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.UserRepository;
import adeuxpas.back.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PreferredScheduleMapperService preferredScheduleMapperService;

    /**
     * Constructor for UserController.
     *
     * @param userService for handling operations concerning users.
     */
    public UserController(
        @Autowired UserService userService,
        @Autowired UserRepository userRepository,
        @Autowired PreferredScheduleMapperService preferredScheduleMapperService
        ){
        this.userService = userService;
        this.userRepository = userRepository;
        this.preferredScheduleMapperService = preferredScheduleMapperService;
    }

    // routes et méthode à changer une fois le login réalisé
    // userId/compte/profil
    @GetMapping("/compte/profil/{id}")
    public ResponseEntity<List<PreferredScheduleDto>> getPreferredShedule(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<PreferredSchedule> preferredSchedules = userService.findPreferredScheduleByUser(user);
            if (!preferredSchedules.isEmpty()) {
                List<PreferredScheduleDto> preferredScheduleDtos = new ArrayList<>();
                for (PreferredSchedule preferredSchedule : preferredSchedules) {
                    PreferredScheduleDto preferredScheduleDto = preferredScheduleMapperService.mapToDto(preferredSchedule);
                    preferredScheduleDtos.add(preferredScheduleDto);
                }
                return ResponseEntity.ok(preferredScheduleDtos);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


