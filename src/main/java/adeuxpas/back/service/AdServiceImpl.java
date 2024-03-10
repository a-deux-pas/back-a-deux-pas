package adeuxpas.back.service;

import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.dto.mapper.MapStructMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService{

    private final AdRepository adRepository;
    private final MapStructMapper mapper;

    public AdServiceImpl(@Autowired AdRepository adRepository,
                         @Autowired MapStructMapper mapper){
        this.adRepository = adRepository;
        this.mapper = mapper;
    }

    @Override
    public List<HomePageAdDTO> findAllAds() {
        List<Ad> myAds = this.adRepository.findAll();
        List<HomePageAdDTO> mappedList = myAds.stream().map(mapper::adToHomePageAdDTO).collect(Collectors.toList());
        if(mappedList.size() > 1) this.sortByCreationDateDesc(mappedList);

        return mappedList;
    }

    private void sortByCreationDateDesc(List<HomePageAdDTO> myAds) {
        myAds.sort((a, b) -> {
           if (a.getCreationDate().isBefore(b.getCreationDate())) return 1;
           else if (a.getCreationDate().isAfter(b.getCreationDate())) return -1;
           else return 0;
        });
    }
}
