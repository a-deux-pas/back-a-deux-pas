package adeuxpas.back.dto;

import adeuxpas.back.entity.ArticlePicture;

import java.math.BigDecimal;

public class AdResponseForHomePageDTO {
    private String title;
    private ArticlePicture articlePicture;
    private BigDecimal price;
    private String publisherName;
}
