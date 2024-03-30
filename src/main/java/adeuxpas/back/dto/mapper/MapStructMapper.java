package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import adeuxpas.back.service.UserService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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

    // @Mapping(expression = "java(findUserById(adPostDto.getPublisherId(),
    // userService))", target = "publisher")
    // // @Mapping(target = "id", ignore = true)
    // // @Mapping(source = "publisherId", target = "publisher", qualifiedByName =
    // // "findUserById")
    // Ad adPostDtoToAd(AdPostDto adPostDto, UserService userService);

    // @Named("findUserById")
    // default User findUserById(Long id, UserService userService) {
    // Optional<User> userOptional = userService.findUserById(id);
    // return userOptional.orElse(null);
    // }
}
