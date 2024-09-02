package adeuxpas.back.service;

import adeuxpas.back.dto.ad.AdCardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import adeuxpas.back.dto.ad.AdPostRequestDTO;
import adeuxpas.back.dto.ad.AdPostResponseDTO;

import java.util.List;

/**
 * Service interface for managing ads.
 */
public interface AdService {

        /**
         * Contract to find all ads and map them to AdCardResponseDTOs.
         *
         * @param pageable       The pagination information.
         * @param loggedInUserId The ID of the logged-in user.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findAllAdCardResponseDTOs(Long loggedInUserId, Pageable pageable);

        /**
         * Contract to find filtered ads and map them to AdCardResponseDTOs.
         *
         * @param prices         The list of price filters.
         * @param cities         The list of city filters.
         * @param articleStates  The list of article state filters.
         * @param category       The category filter.
         * @param loggedInUserId The ID of the logged-in user.
         * @param pageNumber     The page number for pagination.
         * @param pageSize       The page size for pagination.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findFilteredAdCardResponseDTOs(List<String> prices, List<String> cities,
                        List<String> articleStates, String category, Long loggedInUserId, int pageNumber, int pageSize);

        /**
         * Persists an Ad object in the database.
         *
         * This method handles both the creation of a new ad and the update of an
         * existing ad.
         * It updates the ad's details and manages its associated images. If the ad is
         * being updated,
         * it removes all existing images and replaces them with new ones provided in
         * the request.
         *
         * @param adDto The data transfer object containing ad details from
         *                         the front-end application.
         * @param adPicture1       The first image file associated with the ad.
         * @param adPicture2       The second image.
         * @param adPicture3       The third image (optional).
         * @param adPicture4       The fourth image (optional).
         * @param adPicture5       The fifth image (optional).
         * @return An AdPostResponseDTO containing the details of the saved or updated
         *         ad.
         */
        AdPostResponseDTO postAd(
                        AdPostRequestDTO adDto,
                        MultipartFile adPicture1,
                        MultipartFile adPicture2,
                        MultipartFile adPicture3,
                        MultipartFile adPicture4,
                        MultipartFile adPicture5);

        /**
         * Contract to find an ad by its ID.
         *
         * @param adId           The ID of the ad to find.
         * @param loggedInUserId The ID of the logged-in user.
         * @return The response DTO containing the ad details.
         */
        AdPostResponseDTO findAdById(long adId, Long loggedInUserId);

        /**
         * Contract that returns a definite number of a user's ads excluding the sold
         * and reserved ones
         * 
         * @param publisherId    The ID of the ad's publisher.
         * @param pageNumber     The page number for pagination.
         * @param pageSize       The page size for pagination.
         * @param loggedInUserId The ID of the logged-in user.
         * @param adId           The ID of ad to exclude from the result list.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findPageOfUserAdsList(long publisherId, int pageNumber, int pageSize,
                        Long loggedInUserId, Long adId);

        /**
         * Contract that returns a definite number of a user's sorted ads
         * 
         * @param publisherId The ID of the ad's publisher.
         * @param pageNumber  The page number for pagination.
         * @param pageSize    The page size for pagination.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> getUserAdsTab(long publisherId, int pageNumber, int pageSize);

        /**
         * Contract that retrieves of list of ads sharing the same category as the
         * current one's
         * 
         * @param category    The category to find.
         * @param publisherId The ID of the ad's publisher.
         * @param userId      The ID of the logged-in user.
         * @param pageNumber  The page number for pagination.
         * @param pageSize    The page size for pagination.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findSimilarAds(String category, Long publisherId, Long userId, int pageNumber,
                        int pageSize);

        /**
         * Contract to find ads added as favorite by a user.
         *
         * @param userId     The ID of the user.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The page size for pagination.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findFavoriteAdsByUserId(long userId, int pageNumber, int pageSize);

        /**
         * Contract to update the ad favorite status added by a user.
         *
         * @param adId   The ID of the ad.
         * @param userId The ID of the user.
         * @param isFavorite The new status of the ad favorite.
         */
        void updateAdFavoriteStatus(long adId, long userId, boolean isFavorite);

        /**
         * Contract that checks an ad's favorite count.
         * 
         * @param adId The ID of the ad.
         * @return The favorite count.
         */
        long getFavoriteCount(long adId);

        /**
         * Contract that deletes an ad.
         * 
         * @param adId The ID of the ad.
         */
        void deleteAd(long adId);
}
