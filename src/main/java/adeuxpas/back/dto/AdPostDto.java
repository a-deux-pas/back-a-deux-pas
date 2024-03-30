package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import adeuxpas.back.entity.ArticlePicture;

public class AdPostDto {
    private Long id;
    private String title;
    private String articleDescription;
    private LocalDateTime creationDate;
    private BigDecimal price;
    private String category;
    private String subcategory;
    private String articleGender;
    private Long publisherId;
    private List<ArticlePicture> articlePictures;
    private String articleState;

    public AdPostDto() {
    }

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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal bigDecimal) {
        this.price = bigDecimal;
    }

    // public AdStatus getStatus() {
    // return status;
    // }

    // public void setStatus(AdStatus status) {
    // this.status = status;
    // }

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

    public List<ArticlePicture> getArticlePictures() {
        return this.articlePictures;
    }

    public void setArticlePictures(List<ArticlePicture> articlePictures) {
        this.articlePictures = articlePictures;
    }

    @Override
    public String toString() {
        return "AdPostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", articleState='" + articleState + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                // ", status=" + status +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", articleGender='" + articleGender + '\'' +
                ", publisherId=" + publisherId +
                ", articlePictures=" + articlePictures +
                '}';
    }
}
