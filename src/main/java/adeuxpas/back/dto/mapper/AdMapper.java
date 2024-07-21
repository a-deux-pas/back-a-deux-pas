package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdCardResponseDTO;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.service.CloudinaryService;
import adeuxpas.back.dto.AdPostResponseDTO;
import adeuxpas.back.dto.ArticlePictureDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper interface for mapping Ad entities to DTOs (Data Transfer Objects).
 * <p>
 * This mapper provides methods for mapping Ad entities to various DTOs,
 * such as its multiple article picture DTO
 * </p>
 * <p>
 * It uses MapStruct annotations for mapping and Spring component model for
 * integration with Spring.
 * </p>
 * <p>
 * This interface defines mapping methods for converting Ad entity attributes to
 * corresponding DTO fields.
 * </p>
 * <p>
 * It also includes helper methods.
 * </p>
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface AdMapper {
    Logger LOGGER = LoggerFactory.getLogger(AdMapper.class);

    default Logger getLogger() {
        return LoggerFactory.getLogger(AdMapper.class);
    }

    /**
     * Maps an ad entity to an AdCardResponseDTO
     *
     * @param ad
     * @return AdCardResponseDTO
     * @see AdCardResponseDTO
     */
    @Mapping(source = "articlePictures", target = "firstArticlePictureUrl", qualifiedByName = "findFirstArticlePictureUrl")
    @Mapping(source = "publisher.alias", target = "publisherAlias")
    @Mapping(source = "publisher.id", target = "publisherId")
    AdCardResponseDTO adToAdCardResponseDTO(Ad ad);

    // TO DO: à virer? (fix cloudinary branch)
    /**
     * Maps an ad entity to an adPostResponseDTO
     * 
     * @param ad
     * @return adPostResponseDTO
     * @see AdPostResponseDTO
     */
    // @Mapping(source = "articlePictures", target = "articlePictures")
    @Mapping(target = "articlePictures", ignore = true)
    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "convertDateToString")
    @Mapping(source = "publisher.id", target = "publisherId")
    @Mapping(source = "publisher.alias", target = "publisherAlias")
    @Mapping(source = "publisher.city", target = "publisherCity")
    @Mapping(source = "publisher.inscriptionDate", target = "publisherInscriptionDate", qualifiedByName = "convertDateToString")
    AdPostResponseDTO adToAdPostResponseDTO(Ad ad);

    /**
     * Gets the first article picture's url
     * 
     * @param articlePictures
     * @return
     */
    @Named("findFirstArticlePictureUrl")
    default String findFirstArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && !articlePictures.isEmpty()) {
            return articlePictures.get(0).getUrl();
        }
        return null;
    }

    /**
     * Convert a date to a string
     * 
     * @param date
     * @return
     */
    @Named("convertDateToString")
    default String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Maps an adPostRequest DTO into an ad entity
     *
     * @param adPostDto
     * @return
     */
    @Mapping(source = "publisherId", target = "publisher.id")
    @Mapping(target = "articlePictures", ignore = true)
    Ad adPostRequestDTOToAd(AdPostRequestDTO adPostDto);

    // /**
    // * Maps ad entity into an an adPostRequest DTO
    // *
    // * @param ad
    // * @return
    // */
    // @Mapping(source = "publisher.id", target = "publisherId")
    // AdPostRequestDTO adToAdPostRequestDTO(Ad ad);

    /**
     * Maps an article picture dto into an article picture entity
     * 
     * @param articlePictureDTO
     * @return
     */
    ArticlePicture articlePictureDTOToArticlePicture(ArticlePictureDTO articlePictureDTO);

    /**
     * Maps an article picture entity into an article picture dto
     * 
     * @param articlePicture
     * @return
     */
    ArticlePictureDTO articlePictureToArticlePictureDTO(ArticlePicture articlePicture);
}
