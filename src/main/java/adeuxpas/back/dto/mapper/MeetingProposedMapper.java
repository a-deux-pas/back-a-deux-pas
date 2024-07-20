package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.MeetingProposedDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.Meeting;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface MeetingProposedMapper {
    @Mapping(source = "idMeeting", target = "idMeeting")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "seller.alias", target = "sellerAlias")
    @Mapping(source = "buyer.alias", target = "buyerAlias")
    @Mapping(source = "buyer.id", target = "buyerId")
    @Mapping(source = "seller.profilePicture", target = "sellerProfilePictureUrl")
    @Mapping(source = "buyer.profilePicture", target = "buyerProfilePictureUrl")
    @Mapping(source = "buyer.inscriptionDate", target = "buyerInscriptionDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "seller.inscriptionDate", target = "sellerInscriptionDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "meetingPlace.name", target = "meetingPlaceName")
    @Mapping(source = "meetingPlace.postalCode", target = "postalCode")
    @Mapping(source = "meetingPlace.city", target = "city")
    @Mapping(source = "meetingPlace.street", target = "street")
    @Mapping(source = "ads", target = "adTitle", qualifiedByName = "adTitle")
    @Mapping(source = "ads", target = "adPrice", qualifiedByName = "adPrice")
    @Mapping(source = "ads", target = "adPictureUrl", qualifiedByName = "adPictureUrl")
    @Mapping(source = "buyerAdditionalInfo", target = "buyerAdditionalInfo")
    @Mapping(source = "sellerAdditionalInfo", target = "sellerAdditionalInfo")
    MeetingProposedDTO meetingToMeetingProposedDTO(Meeting meeting);

    @Named("adTitle")
    default String adTitle(Set<Ad> ads) {
        return ads.isEmpty() ? null : ads.iterator().next().getTitle();
    }

    @Named("adPrice")
    default BigDecimal adPrice(Set<Ad> ads) {
        return ads.isEmpty() ? null : ads.iterator().next().getPrice();
    }

    @Named("adPictureUrl")
    default String adPictureUrl(Set<Ad> ads) {
        return ads.isEmpty() || ads.iterator().next().getArticlePictures().isEmpty() ? null : ads.iterator().next().getArticlePictures().get(0).getUrl();
    }
}
