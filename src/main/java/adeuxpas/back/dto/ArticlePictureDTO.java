package adeuxpas.back.dto;

import adeuxpas.back.entity.Ad;

public class ArticlePictureDTO {
    private Long id;
    private String url;
    private Ad ad;

    // no-args constructor
    public ArticlePictureDTO() {
    }

    // constructor with arguments
    public ArticlePictureDTO(Long id, String url, Ad ad) {
        this.id = id;
        this.url = url;
        this.ad = ad;
    }

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
