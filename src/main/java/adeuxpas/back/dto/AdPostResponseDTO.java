package adeuxpas.back.dto;

import java.math.BigDecimal;

import org.springframework.lang.Nullable;

import adeuxpas.back.enums.AdStatus;

public class AdPostResponseDTO {
    private Long id;
    private String title;
    private String firstArticlePictureUrl;
    private String secondArticlePictureUrl;
    @Nullable
    private String thirdArticlePictureUrl;
    @Nullable
    private String fourthArticlePictureUrl;
    @Nullable
    private String fifthArticlePictureUrl;
    private BigDecimal price;
    private String creationDate;
    private Long publisherId;
    private String publisherAlias;
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

    public String getFirstArticlePictureUrl() {
        return firstArticlePictureUrl;
    }

    public void setFirstArticlePictureUrl(String firstArticlePictureUrl) {
        this.firstArticlePictureUrl = firstArticlePictureUrl;
    }

    public String getSecondArticlePictureUrl() {
        return secondArticlePictureUrl;
    }

    public void setSecondArticlePictureUrl(String secondArticlePictureUrl) {
        this.secondArticlePictureUrl = secondArticlePictureUrl;
    }

    public String getThirdArticlePictureUrl() {
        return thirdArticlePictureUrl;
    }

    public void setThirdArticlePictureUrl(String thirdArticlePictureUrl) {
        this.thirdArticlePictureUrl = thirdArticlePictureUrl;
    }

    public String getFourthArticlePictureUrl() {
        return fourthArticlePictureUrl;
    }

    public void setFourthArticlePictureUrl(String fourthArticlePictureUrl) {
        this.fourthArticlePictureUrl = fourthArticlePictureUrl;
    }

    public String getFifthArticlePictureUrl() {
        return fifthArticlePictureUrl;
    }

    public void setFifthArticlePictureUrl(String fifthArticlePictureUrl) {
        this.fifthArticlePictureUrl = fifthArticlePictureUrl;
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
