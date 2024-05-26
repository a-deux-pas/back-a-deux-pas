package adeuxpas.back.dto;

import adeuxpas.back.entity.Ad;

/*
 * Data transfer object (DTO) class that is used when sending
 * ArticlePicture data to the client app
 */
public class ArticlePictureDTO {
    private Long id;
    private String url;
    private Ad ad;


    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
