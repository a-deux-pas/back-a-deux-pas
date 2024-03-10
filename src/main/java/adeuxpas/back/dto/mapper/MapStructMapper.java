package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdPublisherDTO;
import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    @Mapping(source = "articlePictures", target = "articlePictureUrl", qualifiedByName = "findFirstArticlePictureUrl")
    @Mapping(source = "publisher", target = "publisherDTO")
    HomePageAdDTO adToHomePageAdDTO(Ad ad);

    @Named("findFirstArticlePictureUrl")
    default String findFirstArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && !articlePictures.isEmpty()) {
            return articlePictures.get(0).getUrl();
        }
        return null;
    }
    default AdPublisherDTO userToAdPublisherDTO(User user) {
        AdPublisherDTO publisherDTO = new AdPublisherDTO();
        publisherDTO.setAlias(user.getAlias());
        publisherDTO.setEmail(user.getEmail());
        return publisherDTO;
    }
}
