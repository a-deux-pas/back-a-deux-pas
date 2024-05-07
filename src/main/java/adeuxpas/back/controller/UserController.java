package adeuxpas.back.controller;

import adeuxpas.back.dto.CityAndPostalCodeResponseDTO;
import adeuxpas.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping("/citiesAndPostalCodes")
    public ResponseEntity<Object> getUniqueCitiesAndPostalCodes() {
        try {
            Set<CityAndPostalCodeResponseDTO> cityAndPostalCodeResponseDTOS = this.userService.getUniqueCitiesAndPostalCodes();
            return ResponseEntity.ok(cityAndPostalCodeResponseDTOS);
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
