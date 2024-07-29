package adeuxpas.back.controller;

import adeuxpas.back.dto.ad.AdCardResponseDTO;
import adeuxpas.back.service.AdService;
import adeuxpas.back.util.ValidationHelper;

import java.io.UncheckedIOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.validation.BindingResult;
import adeuxpas.back.dto.ad.AdPostRequestDTO;
import adeuxpas.back.dto.ad.AdPostResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.*;

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
     *      int, int)
     */
    @Operation(summary = "Retrieves a paginated list of ads based on specified filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ads retrieved successfully"),
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
            Page<AdCardResponseDTO> adsPage = this.adService.findFilteredAdCardResponseDTOs(priceRangesFilter,
                    citiesAndPostalCodesFilter, articleStatesFilter, categoryFilter, loggedInUserId, pageNumber,
                    pageSize);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Creates a new Ad with the provided details.
     *
     * This endpoint handles the creation of a new ad. It expects an
     * AdPostRequestDTO containing the ad's information and up to five image files
     * associated with the ad.
     * 
     * @param adDto         The data transfer object containing ad details, such as
     *                      title, description, and price.
     * @param adPicture1    The first image file associated with the ad.
     * @param adPicture2    The second image.
     * @param adPicture3    The third image (optional).
     * @param adPicture4    The fourth image (optional).
     * @param adPicture5    The fifth image (optional).
     * @param bindingResult Contains validation errors if the DTO has any. Used to
     *                      generate a detailed error response.
     * @return A ResponseEntity containing the response status and the
     *         AdPostResponseDTO if successful, or error details.
     */
    @Operation(summary = "Creates an Ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<Object> createAd(
            @RequestPart("adInfo") @Valid AdPostRequestDTO adDto,
            @RequestPart("adPicture-1") MultipartFile adPicture1,
            @RequestPart("adPicture-2") MultipartFile adPicture2,
            @RequestPart(value = "adPicture-3", required = false) MultipartFile adPicture3,
            @RequestPart(value = "adPicture-4", required = false) MultipartFile adPicture4,
            @RequestPart(value = "adPicture-5", required = false) MultipartFile adPicture5,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationHelper.getErrors(bindingResult));
        }
        try {
            AdPostResponseDTO response = adService.postAd(adDto, adPicture1, adPicture2, adPicture3, adPicture4,
                    adPicture5);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid credentials: " + e.getMessage());
        } catch (UncheckedIOException | PersistenceException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Updates an existing Ad with new details and images.
     *
     * This endpoint handles updating an existing ad. It expects an AdPostRequestDTO
     * containing the updated ad's information
     * and up to five image files. The ad is identified and updated based on the
     * information provided. If the request
     * contains validation errors, a 400 Bad Request response is returned. On
     * successful update, a 200 OK response with
     * the updated ad details is returned.
     * 
     * @param adDto         The data transfer object containing updated ad details.
     * @param adPicture1    The first image file associated with the ad.
     * @param adPicture2    The second image.
     * @param adPicture3    The third image (optional).
     * @param adPicture4    The fourth image (optional).
     * @param adPicture5    The fifth image (optional).
     * @param bindingResult Contains validation errors if the DTO has any. Used to
     *                      generate a detailed error response.
     * @return A ResponseEntity containing the response status and the updated
     *         AdPostResponseDTO if successful, or error details.
     */
    @Operation(summary = "Updates an ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad updated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update")
    public ResponseEntity<Object> updateAd(
            @RequestPart("adInfo") @Valid AdPostRequestDTO adDto,
            @RequestPart("adPicture-1") MultipartFile adPicture1,
            @RequestPart("adPicture-2") MultipartFile adPicture2,
            @RequestPart(value = "adPicture-3", required = false) MultipartFile adPicture3,
            @RequestPart(value = "adPicture-4", required = false) MultipartFile adPicture4,
            @RequestPart(value = "adPicture-5", required = false) MultipartFile adPicture5,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationHelper.getErrors(bindingResult));
        }
        try {
            return ResponseEntity
                    .ok(adService.postAd(adDto, adPicture1, adPicture2, adPicture3, adPicture4, adPicture5));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid credentials: " + e.getMessage());
        } catch (UncheckedIOException | PersistenceException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Endpoint that retrieves information about one Ad with its ID.
     * 
     * @param adId           The ad ID.
     * @param loggedInUserId The logged in user ID only to check if the Ad is part
     *                       of its favorites.
     * @return ResponseEntity indicating if the Ad has been found.
     */
    @Operation(summary = "Retrieves an ad details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad information retrieved successfuly"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{adId}/{loggedInUserId}")
    public ResponseEntity<Object> findById(
            @PathVariable long adId,
            @PathVariable Long loggedInUserId) {
        try {
            return ResponseEntity.ok(adService.findAdById(adId, loggedInUserId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ad found with this id");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that gets a page of ads created by a user excluding the one the
     * current user is on as well as the sold and reserved ones.
     * 
     * @param userId     The user ID.
     * @param pageNumber The page number for pagination (default is 0).
     * @param pageSize   The page size for pagination (default is 8).
     * @param adId       The ad ID to exclude from the result list.
     * @return ResponseEntity indicating if the Ads have been found.
     */
    @Operation(summary = "Retrieves a user's ads list excluding those that are sold or reserved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's ad list retrieved successfuly"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/adPageContentList/{publisherId}/{loggedInUserId}/{adId}")
    public ResponseEntity<Object> getUserAds(
            @PathVariable long publisherId,
            @PathVariable Long loggedInUserId,
            @PathVariable(required = false) Long adId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "8") int pageSize) {
        try {
            Page<AdCardResponseDTO> adsPage = this.adService.findPageOfUserAdsList(publisherId, pageNumber, pageSize,
                    loggedInUserId, adId);
            return ResponseEntity.ok(adsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that gets a page of ads created by a user sorted so that the sold
     * and reserved ones are the very last to be retrieved.
     * 
     * @param userId     The user ID.
     * @param pageNumber The page number for pagination (default is 0).
     * @param pageSize   The page size for pagination (default is 12).
     * @return ResponseEntity indicating if the Ads have been found.
     */
    @Operation(summary = "Retrieves a user's ads list, sorted by status, with sold or reserved ads listed last")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's ad list retrieved successfuly"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/adTablist/{userId}")
    public ResponseEntity<Object> getMyAdTab(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "12") int pageSize) {
        try {
            Page<AdCardResponseDTO> adsPage = this.adService.getUserAdsTab(userId, pageNumber, pageSize);
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
     * @param pageSize   The page size for pagination (default is 4).
     * @return a list of similar ads sharing the same category.
     */
    @Operation(summary = "Retrieves a list of ads with the same category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Similar ads list retrieved successfuly"),
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
            Page<AdCardResponseDTO> adsPage = this.adService.findSimilarAds(category, publisherId, userId, pageNumber,
                    pageSize);
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
    @Operation(summary = "Retrieves a user's favorites ads list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's favorites ads retrieved successfuly"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/favorites/{userId}")
    public ResponseEntity<Object> getUserFavoritesAds(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "12") int pageSize) {
        try {
            return ResponseEntity.ok(adService.findFavoriteAdsByUserId(userId, pageNumber, pageSize).getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that update an ad favorite status.
     *
     * @param adId       the ad ID.
     * @param isFavorite the favorite status of the ad.
     * @return ResponseEntity with a 200 code if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "Update an Ad favorite's status")
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
     * Endpoint that counts how many users have added an ad as favorite.
     * 
     * @param adId the ad ID.
     * @return The favorite ads count.
     */
    @Operation(summary = "Retrieves the count of users who have added an ad to their favorites list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got the count of users that have liked this ad"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/favoriteCount/{adId}")
    public ResponseEntity<Object> getFavoriteCount(
            @PathVariable long adId) {
        try {
            return ResponseEntity.ok(adService.getFavoriteCount(adId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that deletes an ad.
     * 
     * @param adId the ad ID.
     */
    @Operation(summary = "Deletes an ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{adId}")
    public ResponseEntity<Object> deleteAd(@PathVariable long adId) {
        try {
            adService.deleteAd(adId);
            return ResponseEntity.ok("Ad deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
