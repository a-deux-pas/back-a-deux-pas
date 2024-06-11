package adeuxpas.back.controller;

import adeuxpas.back.dto.AdHomeResponseDTO;
import adeuxpas.back.service.AdService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import adeuxpas.back.dto.AdPostRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import adeuxpas.back.dto.AdPostResponseDTO;

import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for handling ad-related endpoints.
 *
 * <p>
 * This controller provides endpoints for managing ads, including retrieving
 * lists of ads,
 * creating new ads, and retrieving individual ad details.
 * </p>
 * <p>
 * It interacts with the AdService to perform ad-related operations.
 * </p>
 *
 * @author Mircea Bardan
 */
@RestController
@RequestMapping("/api/ads")
public class AdController {
    private final AdService adService;

    /**
     * Constructor for AdController.
     *
     * @param adService the service responsible for handling ad-related operations.
     */
    public AdController(@Autowired AdService adService) {
        this.adService = adService;
    }

    /**
     * Retrieves a paginated list of ads based on specified filters.
     *
     * <p>
     * This endpoint fetches a paginated list of ads from the AdService based on the
     * provided filters.
     * The filters include price ranges, cities and postal codes, article states,
     * and category.
     * If successful, it returns a ResponseEntity containing the paginated list of
     * ads.
     * If an unexpected exception occurs during the operation, it returns a 500
     * Internal Server Error response
     * with an error message in the response body.
     * </p>
     *
     * @param priceRangesFilter          the list of price ranges to filter ads by
     * @param citiesAndPostalCodesFilter the list of cities and postal codes to
     *                                   filter ads by
     * @param articleStatesFilter        the list of article states to filter ads by
     * @param categoryFilter             the category to filter ads by
     * @param pageNumber                 the page number for pagination (default is
     *                                   0)
     * @param pageSize                   the page size for pagination (default is 8)
     * @return a ResponseEntity containing the paginated list of ads if successful,
     *         or a 500 Internal Server Error response if an exception occurs
     *
     * @see AdService#findFilteredAdHomeResponseDTOs(List, List, List, String,
     *      Pageable)
     */
    @Operation(summary = "Retrieves a paginated list of ads based on specified filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of ads"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/list", params = { "priceRanges", "citiesAndPostalCodes", "articleStates", "category" })
    public ResponseEntity<Object> getAdsPage(
            @RequestParam("priceRanges") List<String> priceRangesFilter,
            @RequestParam("citiesAndPostalCodes") List<String> citiesAndPostalCodesFilter,
            @RequestParam("articleStates") List<String> articleStatesFilter,
            @RequestParam("category") String categoryFilter,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "8") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdHomeResponseDTO> adsPage = this.adService.findFilteredAdHomeResponseDTOs(priceRangesFilter,
                    citiesAndPostalCodesFilter,
                    articleStatesFilter, categoryFilter, pageable);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint getting a Dto to transform it into an Ad object that will be saved
     * in the database before using it to get a ResponseDto to send to the
     * front-end
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
            return ResponseEntity.ok(adService.postAd(adDto));
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
            return ResponseEntity.ok(adService.findAdById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no ad found for this id");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * endpoint that gets a page of ads created by a user
     * 
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return ResponseEntity indicating if the Ads have been found
     */
    @Operation(summary = "a page of the user's ads list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of a page of the user's ad list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/list/{userId}")
    public ResponseEntity<Object> getMyAds(@PathVariable long userId, @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "8") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdPostResponseDTO> adsPage = this.adService.findPageOfUserAdsList(userId, pageable);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // TO DO :: checker les exceptions (UsernameNotFoundException a rien a voir avec
    // getSimilarAds)
    /**
     * endpoint that gets the count ads published by a user
     * 
     * @param userId
     * @return The number of ads published by a user
     */
    @Operation(summary = "the number of ads published by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval the user's ad list count"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("count/{userId}")
    public ResponseEntity<Object> getAdsCount(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(adService.getUserAdsListLength(userId));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get the user's ad count.");
        }
    }

    // TO DO :: checker les exceptions (UsernameNotFoundException a rien a voir avec
    // getSimilarAds)
    /**
     * endpoint that gets a list of similar ads
     * 
     * @param category
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return a list of similar ads sharing the same category
     */
    @Operation(summary = "list of similar ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the list of similar ads"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("similarAdsList/{category}/{publisherId}/{userId}")
    public ResponseEntity<Object> getSimilarAds(@PathVariable String category, @PathVariable Long userId,
            @PathVariable Long publisherId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "4") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdPostResponseDTO> adsPage = this.adService.findSimilarAds(category, userId, publisherId, pageable);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get a list of similar ads");
        }
    }
}
