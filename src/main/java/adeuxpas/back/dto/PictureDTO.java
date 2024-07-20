package adeuxpas.back.dto;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class PictureDTO {
    @NotBlank
    private Long id;
    @NotBlank
    private MultipartFile file;
    private Long userId;
    private Long adId;
    private String publicId;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return MultipartFile return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return Long return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return Long return the adId
     */
    public Long getAdId() {
        return adId;
    }

    /**
     * @param adId the adId to set
     */
    public void setAdId(Long adId) {
        this.adId = adId;
    }

    /**
     * @return String return the publicId
     */
    public String getPublicId() {
        return publicId;
    }

    /**
     * @param publicId the publicId to set
     */
    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

}
