package adeuxpas.back.service;

import adeuxpas.back.dto.AdCardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;

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
         * @param pageable       The pagination information.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findFilteredAdCardResponseDTOs(List<String> prices, List<String> cities,
                        List<String> articleStates, String category, Long loggedInUserId, Pageable pageable);

        /**
         * Contract to post an ad.
         *
         * @param adDto The DTO containing the details of the ad to be posted.
         * @return The response DTO after posting the ad.
         */
        AdPostResponseDTO postAd(AdPostRequestDTO adDto);

        /**
         * Contract to find an ad by its ID.
         *
         * @param adId           The ID of the ad to find.
         * @param loggedInUserId The ID of the logged-in user.
         * @return The response DTO containing the ad details.
         */
        AdPostResponseDTO findAdById(long adId, long loggedInUserId);

        /**
         * Contract that returns a definite number of a user's ads excluding the sold
         * and reserved ones
         * 
         * @param publisherId    The ID of the ad's publisher.
         * @param pageable       The pagination information.
         * @param loggedInUserId The ID of the logged-in user.
         * @param adId           The ID of ad to exclude from the result list.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findPageOfUserAdsList(long publisherId, Pageable pageable, Long loggedInUserId,
                        Long adId);

        /**
         * Contract that returns a definite number of a user's sorted ads
         * 
         * @param publisherId The ID of the ad's publisher.
         * @param pageable    The pagination information.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> getUserAdsTab(long publisherId, Pageable pageable);

        /**
         * Contract that retrieves of list of ads sharing the same category as the
         * current one's
         * 
         * @param category    The category to find.
         * @param publisherId The ID of the ad's publisher.
         * @param userId      The ID of the logged-in user.
         * @param pageable    The pagination information.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findSimilarAds(String category, Long publisherId, Long userId, Pageable pageable);

        /**
         * Contract to find ads added as favorite by a user.
         *
         * @param userId The ID of the user.
         * @return The page of AdCardResponseDTOs.
         */
        Page<AdCardResponseDTO> findFavoriteAdsByUserId(long userId, Pageable pageable);

        /**
         * Contract to update the ad favorite status added by a user.
         *
         * @param adId   The ID of the ad.
         * @param userId The ID of the user.
         * @param userId The new status of the ad favorite.
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
