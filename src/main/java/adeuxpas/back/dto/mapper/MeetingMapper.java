package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.meeting.MeetingRequestDTO;
import adeuxpas.back.dto.meeting.MeetingResponseDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.Meeting;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeetingMapper {

    @Mapping(source = "idMeeting", target = "idMeeting")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "buyer.id", target = "buyerId")
    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "buyer.alias", target = "buyerAlias")
    @Mapping(source = "seller.alias", target = "sellerAlias")
    @Mapping(source = "buyer.inscriptionDate", target = "buyerInscriptionDate")
    @Mapping(source = "seller.inscriptionDate", target = "sellerInscriptionDate")
    @Mapping(source = "seller.city", target = "sellerCity")
    @Mapping(source = "buyer.city", target = "buyerCity")
    @Mapping(source = "buyerAdditionalInfo", target = "buyerAdditionalInfo")
    @Mapping(source = "sellerAdditionalInfo", target = "sellerAdditionalInfo")
    @Mapping(source = "buyerDistinctiveSign", target = "buyerDistinctiveSign")
    @Mapping(source = "sellerDistinctiveSign", target = "sellerDistinctiveSign")
    @Mapping(source = "buyer.profilePicture", target = "buyerProfilePictureUrl")
    @Mapping(source = "seller.profilePicture", target = "sellerProfilePictureUrl")
    @Mapping(source = "meetingPlace.name", target = "meetingPlaceName")
    @Mapping(source = "meetingPlace.postalCode", target = "postalCode")
    @Mapping(source = "meetingPlace.city", target = "city")
    @Mapping(source = "meetingPlace.street", target = "street")
    @Mapping(source = "ads", target = "adPublisherAlias", qualifiedByName = "adPublisherAlias")
    @Mapping(source = "ads", target = "adId", qualifiedByName = "adId")
    @Mapping(source = "ads", target = "adTitle", qualifiedByName = "adTitle")
    @Mapping(source = "ads", target = "adPrice", qualifiedByName = "adPrice")
    @Mapping(source = "ads", target = "adPictureUrl", qualifiedByName = "adPictureUrl")
    MeetingResponseDTO meetingToMeetingDTO(Meeting meeting);

    /**
     * Returns the ID of the first ad in the given set.
     *
     * @param ads The set of ad.
     * @return The ID of the first ad, or null if the set is empty.
     */
    @Named("adId")
    default Long adId(Set<Ad> ads) {
        return getFirstAd(ads).map(Ad::getId).orElse(null);
    }

    /**
     * Returns the alias of the publisher of the first ad in the given set.
     *
     * @param ads The set of ads.
     * @return The alias of the publisher of the first ad, or null if the set is
     *         empty.
     */
    @Named("adPublisherAlias")
    default String adPublisherAlias(Set<Ad> ads) {
        return getFirstAd(ads).map(ad -> ad.getPublisher().getAlias()).orElse(null);
    }

    /**
     * Returns the title of the first ad in the given set.
     *
     * @param ads The set of ads.
     * @return The title of the first ad, or null if the set is empty.
     */
    @Named("adTitle")
    default String adTitle(Set<Ad> ads) {
        return getFirstAd(ads).map(Ad::getTitle).orElse(null);
    }

    /**
     * Returns the price of the first ad in the given set.
     *
     * @param ads The set of ads.
     * @return The price of the first ad, or null if the set is empty.
     */
    @Named("adPrice")
    default BigDecimal adPrice(Set<Ad> ads) {
        return getFirstAd(ads).map(Ad::getPrice).orElse(null);
    }

    /**
     * Returns the URL of the picture of the first ad in the given set.
     *
     * @param ads The set of ads.
     * @return The URL of the picture of the first ad, or null if the set is empty.
     */
    @Named("adPictureUrl")
    default String adPictureUrl(Set<Ad> ads) {
        return getFirstAd(ads)
                .flatMap(ad -> ad.getArticlePictures().stream().findFirst())
                .map(ArticlePicture::getUrl)
                .orElse(null);
    }

    /**
     * Helper method to get the first ad from the set if it exists.
     * 
     * @param ads Set of ads
     * @return Optional containing the first ad if present, or empty Optional if the
     *         set is empty
     */
    default Optional<Ad> getFirstAd(Set<Ad> ads) {
        return Optional.ofNullable(ads).filter(set -> !set.isEmpty())
                .map(set -> set.iterator().next());
    }

    @Mapping(source = "buyerId", target = "buyer.id")
    @Mapping(source = "sellerId", target = "seller.id")
    @Mapping(source = "proposedMeetingPlaceId", target = "meetingPlace.id")
    Meeting proposedMeetingRequestDTOToMeeting(MeetingRequestDTO meetingRequestDTO);

}
