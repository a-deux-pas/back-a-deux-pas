package adeuxpas.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adeuxpas.back.service.UserService;

@RestController
@RequestMapping("/utilisateur")
public class UserController {

    private final UserService userService;

    public UserController(
        @Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getAUser(@PathVariable String email) {
        try {
            return ResponseEntity.ok(this.userService.findUserByEmail(email).orElse(null));
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
}


