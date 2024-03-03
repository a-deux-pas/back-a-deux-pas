package adeuxpas.back.entity;

import adeuxpas.back.enums.AdStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ad {

    @Id
    private Long id;
    private String title;
    @Column(name = "article_description")
    private String articleDescription;
    @Column(name = "article_state")
    private String articleState;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    private double price;
    private AdStatus status;
    private String category;
    private String subcategory;
    @Column(name = "article_gender")
    private String articleGender;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private User publisher;


    // no-args constructor
    public Ad(){}


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
        if (this == o) return true;
        if (!(o instanceof Ad ad)) return false;
        return Double.compare(price, ad.price) == 0 && Objects.equals(title, ad.title) && Objects.equals(articleDescription, ad.articleDescription)
                                                    && Objects.equals(articleState, ad.articleState)
                                                    && Objects.equals(creationDate, ad.creationDate)
                                                    && status == ad.status && Objects.equals(category, ad.category)
                                                    && Objects.equals(subcategory, ad.subcategory)
                                                    && Objects.equals(articleGender, ad.articleGender)
                                                    && Objects.equals(publisher, ad.publisher);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(title, articleDescription, articleState,
                            creationDate, price, status, category,
                            subcategory, articleGender, publisher);
    }
}
