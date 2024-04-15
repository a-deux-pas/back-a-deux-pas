package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.UserRepository;
import adeuxpas.back.service.UserService;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    // Ad
    @Mapping(source = "articlePictures", target = "articlePictureUrl", qualifiedByName = "findFirstArticlePictureUrl")
    @Mapping(source = "publisher.alias", target = "publisher")
    @Mapping(source = "publisher.city", target = "publisherCity")
    HomePageAdDTO adToHomePageAdDTO(Ad ad);

    @Named("findFirstArticlePictureUrl")
    default String findFirstArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && !articlePictures.isEmpty()) {
            return articlePictures.get(0).getUrl();
        }
        return null;
    }

    @Mapping(source = "adPostDto.publisherId", target = "publisher.id")
    Ad adPostDtoToAd(AdPostDto adPostDto, UserRepository userRepository);

}
