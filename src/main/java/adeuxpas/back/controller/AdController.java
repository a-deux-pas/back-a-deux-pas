package adeuxpas.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class AdController {
    
    /**
     * endpoint getting a Dto to transform it into an Ad object that will be saved
     * in the database before using it to get a Dto to send to the front-end
     * 
     * @param adDto
     * @return also a Dto
     */
    @PostMapping("/create")
    public ResponseEntity<?> postAd(@RequestBody AdPostDto adDto) {
        try {
            Ad newAd = service.postAd(adDto);
            Ad savedAd = repo.save(newAd);
            AdResponseDTO responseDto = mapper.adToAdResponseDTO(savedAd);

            return ResponseEntity.ok().body(responseDto);
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
            Optional<Ad> ad = service.findById(id);
            if (ad.isEmpty())
                return ResponseEntity.status(404).body("Not found");
            Ad entityAd = ad.get();
            AdResponseDTO responseDto = mapper.adToAdResponseDTO(entityAd);
            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
}
