package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.util.Objects;

/*
 * Data transfer object (DTO) class that is used when sending
 * Ad data to the front end home page
 */
public class AdHomeResponseDTO {
    private String title;
    private String articlePictureUrl;
    private BigDecimal price;
    private String publisher;
    private String publisherCity;
    private String publisherPostalCode;

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
        if (!(o instanceof AdHomeResponseDTO that)) return false;
        return Objects.equals(title, that.title) && Objects.equals(articlePictureUrl, that.articlePictureUrl) && Objects.equals(price, that.price) && Objects.equals(publisher, that.publisher) && Objects.equals(publisherCity, that.publisherCity) && Objects.equals(publisherPostalCode, that.publisherPostalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, articlePictureUrl, price, publisher, publisherCity, publisherPostalCode);
    }
}
