package adeuxpas.back.dto;

/*
 * Data transfer object (DTO) class that is used when sending
 * ArticlePicture data to the client app
 */
public class ArticlePictureDTO {
    private String url;
    // private Long adId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // public Long getAdId() {
    // return adId;
    // }

    // public void setAdId(Long adId) {
    // this.adId = adId;
    // }

    @Override
    public String toString() {
        return "ArticlePictureDTO{" +
                "url='" + url + '\'' +
                '}';
    }
}
