package adeuxpas.back.entity;

import jakarta.persistence.*;

@Entity
public class ArticlePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;

    // constructors
    public ArticlePicture() {
    }

    // constructor with arguments
    public ArticlePicture(String url, Ad ad) {
        this.url = url;
        this.ad = ad;
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "ArticlePicture{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", ad=" + (ad != null ? "Ad{id=" + ad.getId() + "}" : "null") +
                '}';
    }
}
