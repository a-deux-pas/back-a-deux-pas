package adeuxpas.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adeuxpas.back.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;
    /**
     * Constructor for UserController.
     *
     * @param userService for handling operations concerning users.
     */
    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    // méthode à changer une fois le login réalisé
    @GetMapping("/compte/profil/présentation")
    public ResponseEntity<Object> getUserInformation() {
        Long userId = 4L; 
        try {
            return ResponseEntity.ok(userService.findUserProfileInfoById(userId));
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }  
    }
    

    // méthode à changer une fois le login réalisé
    @GetMapping("/compte/profil/disponibilités")
    public ResponseEntity<Object> getPreferredShedules() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findPreferredSchedulesByUserId(userId));
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }  
    }

    // méthode à changer une fois le login réalisé
    @GetMapping("/compte/profil/lieux-de-rdv")
    public ResponseEntity<Object> getPreferredMeetingPlaces() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findPreferredMeetingPlacesByUserId(userId));
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }  
    }
}


