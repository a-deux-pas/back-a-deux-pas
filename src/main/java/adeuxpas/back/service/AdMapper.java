package adeuxpas.back.service;

import org.springframework.stereotype.Service;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.entity.Ad;

@Service
public class AdMapper {
    public AdPostDto TransformAdEntityInAdDto(Ad ad) {
        AdPostDto adDto = new AdPostDto();
        // adDto.setId(ad.getId());
        adDto.setTitle(ad.getTitle());
        adDto.setArticleDescription(ad.getArticleDescription());
        adDto.setArticleState(ad.getArticleState());
        adDto.setCreationDate(ad.getCreationDate());
        adDto.setPrice(ad.getPrice());
        adDto.setStatus(ad.getStatus());
        adDto.setCategory(ad.getCategory());
        adDto.setCategory(ad.getSubcategory());
        adDto.setArticleState(ad.getArticleGender());
        return adDto;
    }

    public Ad TransformAdDtoInAdEntity(AdPostDto adDto) {
        Ad ad = new Ad();
        // ad.setId(adDto.getId());
        ad.setTitle(adDto.getTitle());
        ad.setArticleDescription(adDto.getArticleDescription());
        ad.setArticleState(adDto.getArticleState());
        ad.setCreationDate(adDto.getCreationDate());
        ad.setPrice(adDto.getPrice());
        ad.setStatus(adDto.getStatus());
        ad.setCategory(adDto.getCategory());
        ad.setSubcategory(adDto.getCategory());
        ad.setArticleGender(adDto.getArticleState());
        return ad;
    }
}
