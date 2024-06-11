package adeuxpas.back.dto;

/*
 * Data transfer object (DTO) class that is used when sending
 * ArticlePicture data to the client app
 */
public class ArticlePictureDTO {
    // private Long id;
    private String url;
    private Long adId;

    // getters and setters
    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
}
