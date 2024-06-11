package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.util.List;

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
    private String creationDate;
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
    @NotEmpty
    @Size(min = 2, max = 5)
    private List<ArticlePictureDTO> articlePictures;
    @NotBlank
    private String articleState;

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
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

    public List<ArticlePictureDTO> getArticlePictures() {
        return this.articlePictures;
    }

    public void setArticlePictures(List<ArticlePictureDTO> articlePictures) {
        this.articlePictures = articlePictures;
    }
}
