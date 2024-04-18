package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.ArticlePictureDTO;
import adeuxpas.back.entity.ArticlePicture;

import org.mapstruct.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticlePictureMapper {

    ArticlePicture articlePictureDTOToArticlePicture(ArticlePictureDTO articlePictureDTOs);

    @InheritInverseConfiguration
    ArticlePictureDTO articlePictureToArticlePictureDTO(ArticlePicture articlePicture);

}
