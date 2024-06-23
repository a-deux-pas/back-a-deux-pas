package adeuxpas.back.service;

import adeuxpas.back.dto.AdHomeResponseDTO;
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
     * Contract to find all ads and map them to AdHomeResponseDTOs.
     *
     * @param pageable The pagination information.
     * @return The page of AdHomeResponseDTOs.
     */
    Page<AdHomeResponseDTO> findAllAdHomeResponseDTOs(Pageable pageable);

    /**
     * Contract to find filtered ads and map them to AdHomeResponseDTOs.
     *
     * @param prices        The list of price filters.
     * @param cities        The list of city filters.
     * @param articleStates The list of article state filters.
     * @param category      The category filter.
     * @param pageable      The pagination information.
     * @return The page of AdHomeResponseDTOs.
     */
    Page<AdHomeResponseDTO> findFilteredAdHomeResponseDTOs(List<String> prices, List<String> cities,
            List<String> articleStates, String category, Pageable pageable);

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
     * @param adId The ID of the ad to find.
     * @return The response DTO containing the ad details.
     */
    AdPostResponseDTO findAdById(Long adId);

    /**
     * Contract that returns a definite number of a user's ads
     * 
     * @param publisherId
     * @param pageable
     * @return
     */
    Page<AdPostResponseDTO> findPageOfUserAdsList(Long publisherId, Pageable pageable);

    /**
     * Contract that returns the count of a user's ads
     * 
     * @param publisherId
     * @return
     */
    Long getUserAdsListLength(Long publisherId);

    /**
     * Contract that retrieves of list of ads sharing the same category as the
     * current one's
     * 
     * @param category
     * @param publisherId
     * @param userId
     * @param pageable
     * @return
     */
    Page<AdPostResponseDTO> findSimilarAds(String category, Long publisherId, Long userId, Pageable pageable);

    /**
     * Contract to find ads added as favorite by a user.
     *
     * @param userId The ID of the user.
     * @return The list of favorites ads.
     */
    List<AdHomeResponseDTO> findFavoriteAdsByUserId(long userId);
}
