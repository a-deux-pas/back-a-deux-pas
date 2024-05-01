package adeuxpas.back.entity;

import adeuxpas.back.enums.AdStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 150)
    private String title;
    @NotNull
    @Column(name = "article_description", columnDefinition = "TEXT")
    private String articleDescription;
    @NotNull
    @Column(name = "article_state", length = 150)
    private String articleState;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private AdStatus status;
    @Column(length = 100)
    private String category;
    @NotNull
    @Column(length = 100)
    private String subcategory;
    @Column(name = "article_gender", length = 10)
    private String articleGender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private User publisher;
    @NotEmpty
    @JsonBackReference
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticlePicture> articlePictures;

    // no-args constructor
    public Ad() {
    }

    /**
     * args - constructor
     * 
     * @param title
     * @param articleDescription
     * @param articleState
     * @param creationDate
     * @param price
     * @param status
     * @param category
     * @param subcategory
     * @param articleGender
     * @param publisher
     * @param articlePictures
     */
    public Ad(String title, String articleDescription, String articleState, LocalDateTime creationDate,
            BigDecimal price, AdStatus status, String category, String subcategory, String articleGender,
            User publisher, List<ArticlePicture> articlePictures) {
        this.title = title;
        this.articleDescription = articleDescription;
        this.articleState = articleState;
        this.creationDate = creationDate;
        this.price = price;
        this.status = status;
        this.category = category;
        this.subcategory = subcategory;
        this.articleGender = articleGender;
        this.publisher = publisher;
        this.articlePictures = articlePictures;
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
}
