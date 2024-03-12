package adeuxpas.back.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // méthode à changer une fois le login réalisé
    @GetMapping("/compte/profil")
    public ResponseEntity<List<PreferredScheduleDto>> getPreferredShedule() {

    // Retrieve the currently authenticated user's authentication object
    //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    // Retrieve the user ID from the authentication object
    Long userId = 3L; //null
    //if (authentication != null) {
        //userId = authentication.getPrincipal().getId();
    //} 

    // If user ID is null, return an unauthorized response
    // if (userId == null) {
    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    // }
    
    // Proceed with retrieving preferred schedule using the user ID
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        List<PreferredSchedule> preferredSchedules = userService.findPreferredScheduleByUser(user);
        if (!preferredSchedules.isEmpty()) {
            List<PreferredScheduleDto> preferredScheduleDtos = new ArrayList<>();
            for (PreferredSchedule preferredSchedule : preferredSchedules) {
                PreferredScheduleDto preferredScheduleDto = preferredScheduleMapperService.mapToDto(preferredSchedule);
                preferredScheduleDtos.add(preferredScheduleDto);
            }
            List<PreferredScheduleDto> filteredPreferredScheduleDtos = preferredScheduleMapperService.groupByTimes(preferredScheduleDtos);
            return ResponseEntity.ok(filteredPreferredScheduleDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}
}


