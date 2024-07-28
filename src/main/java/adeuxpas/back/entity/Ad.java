package adeuxpas.back.entity;

import adeuxpas.back.enums.AdStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Entity class representing an ad in the application.
 * This class encapsulates ad-related information, such as title, article
 * description, article state etc.
 * Instances of this class are persisted to the database by the AdRepository.
 * 
 * @author Mircea Bardan
 */
@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(name = "article_description", columnDefinition = "TEXT", nullable = false)
    private String articleDescription;

    @Column(name = "article_state", length = 150, nullable = false)
    private String articleState;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdStatus status;

    @Column(length = 100, nullable = false)
    private String category;

    @Column(length = 100, nullable = false)
    private String subcategory;

    @Column(name = "article_gender", length = 10)
    private String articleGender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticlePicture> articlePictures;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsersFavoriteAds> usersFavorite;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

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

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public void addArticlePicture(ArticlePicture articlePicture) {
        if (this.articlePictures == null) {
            this.articlePictures = new ArrayList<>();
        }
        this.articlePictures.add(articlePicture);
        articlePicture.setAd(this);
    }

    public Set<UsersFavoriteAds> getUsersFavorite() {
        return usersFavorite;
    }

    public void setUsersFavorite(Set<UsersFavoriteAds> usersFavorite) {
        this.usersFavorite = usersFavorite;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        String articlePicturesString = articlePictures != null
                ? articlePictures.stream()
                        .map(ArticlePicture::toString)
                        .collect(Collectors.joining(", ", "[", "]"))
                : "null";

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
                ", publisher=" + (publisher != null ? publisher.getId() : "null") +
                ", articlePictures=" + articlePicturesString +
                '}';
    }
}
