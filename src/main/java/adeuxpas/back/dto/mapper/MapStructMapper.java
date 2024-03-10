package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdPublisherDTO;
import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    @Mapping(source = "articlePictures.first.url", target = "articlePictureUrl")
    HomePageAdDTO adToHomePageAdDTO(Ad ad);

    AdPublisherDTO userToAdPublisherDTO(User user);
}
