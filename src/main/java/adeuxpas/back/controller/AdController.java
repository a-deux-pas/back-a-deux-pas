package adeuxpas.back.controller;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.AdResponseDTO;
import adeuxpas.back.dto.mapper.AdMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.service.AdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final AdService service;
    private final AdRepository repo;
    private final AdMapper mapper;

    public AdController(
            @Autowired AdService service,
            @Autowired AdRepository repo,
            @Autowired AdMapper mapper) {
        this.service = service;
        this.repo = repo;
        this.mapper = mapper;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> findAllAds() {
        try {
            return ResponseEntity.ok(this.service.findAllHomePageAds());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

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
