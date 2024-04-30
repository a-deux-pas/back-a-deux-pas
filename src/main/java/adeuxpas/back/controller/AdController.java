package adeuxpas.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.mapper.AdMapper;
import adeuxpas.back.service.AdService;

import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.Operation;

//AJouter doc swagger que Lea a mis dans son userController
@RestController
@RequestMapping("/ad")
public class AdController {
    private final AdService service;
    private final AdMapper mapper;

    final Logger logger = LoggerFactory.getLogger(AdController.class);

    public AdController(
            @Autowired AdService service,
            @Autowired AdMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * endpoint getting a Dto to transform it into an Ad object that will be saved
     * in the database before using it to get a ResponseDto to send to the front-end
     * 
     * @param adDto
     * @return also a Dto
     */
    @PostMapping("/create")
    public ResponseEntity<?> postAd(@RequestBody AdPostRequestDTO adDto) {
        logger.atInfo().log("adDto: " + adDto);
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
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.findAdById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no ad found for this id");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
