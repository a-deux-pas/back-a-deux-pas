package adeuxpas.back.service;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService{

    private final AdRepository adRepository;

    public AdServiceImpl(@Autowired AdRepository adRepository){
        this.adRepository = adRepository;
    }

    @Override
    public List<Ad> findAllAds() {
        List<Ad> myAds = this.adRepository.findAll();
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
}
