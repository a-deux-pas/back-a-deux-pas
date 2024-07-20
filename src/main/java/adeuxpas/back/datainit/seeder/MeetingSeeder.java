package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.*;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.*;
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
    private final AdRepository adRepository;

    @Autowired
    public MeetingSeeder(MeetingRepository meetingRepository,
                         UserRepository userRepository,
                         PreferredMeetingPlaceRepository preferredMeetingPlaceRepository,
                         PreferredScheduleRepository preferredScheduleRepository,
                         AdRepository adRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.preferredMeetingPlaceRepository = preferredMeetingPlaceRepository;
        this.preferredScheduleRepository = preferredScheduleRepository;
        this.adRepository = adRepository;
    }

    public void seedMeetings() {
        User buyer1 = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller1 = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));

        User buyer2 = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller2 = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));

        User buyer3 = userRepository.findById(3L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller3 = userRepository.findById(3L).orElseThrow(() -> new RuntimeException("User not found"));

        User buyer4 = userRepository.findById(4L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller4 = userRepository.findById(4L).orElseThrow(() -> new RuntimeException("User not found"));

        User buyer5 = userRepository.findById(5L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller5 = userRepository.findById(5L).orElseThrow(() -> new RuntimeException("User not found"));

        User buyer6 = userRepository.findById(6L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller6 = userRepository.findById(6L).orElseThrow(() -> new RuntimeException("User not found"));

        User buyer7 = userRepository.findById(7L).orElseThrow(() -> new RuntimeException("User not found"));
        User seller7 = userRepository.findById(7L).orElseThrow(() -> new RuntimeException("User not found"));

        PreferredMeetingPlace meetingPlace1 = preferredMeetingPlaceRepository.findById(1L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));
        PreferredMeetingPlace meetingPlace2 = preferredMeetingPlaceRepository.findById(2L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));
        PreferredMeetingPlace meetingPlace3 = preferredMeetingPlaceRepository.findById(3L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));
        PreferredMeetingPlace meetingPlace4 = preferredMeetingPlaceRepository.findById(4L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));
        PreferredMeetingPlace meetingPlace5 = preferredMeetingPlaceRepository.findById(5L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));
        PreferredMeetingPlace meetingPlace6 = preferredMeetingPlaceRepository.findById(6L).orElseThrow(() -> new RuntimeException("Meeting Place not found"));

        PreferredSchedule schedule1 = preferredScheduleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Schedule not found"));
        PreferredSchedule schedule2 = preferredScheduleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Schedule not found"));
        PreferredSchedule schedule3 = preferredScheduleRepository.findById(3L).orElseThrow(() -> new RuntimeException("Schedule not found"));
        PreferredSchedule schedule4 = preferredScheduleRepository.findById(4L).orElseThrow(() -> new RuntimeException("Schedule not found"));
        PreferredSchedule schedule5 = preferredScheduleRepository.findById(5L).orElseThrow(() -> new RuntimeException("Schedule not found"));
        PreferredSchedule schedule6 = preferredScheduleRepository.findById(6L).orElseThrow(() -> new RuntimeException("Schedule not found"));

        List<Ad> existingAds = adRepository.findAll();

        // Proposed
        Meeting firstMeeting = new Meeting();
        firstMeeting.setBuyer(buyer2);//donia == user
        firstMeeting.setSeller(seller1);//mircea
        firstMeeting.setMeetingPlace(meetingPlace1);
        firstMeeting.setSchedule(schedule1);
        firstMeeting.setDate(LocalDateTime.now().minusDays(2));
        firstMeeting.setStatus(MeetingStatus.INITIALIZED);
        firstMeeting.setBuyerDistinctiveSign("Je suis chauve et je porte des lunettes rouges.");
        firstMeeting.setSellerDistinctiveSign("Je porterai un grand cabas doré.");
        firstMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        firstMeeting.setSellerAdditionalInfo("Merci de porter un masque.");
        firstMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(0))));

        // ToConfirm
        Meeting secondMeeting = new Meeting();
        secondMeeting.setBuyer(buyer3);//lea
        secondMeeting.setSeller(seller2);//donia == user
        secondMeeting.setMeetingPlace(meetingPlace2);
        secondMeeting.setSchedule(schedule2);
        secondMeeting.setDate(LocalDateTime.now());
        secondMeeting.setStatus(MeetingStatus.INITIALIZED);
        secondMeeting.setBuyerDistinctiveSign("Je porterai un chapeau bleu.");
        secondMeeting.setSellerDistinctiveSign("Je porterai un manteau vert.");
        secondMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        secondMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        secondMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(1))));

        // Planned
        Meeting thirdMeeting = new Meeting();
        thirdMeeting.setBuyer(buyer2);// donia == user
        thirdMeeting.setSeller(seller4);// eri
        thirdMeeting.setMeetingPlace(meetingPlace3);
        thirdMeeting.setSchedule(schedule3);
        thirdMeeting.setDate(LocalDateTime.now());
        thirdMeeting.setStatus(MeetingStatus.ACCEPTED);
        thirdMeeting.setBuyerDistinctiveSign("Je porterai une écharpe rouge.");
        thirdMeeting.setSellerDistinctiveSign("Je porterai un sac à dos noir.");
        thirdMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        thirdMeeting.setSellerAdditionalInfo("Merci de confirmer l'heure du rendez-vous.");
        thirdMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(2))));

        Meeting fourthMeeting = new Meeting();
        fourthMeeting.setBuyer(buyer5);// julien
        fourthMeeting.setSeller(seller2);//donia == user
        fourthMeeting.setMeetingPlace(meetingPlace1);
        fourthMeeting.setSchedule(schedule2);
        fourthMeeting.setDate(LocalDateTime.now());
        fourthMeeting.setStatus(MeetingStatus.ACCEPTED);
        fourthMeeting.setBuyerDistinctiveSign("Je porterai une chemise blanche.");
        fourthMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil.");
        fourthMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        fourthMeeting.setSellerAdditionalInfo("Merci de respecter la distance sociale.");
        fourthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(3))));

        //FINALIZED
        Meeting fifthMeeting = new Meeting();
        fifthMeeting.setBuyer(buyer6);// cameron
        fifthMeeting.setSeller(seller2);// donia == user
        fifthMeeting.setMeetingPlace(meetingPlace2);
        fifthMeeting.setSchedule(schedule3);
        fifthMeeting.setDate(LocalDateTime.now().minusDays(1));
        fifthMeeting.setStatus(MeetingStatus.FINALIZED);
        fifthMeeting.setBuyerDistinctiveSign("Je porterai un pull rouge.");
        fifthMeeting.setSellerDistinctiveSign("Je porterai un manteau noir.");
        fifthMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        fifthMeeting.setSellerAdditionalInfo("Merci d'apporter l'article.");
        fifthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(4))));

        Meeting sixthMeeting = new Meeting();
        sixthMeeting.setBuyer(buyer2); // donia == user
        sixthMeeting.setSeller(seller7); // sofia
        sixthMeeting.setMeetingPlace(meetingPlace3);
        sixthMeeting.setSchedule(schedule1);
        sixthMeeting.setDate(LocalDateTime.now().minusDays(2));
        sixthMeeting.setStatus(MeetingStatus.FINALIZED);
        sixthMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        sixthMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        sixthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        sixthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        sixthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(5))));

        Meeting seventhMeeting = new Meeting();
        seventhMeeting.setBuyer(buyer5); // julien
        seventhMeeting.setSeller(seller7); // sofia
        seventhMeeting.setMeetingPlace(meetingPlace3);
        seventhMeeting.setSchedule(schedule1);
        seventhMeeting.setDate(LocalDateTime.now());
        seventhMeeting.setStatus(MeetingStatus.FINALIZED);
        seventhMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        seventhMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        seventhMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        seventhMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        seventhMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(6))));

        List<Meeting> meetings = Arrays.asList(firstMeeting, secondMeeting, thirdMeeting, fourthMeeting, fifthMeeting, sixthMeeting, seventhMeeting);
        this.meetingRepository.saveAll(meetings);
    }
}