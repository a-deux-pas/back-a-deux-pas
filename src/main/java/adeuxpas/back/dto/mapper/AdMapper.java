package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.dto.HomePageAdDTO;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "adPostDto.publisherId", target = "publisher.id")
    Ad adPostDtoToAd(AdPostDto adPostDto);

    // Article Picture
    ArticlePicture articlePictureDTOToaArticlePicture(ArticlePictureDTO articlePictureDTO);

    @Mapping(source = "publisher.id", target = "publisherId")
    AdPostDto adToAdPostDto(Ad ad);
}
