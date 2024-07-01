package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.Meeting;
import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.MeetingRepository;
import adeuxpas.back.repository.PreferredMeetingPlaceRepository;
import adeuxpas.back.repository.PreferredScheduleRepository;
import adeuxpas.back.repository.UserRepository;
import adeuxpas.back.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class MeetingSeeder {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final PreferredMeetingPlaceRepository preferredMeetingPlaceRepository;
    private final PreferredScheduleRepository preferredScheduleRepository;
    private final AdSeeder adSeeder;

    @Autowired
    public MeetingSeeder(MeetingRepository meetingRepository,
                         UserRepository userRepository,
                         PreferredMeetingPlaceRepository preferredMeetingPlaceRepository,
                         PreferredScheduleRepository preferredScheduleRepository,
                         AdSeeder adSeeder,
                         AdRepository adRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.preferredMeetingPlaceRepository = preferredMeetingPlaceRepository;
        this.preferredScheduleRepository = preferredScheduleRepository;
        this.adSeeder = adSeeder;
    }

    /**
     * Creates and seeds hardcoded meetings.
     */
    public void seedMeetings() {
        User buyer1 = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller1 = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));
        User buyer2 = userRepository.findById(3L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller2 = userRepository.findById(4L).orElseThrow(() -> new RuntimeException("User not found"));
        User buyer3 = userRepository.findById(5L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller3 = userRepository.findById(6L).orElseThrow(() -> new RuntimeException("User not found"));

        PreferredMeetingPlace meetingPlace1 = preferredMeetingPlaceRepository.findById(1L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));
        PreferredMeetingPlace meetingPlace2 = preferredMeetingPlaceRepository.findById(2L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));
        PreferredMeetingPlace meetingPlace3 = preferredMeetingPlaceRepository.findById(3L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));

        PreferredSchedule schedule1 = preferredScheduleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Schedule not found"));
        PreferredSchedule schedule2 = preferredScheduleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Schedule not found"));
        PreferredSchedule schedule3 = preferredScheduleRepository.findById(3L).orElseThrow(() -> new RuntimeException("Schedule not found"));

        List<User> users = Arrays.asList(buyer1, seller1, buyer2, seller2, buyer3, seller3);
        List<Ad> ads = adSeeder.createAds(users);
        adSeeder.seedAds(ads);

        Meeting firstMeeting = new Meeting();
        firstMeeting.setBuyer(buyer1);
        firstMeeting.setSeller(seller1);
        firstMeeting.setMeetingPlace(meetingPlace1);
        firstMeeting.setSchedule(schedule1);
        firstMeeting.setDate(LocalDateTime.now().minusDays(2));
        firstMeeting.setStatus(MeetingStatus.INITIALIZED);
        firstMeeting.setBuyerDistinctiveSign("Je suis chauve et je porte des lunettes rouges.");
        firstMeeting.setSellerDistinctiveSign("Je porterai un grand cabas doré.");
        firstMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        firstMeeting.setSellerAdditionalInfo("Merci de porter un masque.");
        firstMeeting.setAds(new HashSet<>(Arrays.asList(ads.get(0))));

        Meeting secondMeeting = new Meeting();
        secondMeeting.setBuyer(buyer2);
        secondMeeting.setSeller(seller2);
        secondMeeting.setMeetingPlace(meetingPlace2);
        secondMeeting.setSchedule(schedule2);
        secondMeeting.setDate(LocalDateTime.now().minusDays(2));
        secondMeeting.setStatus(MeetingStatus.INITIALIZED);
        secondMeeting.setBuyerDistinctiveSign("Je porterai un chapeau bleu.");
        secondMeeting.setSellerDistinctiveSign("Je porterai un manteau vert.");
        secondMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        secondMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        secondMeeting.setAds(new HashSet<>(Arrays.asList(ads.get(1))));

        Meeting thirdMeeting = new Meeting();
        thirdMeeting.setBuyer(buyer3);
        thirdMeeting.setSeller(seller3);
        thirdMeeting.setMeetingPlace(meetingPlace3);
        thirdMeeting.setSchedule(schedule3);
        thirdMeeting.setDate(LocalDateTime.now());
        thirdMeeting.setStatus(MeetingStatus.ACCEPTED);
        thirdMeeting.setBuyerDistinctiveSign("Je porterai une écharpe rouge.");
        thirdMeeting.setSellerDistinctiveSign("Je porterai un sac à dos noir.");
        thirdMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        thirdMeeting.setSellerAdditionalInfo("Merci de confirmer l'heure du rendez-vous.");
        thirdMeeting.setAds(new HashSet<>(Arrays.asList(ads.get(2))));

        Meeting fourthMeeting = new Meeting();
        fourthMeeting.setBuyer(buyer1);
        fourthMeeting.setSeller(seller2);
        fourthMeeting.setMeetingPlace(meetingPlace1);
        fourthMeeting.setSchedule(schedule2);
        fourthMeeting.setDate(LocalDateTime.now());
        fourthMeeting.setStatus(MeetingStatus.INITIALIZED);
        fourthMeeting.setBuyerDistinctiveSign("Je porterai une chemise blanche.");
        fourthMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil.");
        fourthMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        fourthMeeting.setSellerAdditionalInfo("Merci de respecter la distance sociale.");
        fourthMeeting.setAds(new HashSet<>(Arrays.asList(ads.get(3))));

        Meeting fifthMeeting = new Meeting();
        fifthMeeting.setBuyer(buyer2);
        fifthMeeting.setSeller(seller3);
        fifthMeeting.setMeetingPlace(meetingPlace2);
        fifthMeeting.setSchedule(schedule3);
        fifthMeeting.setDate(LocalDateTime.now());
        fifthMeeting.setStatus(MeetingStatus.ACCEPTED);
        fifthMeeting.setBuyerDistinctiveSign("Je porterai un pull rouge.");
        fifthMeeting.setSellerDistinctiveSign("Je porterai un manteau noir.");
        fifthMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        fifthMeeting.setSellerAdditionalInfo("Merci d'apporter l'article.");
        fifthMeeting.setAds(new HashSet<>(Arrays.asList(ads.get(4))));

        Meeting sixthMeeting = new Meeting();
        sixthMeeting.setBuyer(buyer3);
        sixthMeeting.setSeller(seller1);
        sixthMeeting.setMeetingPlace(meetingPlace3);
        sixthMeeting.setSchedule(schedule1);
        sixthMeeting.setDate(LocalDateTime.now());
        sixthMeeting.setStatus(MeetingStatus.INITIALIZED);
        sixthMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        sixthMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        sixthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        sixthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        sixthMeeting.setAds(new HashSet<>(Arrays.asList(ads.get(5))));

        List<Meeting> meetings = Arrays.asList(firstMeeting, secondMeeting, thirdMeeting, fourthMeeting, fifthMeeting, sixthMeeting);
        this.meetingRepository.saveAll(meetings);
    }
}
