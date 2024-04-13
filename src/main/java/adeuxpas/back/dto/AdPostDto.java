package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.UserRepository;

public class AdPostDto {
    private Long id;
    private String title;
    private String articleDescription;
    private String creationDate;
    private BigDecimal price;
    private String category;
    private String subcategory;
    private String articleGender;
    private long publisherId;
    private List<ArticlePictureDTO> articlePictures;
    private String articleState;
    private AdStatus status = AdStatus.AVAILABLE;

    // A enlever une fois la partie connexion integré
    private UserRepository userRepository;

    // no-args constructor
    public AdPostDto() {
    }

    // args constructor
    public AdPostDto(Long id, String title, String articleDescription, String creationDate, BigDecimal price,
            String category, String articleGender, long publisherId, List<ArticlePictureDTO> articlePictures,
            String articleState, AdStatus status) {
        this.id = id;
        this.title = title;
        this.articleDescription = articleDescription;
        this.creationDate = creationDate;
        this.price = price;
        this.category = category;
        this.articleGender = articleGender;
        this.publisherId = publisherId;
        this.articlePictures = articlePictures;
        this.articleState = articleState;
        this.status = status;

    }

    // args constructor
    // public AdPostDto(UserRepository userRepository) {
    // this.userRepository = userRepository;
    // }

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal bigDecimal) {
        this.price = bigDecimal;
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

    public long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public List<ArticlePictureDTO> getArticlePictures() {
        return this.articlePictures;
    }

    public void setArticlePictures(List<ArticlePictureDTO> articlePictures) {
        this.articlePictures = articlePictures;
    }

    @Override
    public String toString() {
        return "AdPostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", articleState='" + articleState + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                // ", status=" + status +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", articleGender='" + articleGender + '\'' +
                ", publisherId=" + publisherId +
                ", articlePictures=" + articlePictures +
                '}';
    }

    // private LocalDateTime convertStringToLocalDateTime(String data) {
    // return LocalDateTime.parse(data);
    // }

    // private User getUserMircea(Long publisherId) {
    // return userRepository.findById(publisherId).orElse(null);
    // }

}
