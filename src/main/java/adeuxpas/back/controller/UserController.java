package adeuxpas.back.controller;

import adeuxpas.back.dto.LoginRequest;
import adeuxpas.back.dto.SignupRequest;
import adeuxpas.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(("/api"))
public class UserController {
    private final UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String accessSignup(){
        return "Welcome, EVERYBODY. Please SIGN UP using the POST http method on this endpoint";
    }

    @GetMapping("/login")
    public String accessLogin(){
        return "Welcome, EVERYBODY. Please LOG IN using the POST http method on this endpoint";
    }

    @GetMapping("/content")
    public String accessContent(){
        return "Welcome, USER/ADMIN";
    }

    @GetMapping("/admin-page")
    public String accessAdminPage(){
        return "Welcome, ADMIN";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest){
        if (this.userService.canDoSignup(signupRequest))
            return ResponseEntity.ok().body("User signed up successfully with email: " + this.userService.findByEmail(signupRequest.getEmail()).get().toString());

        return ResponseEntity.status(403).body("A user with email '" + signupRequest.getEmail() + "' already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Optional<String> token = this.userService.login(loginRequest);
        if (token.isPresent())
            return ResponseEntity.ok(token);

        return ResponseEntity.status(401).body("Invalid email and/or password");
    }
}
