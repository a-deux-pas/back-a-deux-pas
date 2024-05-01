package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.util.List;

import adeuxpas.back.enums.AdStatus;

/*
 * This a data transfer object (DTO) that is used when receiving 
 * an Ad object from the front 
 * to tranform it into an AdPostDto during 
 * the Ad creation process in order 
 * to save it into the database
 */

public class AdPostRequestDTO {
    private Long id;
    private String title;
    private String articleDescription;
    private String creationDate;
    private BigDecimal price;
    private String category;
    private String subcategory;
    private String articleGender;
    private Long publisherId;
    private List<ArticlePictureDTO> articlePictures;
    private String articleState;
    private AdStatus status = AdStatus.AVAILABLE;

    // no-args constructor
    public AdPostRequestDTO() {
    }

    /**
     * args constructor
     * 
     * @param id
     * @param title
     * @param articleDescription
     * @param creationDate
     * @param price
     * @param category
     * @param articleGender
     * @param publisherId
     * @param articlePictures
     * @param articleState
     * @param status
     */
    public AdPostRequestDTO(Long id, String title, String articleDescription, String creationDate, BigDecimal price,
            String category, String articleGender, Long publisherId, List<ArticlePictureDTO> articlePictures,
            String articleState, AdStatus status) {
        this.id = id;
        this.title = title;
        this.articleDescription = articleDescription;
        this.creationDate = creationDate;
        this.price = price;
        this.category = category;
        this.articleGender = articleGender;
        this.publisherId = publisherId;
        this.articlePictures = articlePictures;
        this.articleState = articleState;
        this.status = status;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public AdStatus getStatus() {
        return status;
    }

    public void setStatus(AdStatus status) {
        this.status = status;
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
