package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdHomeResponseDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface AdMapper {
    @Mapping(source = "articlePictures", target = "articlePictureUrl", qualifiedByName = "findFirstArticlePictureUrl")
    @Mapping(source = "publisher.alias", target = "publisher")
    @Mapping(source = "publisher.city", target = "publisherCity")
    @Mapping(source = "publisher.postalCode", target = "publisherPostalCode")
    AdHomeResponseDTO adToAdHomeResponseDTO(Ad ad);

    @Named("findFirstArticlePictureUrl")
    default String findFirstArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && !articlePictures.isEmpty()) {
            return articlePictures.get(0).getUrl();
        }
        return null;
    }
}
