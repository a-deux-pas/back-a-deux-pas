package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.util.Objects;

/*
 * Data transfer object (DTO) class that is used when sending
 * Ad data to the front end home page
 */
public class AdHomeResponseDTO {
    private Long id;
    private String title;
    private String firstArticlePictureUrl;
    private BigDecimal price;
    private Long publisherId;
    private String publisherAlias;
    private String publisherCity;
    private String publisherPostalCode;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPublisherAlias() {
        return publisherAlias;
    }

    public void setPublisherAlias(String publisherAlias) {
        this.publisherAlias = publisherAlias;
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

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AdHomeResponseDTO that))
            return false;
        return Objects.equals(title, that.title) && Objects.equals(firstArticlePictureUrl, that.firstArticlePictureUrl)
                && Objects.equals(price, that.price) && Objects.equals(publisherAlias, that.publisherAlias)
                && Objects.equals(publisherCity, that.publisherCity)
                && Objects.equals(publisherPostalCode, that.publisherPostalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, firstArticlePictureUrl, price, publisherAlias, publisherCity, publisherPostalCode);
    }
}
