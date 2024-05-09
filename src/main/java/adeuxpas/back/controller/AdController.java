package adeuxpas.back.controller;

import adeuxpas.back.service.AdService;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import adeuxpas.back.dto.AdPostRequestDTO;

import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/ads")
public class AdController {
    private final AdService service;

    public AdController(
            @Autowired AdService service) {
        this.service = service;
    }

    /**
     * endpoint getting a Dto to transform it into an Ad object that will be saved
     * in the database before using it to get a ResponseDto to send to the front-end
     * 
     * @param adDto
     * @return also a Dto
     */
    @Operation(summary = "new Ad creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful ad creation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<Object> postAd(@RequestBody @Valid AdPostRequestDTO adDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        try {
            return ResponseEntity.ok(service.postAd(adDto));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create ad.");
        }
    }

    /**
     * endpoint that retrieves information about one Ad with its id
     * 
     * @param id
     * @return ResponseEntity indicating if the Ad has been found
     */
    @Operation(summary = "ad details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of ad information"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.findAdById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no ad found for this id");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * endpoint that get all the ads created by a user
     *
     * @param userId
     * @return ResponseEntity indicating if the Ads have been found
     */
    @Operation(summary = "ads list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of a user's ad list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/list/{userId}")
    public ResponseEntity<Object> getMyAds(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(service.findAdsByPublisherId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
