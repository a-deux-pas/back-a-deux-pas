package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdCardResponseDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.dto.AdPostResponseDTO;
import adeuxpas.back.dto.ArticlePictureDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

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
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {

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

    // TO DO: à virer?
    /**
     * Maps an ad entity to an adPostResponseDTO
     * 
     * @param ad
     * @return adPostResponseDTO
     * @see AdPostResponseDTO
     */
    @Mapping(source = "articlePictures", target = "firstArticlePictureUrl", qualifiedByName = "findFirstArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "secondArticlePictureUrl", qualifiedByName = "findSecondArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "thirdArticlePictureUrl", qualifiedByName = "findThirdArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "fourthArticlePictureUrl", qualifiedByName = "findFourthArticlePictureUrl")
    @Mapping(source = "articlePictures", target = "fifthArticlePictureUrl", qualifiedByName = "findFifthArticlePictureUrl")
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
     * Gets the second article picture's url
     * 
     * @param articlePictures
     * @return
     */
    @Named("findSecondArticlePictureUrl")
    default String findSecondArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 2) {
            return articlePictures.get(1).getUrl();
        }
        return null;
    }

    /**
     * Gets the third article picture's url
     * 
     * @param articlePictures
     * @return
     */
    @Named("findThirdArticlePictureUrl")
    default String thirdArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 3) {
            return articlePictures.get(2).getUrl();
        }
        return null;
    }

    /**
     * Gets the fourth article picture's url
     * 
     * @param articlePictures
     * @return
     */
    @Named("findFourthArticlePictureUrl")
    default String fourthArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 4) {
            return articlePictures.get(3).getUrl();
        }
        return null;
    }

    /**
     * Gets the find article picture's url
     * 
     * @param articlePictures
     * @return
     */
    @Named("findFifthArticlePictureUrl")
    default String fifthArticlePictureUrl(List<ArticlePicture> articlePictures) {
        if (articlePictures != null && articlePictures.size() >= 5) {
            return articlePictures.get(4).getUrl();
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
    Ad adPostRequestDTOToAd(AdPostRequestDTO adPostDto);

    /**
     * Maps ad entity into an an adPostRequest DTO
     * 
     * @param ad
     * @return
     */
    @Mapping(source = "publisher.id", target = "publisherId")
    AdPostRequestDTO adToAdPostRequestDTO(Ad ad);

    /**
     * Maps an article picture dto into an article picture entity
     * 
     * @param articlePictureDTO
     * @return
     */
    @Mapping(source = "adId", target = "ad.id")
    ArticlePicture articlePictureDTOToArticlePicture(ArticlePictureDTO articlePictureDTO);

    /**
     * Maps an article picture entity into an article picture dto
     * 
     * @param articlePicture
     * @return
     */
    @Mapping(source = "ad.id", target = "adId")
    ArticlePictureDTO articlePictureToArticlePictureDTO(ArticlePicture articlePicture);
}
