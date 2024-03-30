package adeuxpas.back.service;

import adeuxpas.back.dto.AdPostDto;
import adeuxpas.back.dto.HomePageAdDTO;
import adeuxpas.back.dto.mapper.MapStructMapper;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private MapStructMapper mapStruct;
    private final AdRepository adRepository;
    private UserService userService;

    public AdServiceImpl(
            @Autowired MapStructMapper mapStruct,
            @Autowired AdRepository adRepository,
            @Autowired UserService userService) {
        this.mapStruct = mapStruct;
        this.adRepository = adRepository;
        this.userService = userService;
    }

    @Override
    public List<HomePageAdDTO> findAllHomePageAds() {
        List<Ad> myAds = this.adRepository.findAll();
        List<HomePageAdDTO> mappedAdsList = myAds.stream().map(mapStruct::adToHomePageAdDTO)
                .collect(Collectors.toList());
        if (mappedAdsList.size() > 1)
            this.sortByCreationDateDesc(mappedAdsList);

        return mappedAdsList;
    }

    private void sortByCreationDateDesc(List<HomePageAdDTO> myAds) {
        myAds.sort((a, b) -> {
            if (a.getCreationDate().isBefore(b.getCreationDate()))
                return 1;
            else if (a.getCreationDate().isAfter(b.getCreationDate()))
                return -1;
            else
                return 0;
        });
    }

    // @Override
    // public Optional<Ad>
    // postAd(AdPostDto adDto)
    {
        // Ad ad = this.mapStruct.adPostDtoToAd(adDto, this.userService);
        // Ad savedAd = adRepository.save(ad);
        // return Optional.ofNullable(savedAd);
    }

}
