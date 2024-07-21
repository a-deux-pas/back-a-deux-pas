package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.MeetingDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.Meeting;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
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
    @Mapping(source = "ads", target = "adTitle", qualifiedByName = "adTitle")
    @Mapping(source = "ads", target = "adPrice", qualifiedByName = "adPrice")
    @Mapping(source = "ads", target = "adPictureUrl", qualifiedByName = "adPictureUrl")
    MeetingDTO meetingToMeetingDTO(Meeting meeting);

    @Named("adTitle")
    default String adTitle(Set<Ad> ads) {
        return getFirstAd(ads).map(Ad::getTitle).orElse(null);
    }

    @Named("adPrice")
    default BigDecimal adPrice(Set<Ad> ads) {
        return getFirstAd(ads).map(Ad::getPrice).orElse(null);
    }

    @Named("adPictureUrl")
    default String adPictureUrl(Set<Ad> ads) {
        return getFirstAd(ads)
                .flatMap(ad -> ad.getArticlePictures().stream().findFirst())
                .map(picture -> picture.getUrl())
                .orElse(null);
    }

    /**
     * Helper method to get the first ad from the set if it exists.
     * @param ads Set of ads
     * @return Optional containing the first ad if present, or empty Optional if the set is empty
     */
    default Optional<Ad> getFirstAd(Set<Ad> ads) {
        return Optional.ofNullable(ads).filter(set -> !set.isEmpty())
                .map(set -> set.iterator().next());
    }
}
