package adeuxpas.back.service;

import adeuxpas.back.dto.AdDto;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService{

    private final AdRepository repo;
    private AdMapper mapper;

    public AdServiceImpl(@Autowired AdRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Ad> findAllAds() {
        List<Ad> myAds = this.repo.findAll();
        if(myAds.size() > 1) sortByCreationDateDesc(myAds);
        return myAds;
    }

    private static void sortByCreationDateDesc(List<Ad> myAds) {
        myAds.sort((a, b) -> {
           if (a.getCreationDate().isBefore(b.getCreationDate())) return 1;
           else if (a.getCreationDate().isAfter(b.getCreationDate())) return -1;
           else return 0;
        });
    }

    @Override
    public Optional<Ad> postAd(AdDto adDto) {
        Ad ad = this.mapper.TransformAdDtoInAdEntity(adDto);
        Ad savedAd = repo.save(ad);
        return Optional.ofNullable(savedAd);
    }
}
