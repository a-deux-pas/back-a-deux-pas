package adeuxpas.back.controller;

import adeuxpas.back.dto.AdCardResponseDTO;
import adeuxpas.back.service.AdService;
import adeuxpas.back.service.CloudinaryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.entity.Ad;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);
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
     * @param priceRangesFilter          The list of price ranges to filter ads by.
     * @param citiesAndPostalCodesFilter The list of cities and postal codes to
     *                                   filter ads by.
     * @param articleStatesFilter        The list of article states to filter ads
     *                                   by.
     * @param categoryFilter             The category to filter ads by.
     * @param loggedInUserId             The ID of the logged-in user.
     * @param pageNumber                 The page number for pagination (default is
     *                                   0).
     * @param pageSize                   The page size for pagination (default is 8)
     * @return A ResponseEntity containing the paginated list of ads if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     *
     * @see AdService#findFilteredAdCardResponseDTOs(List, List, List, String, Long,
     *      Pageable)
     */
    @Operation(summary = "Retrieves a paginated list of ads based on specified filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of ads"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/list", params = { "priceRanges", "citiesAndPostalCodes", "articleStates", "category",
            "loggedInUserId" })
    public ResponseEntity<Object> getAdsPage(
            @RequestParam("priceRanges") List<String> priceRangesFilter,
            @RequestParam("citiesAndPostalCodes") List<String> citiesAndPostalCodesFilter,
            @RequestParam("articleStates") List<String> articleStatesFilter,
            @RequestParam("category") String categoryFilter,
            @RequestParam(value = "loggedInUserId", required = false) Long loggedInUserId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "8") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdCardResponseDTO> adsPage = this.adService.findFilteredAdCardResponseDTOs(priceRangesFilter,
                    citiesAndPostalCodesFilter,
                    articleStatesFilter, categoryFilter, loggedInUserId, pageable);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint getting a Dto to transform it into an Ad object that will be saved
     * in the database before using it to get a ResponseDto to send to the
     * front-end.
     *
     * @param adDto The AdDTO.
     * @return The AdDTO.
     */
    // TO DO :: à revoir (fix Cloudinary branch)
    @Operation(summary = "new Ad creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful ad creation"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    // TO DO :: revoir doc
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> postAd(
            @RequestPart("adInfo") @Valid AdPostRequestDTO adDto,
            @RequestPart("adPicture-1") MultipartFile adPicture1,
            @RequestPart("adPicture-2") MultipartFile adPicture2,
            @RequestPart(value = "adPicture-3", required = false) MultipartFile adPicture3,
            @RequestPart(value = "adPicture-4", required = false) MultipartFile adPicture4,
            @RequestPart(value = "adPicture-5", required = false) MultipartFile adPicture5,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        try {
            List<MultipartFile> pictures = new ArrayList<>();
            pictures.add(adPicture1);
            pictures.add(adPicture2);
            if (adPicture3 != null)
                pictures.add(adPicture3);
            if (adPicture4 != null)
                pictures.add(adPicture4);
            if (adPicture5 != null)
                pictures.add(adPicture5);

            logger.info("adDto in the controller: {}", adDto);
            logger.info("pictures in the controller: {}", pictures);
            // TODO :: modifier pour mettre 'HttpStatus.CREATED'
            return ResponseEntity.ok(adService.postAd(adDto, pictures));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create ad");
        }
    }

    /**
     * Endpoint that retrieves information about one Ad with its ID.
     * 
     * @param id The ad ID.
     * @return ResponseEntity indicating if the Ad has been found.
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
     * endpoint that gets a page of ads created by a user excluding the one the
     * current user is on as well as the sold and reserved ones
     * 
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @param adId
     * @return ResponseEntity indicating if the Ads have been found
     */
    @Operation(summary = "a page of the user's ads list excluding the ones that are sold or reserved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of a page of the user's ad list for an ad page content"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/adPageContentList/{publisherId}/{loggedInUserId}/{adId}")
    public ResponseEntity<Object> getMyAds(
            @PathVariable long publisherId,
            @PathVariable Long loggedInUserId,
            @PathVariable Long adId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "8") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdCardResponseDTO> adsPage = this.adService.findPageOfUserAdsList(publisherId, pageable,
                    loggedInUserId, adId);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * endpoint that gets a page of ads created by a user sorted so that the sold
     * and reserved ones are the very last to be retrieved
     * 
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return ResponseEntity indicating if the Ads have been found
     */
    @Operation(summary = "a page of the user's ads list sorted by their statuses so that the ones that are sold or reserved are the last called")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of a page of the user's ad list for the ad tab"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/adTablist/{userId}")
    public ResponseEntity<Object> getMyAdTab(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "12") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdCardResponseDTO> adsPage = this.adService.getUserAdsTab(userId, pageable);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that gets a list of similar ads.
     * 
     * @param category   The ad's category.
     * @param userId     The user ID.
     * @param pageNumber The page number for pagination (default is
     *                   0).
     * @param pageSize   The page size for pagination (default is 8).
     * @return a list of similar ads sharing the same category.
     */
    @Operation(summary = "list of similar ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the list of similar ads"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/similarAdsList/{category}/{publisherId}/{userId}")
    public ResponseEntity<Object> getSimilarAds(
            @PathVariable String category,
            @PathVariable long publisherId,
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "4") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<AdCardResponseDTO> adsPage = this.adService.findSimilarAds(category, publisherId, userId, pageable);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get a list of similar ads");
        }
    }

    /**
     * Endpoint that get the ads added as favorite by a user.
     *
     * @param userId The user ID.
     * @return ResponseEntity containing the ads with a 200 code if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "user's favorites ads list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of a user's favorites ads"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/favorites/{userId}")
    public ResponseEntity<Object> getUserFavoritesAds(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "12") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return ResponseEntity.ok(adService.findFavoriteAdsByUserId(userId, pageable).getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that update an ad favorite status.
     *
     * @param adId       the ad ID.
     * @param isFavorite the favorite status of the ad
     * @return ResponseEntity with a 200 code if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "update an Ad favorite's status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite status updated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{adId}/favorite/{userId}")
    public ResponseEntity<Object> updateAdFavoriteStatus(
            @PathVariable long adId,
            @PathVariable long userId,
            @RequestBody boolean isFavorite) {
        try {
            adService.updateAdFavoriteStatus(adId, userId, isFavorite);
            return ResponseEntity.ok("Favorite status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that checks how many users have added this ad as a favorite
     * 
     * @param adId the ad ID.
     * @return the favorite count
     */
    @Operation(summary = "how many users have added this ad in their favorite list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got the count of users that have liked this ad"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/favoriteCount/{adId}")
    public ResponseEntity<Object> checksFavoriteCount(
            @PathVariable long adId) {
        try {
            return ResponseEntity.ok(adService.checkFavoriteCount(adId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}