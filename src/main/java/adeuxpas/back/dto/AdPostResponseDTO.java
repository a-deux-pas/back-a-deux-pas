package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.util.List;

import adeuxpas.back.enums.AdStatus;
import jakarta.validation.constraints.Size;

public class AdPostResponseDTO {
    private Long id;
    private String title;
    @Size(min = 2, max = 5)
    private List<String> articlePictures;
    private BigDecimal price;
    private String creationDate;
    private Long publisherId;
    private String publisherAlias;
    private String publisherEmail;
    private String publisherInscriptionDate;
    private String publisherCity;
    private AdStatus status;
    private String articleDescription;
    private String articleState;
    private String category;
    private boolean isFavorite;

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

    public List<String> getArticlePictures() {
        return articlePictures;
    }

    public void setArticlePictures(List<String> articlePictures) {
        this.articlePictures = articlePictures;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisher) {
        this.publisherId = publisher;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

    public AdStatus getStatus() {
        return status;
    }

    public void setStatus(AdStatus status) {
        this.status = status;
    }

    public String getArticleState() {
        return articleState;
    }

    public void setArticleState(String articleState) {
        this.articleState = articleState;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getPublisherAlias() {
        return publisherAlias;
    }

    public void setPublisherAlias(String publisherAlias) {
        this.publisherAlias = publisherAlias;
    }

    public String getPublisherInscriptionDate() {
        return publisherInscriptionDate;
    }

    public void setPublisherInscriptionDate(String publisherInscriptionDate) {
        this.publisherInscriptionDate = publisherInscriptionDate;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
