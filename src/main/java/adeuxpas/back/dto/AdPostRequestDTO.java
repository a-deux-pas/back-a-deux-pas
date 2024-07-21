package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import adeuxpas.back.enums.AdStatus;
import jakarta.validation.constraints.*;

/*
 * This a data transfer object (DTO) that is used when receiving 
 * an Ad object from the front 
 * to transform it into an AdPostDto during
 * the Ad creation process in order 
 * to save it into the database
 */

public class AdPostRequestDTO {

    @Size(min = 4, max = 150)
    @NotBlank
    private String title;
    @NotBlank
    private String articleDescription;
    @NotNull
    private Date creationDate;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotBlank
    private String category;
    @NotBlank
    private String subcategory;
    private String articleGender;
    @NotNull
    private Long publisherId;
    @NotBlank
    private String articleState;
    private List<String> articlePictures;
    @NotNull
    AdStatus status = AdStatus.AVAILABLE;

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleState() {
        return articleState;
    }

    public void setArticleState(String articleState) {
        this.articleState = articleState;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal bigDecimal) {
        this.price = bigDecimal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getArticleGender() {
        return articleGender;
    }

    public void setArticleGender(String articleGender) {
        this.articleGender = articleGender;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public List<String> getArticlePictures() {
        return articlePictures;
    }

    public void setArticlePictures(List<String> articlePictures) {
        this.articlePictures = articlePictures;
    }

    public AdStatus getStatus() {
        return status;
    }

    public void setStatus(AdStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String articlePicturesString = articlePictures != null
                ? articlePictures.stream()
                        .map(String::toString)
                        .collect(Collectors.joining(", ", "[", "]"))
                : "null";

        return "AdPostRequestDTO{" +
                "title='" + title + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", articleGender='" + articleGender + '\'' +
                ", publisherId=" + publisherId +
                ", articleState='" + articleState + '\'' +
                ", articlePictures=" + articlePicturesString +
                '}';
    }

}
