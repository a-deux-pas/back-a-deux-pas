package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.AdResponseDTO;
import adeuxpas.back.dto.HomePageAdDTO;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "articlePictures", target = "articlePictureUrl", qualifiedByName = "findFirstArticlePictureUrl")
    @Mapping(source = "publisher.alias", target = "publisher")
    @Mapping(source = "publisher.city", target = "publisherCity")
    HomePageAdDTO adToHomePageAdDTO(Ad ad);

    @Mapping(source = "articlePictures", target = "firstArticlePictureUrl", qualifiedByName = "findFirstArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "secondArticlePictureUrl", qualifiedByName = "findSecondArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "thirdArticlePictureUrl", qualifiedByName = "findThirdArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "fourthArticlePictureUrl", qualifiedByName = "findFourthArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "fifthArticlePictureUrl", qualifiedByName = "findFifthArticlePictureUrl")
    @Mapping(source = "publisher.alias", target = "publisher")
    @Mapping(source = "publisher.city", target = "publisherCity")
    @Mapping(source = "publisher.postalCode", target = "publisherPostalCode")
    AdResponseDTO adToAdResponseDTO(Ad ad);

    @Named("findFirstArticlePictureUrl")
    default String findFirstArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && !articlePictures.isEmpty()) {
            return articlePictures.get(0).getUrl();
        }
        return null;
    }

    @Named("findSecondArticlePictureUrl")
    default String findSecondArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 2) {
            return articlePictures.get(1).getUrl();
        }
        return null;
    }

    @Named("findThirdArticlePictureUrl")
    default String thirdArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 3) {
            return articlePictures.get(2).getUrl();
        }
        return null;
    }

    @Named("findFourthArticlePictureUrl")
    default String fourthArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 4) {
            return articlePictures.get(3).getUrl();
        }
        return null;
    }

    @Named("findFifthArticlePictureUrl")
    default String fifthArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 5) {
            return articlePictures.get(4).getUrl();
        }
        return null;
    }

    @Mapping(source = "publisherId", target = "publisher.id")
    Ad adPostDtoToAd(AdPostDto adPostDto);
}
