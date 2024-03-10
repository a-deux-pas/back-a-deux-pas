package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HomePageAdDTO {
    private String title;
    private String articlePictureUrl;
    private BigDecimal price;
    private String articleState;
    private String category;
    private String subcategory;
    private String articleGender;
    private LocalDateTime creationDate;
    private AdPublisherDTO publisherDTO;

    public HomePageAdDTO(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title ;
    }

    public String getArticlePictureUrl() {
        return articlePictureUrl;
    }

    public void setArticlePictureUrl(String articlePictureUrl) {
        this.articlePictureUrl = articlePictureUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getArticleState() {
        return articleState;
    }

    public void setArticleState(String articleState) {
        this.articleState = articleState;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public AdPublisherDTO getPublisherDTO() {
        return publisherDTO;
    }

    public void setPublisherDTO(AdPublisherDTO publisherDTO) {
        this.publisherDTO = publisherDTO;
    }
}
