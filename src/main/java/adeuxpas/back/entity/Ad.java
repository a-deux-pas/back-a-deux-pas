package adeuxpas.back.entity;

import adeuxpas.back.enums.AdStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String title;
    @Column(name = "article_description", columnDefinition = "TEXT")
    private String articleDescription;
    @Column(name = "article_state", length = 150)
    private String articleState;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private AdStatus status;
    @Column(length = 100)
    private String category;
    @Column(length = 100)
    private String subcategory;
    @Column(name = "article_gender", length = 10)
    private String articleGender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private User publisher;
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticlePicture> articlePictures;

    // no-args constructor
    public Ad() {
    }

    // getters and setters
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

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleState() {
        return articleState;
    }

    public void setArticleState(String articleState) {
        this.articleState = articleState;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal d) {
        this.price = d;
    }

    public AdStatus getStatus() {
        return status;
    }

    public void setStatus(AdStatus status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getArticleGender() {
        return articleGender;
    }

    public void setArticleGender(String articleGender) {
        this.articleGender = articleGender;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public List<ArticlePicture> getArticlePictures() {
        return articlePictures;
    }

    public void setArticlePictures(List<ArticlePicture> articlePictures) {
        this.articlePictures = articlePictures;
    }

    // toString
    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", articleState='" + articleState + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", status=" + status +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", articleGender='" + articleGender + '\'' +
                ", publisher=" + publisher +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Ad ad))
            return false;
        return Objects.equals(title, ad.title) && Objects.equals(articleDescription, ad.articleDescription)
                && Objects.equals(articleState, ad.articleState) && Objects.equals(creationDate, ad.creationDate)
                && Objects.equals(price, ad.price) && status == ad.status && Objects.equals(category, ad.category)
                && Objects.equals(subcategory, ad.subcategory) && Objects.equals(articleGender, ad.articleGender)
                && Objects.equals(publisher, ad.publisher);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(title, articleDescription, articleState, creationDate, price, status, category, subcategory,
                articleGender, publisher);
    }
}
