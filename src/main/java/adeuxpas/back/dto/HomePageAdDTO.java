package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class HomePageAdDTO {
    private String title;
    private String articlePictureUrl;
    private BigDecimal price;
    private String articleState;
    private String category;
    private String subcategory;
    private String articleGender;
    private LocalDateTime creationDate;
    private String publisher;
    private String publisherCity;
    private String publisherPostalCode;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }

    public String getPublisherPostalCode() {
        return publisherPostalCode;
    }

    public void setPublisherPostalCode(String publisherPostalCode) {
        this.publisherPostalCode = publisherPostalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomePageAdDTO that)) return false;
        return Objects.equals(title, that.title) && Objects.equals(articlePictureUrl, that.articlePictureUrl) && Objects.equals(price, that.price) && Objects.equals(articleState, that.articleState) && Objects.equals(category, that.category) && Objects.equals(subcategory, that.subcategory) && Objects.equals(articleGender, that.articleGender) && Objects.equals(creationDate, that.creationDate) && Objects.equals(publisher, that.publisher) && Objects.equals(publisherCity, that.publisherCity) && Objects.equals(publisherPostalCode, that.publisherPostalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, articlePictureUrl, price, articleState, category, subcategory, articleGender, creationDate, publisher, publisherCity, publisherPostalCode);
    }
}
