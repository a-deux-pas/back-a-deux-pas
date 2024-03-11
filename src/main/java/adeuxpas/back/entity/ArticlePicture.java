package adeuxpas.back.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ArticlePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;

    // constructors
    public ArticlePicture(){}

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

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticlePicture that)) return false;
        return Objects.equals(url, that.url) && Objects.equals(ad, that.ad);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(url, ad);
    }
}
