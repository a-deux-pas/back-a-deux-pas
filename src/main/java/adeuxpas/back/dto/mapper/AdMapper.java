package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.AdCardResponseDTO;
import adeuxpas.back.dto.AdPostRequestDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.dto.AdPostResponseDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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

    /**
     * Maps an ad entity into an adPostResponse DTO
     * 
     * @param ad
     * @return
     */
    @Mapping(target = "articlePictures", qualifiedByName = "convertArticlePicturesToUrls")
    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "convertDateToString")
    @Mapping(source = "publisher.id", target = "publisherId")
    @Mapping(source = "publisher.alias", target = "publisherAlias")
    @Mapping(source = "publisher.email", target = "publisherEmail")
    @Mapping(source = "publisher.city", target = "publisherCity")
    @Mapping(source = "publisher.inscriptionDate", target = "publisherInscriptionDate", qualifiedByName = "convertDateToString")
    AdPostResponseDTO adToAdPostResponseDTO(Ad ad);

    /**
     * Gets all the article picture objects from the Ad entity and retrieves their
     * urls
     * 
     * @param pictures
     * @return a list of string
     */
    @Named("convertArticlePicturesToUrls")
    default List<String> convertArticlePicturesToUrls(List<ArticlePicture> pictures) {
        return pictures != null ? pictures.stream()
                .map(ArticlePicture::getUrl)
                .toList() : null;
    }

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
     * @return a string returning the date under the dd/MM/yyyy format
     */
    @Named("convertDateToString")
    default String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Maps an adPostRequest DTO into an Ad entity.
     *
     * @param adPostDto
     * @return an Ad entity.
     */
    Ad adPostRequestDTOToAd(AdPostRequestDTO adPostDto);

    /**
     * Updates an existing Ad entity with values from the provided AdPostRequestDTO.
     *
     * This method maps non-null fields from the DTO to the specified Ad entity.
     * The `ad` entity is modified directly.
     *
     * @param adPostRequestDTO The DTO containing updated ad details.
     * @param ad               The Ad entity to be updated.
     */
    void updateAdFromDto(AdPostRequestDTO adPostRequestDTO, @MappingTarget Ad ad);
}
