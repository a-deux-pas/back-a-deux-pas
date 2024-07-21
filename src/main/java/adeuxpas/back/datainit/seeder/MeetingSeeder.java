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

        List<Ad> existingAds = adRepository.findAll();

        // user2  => Donia
        // Proposed
        Meeting firstMeeting = new Meeting();
        firstMeeting.setBuyer(buyer2);//donia == user
        firstMeeting.setSeller(seller1);//mircea
        firstMeeting.setMeetingPlace(meetingPlace1);
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
        sixthMeeting.setDate(LocalDateTime.now().minusDays(2));
        sixthMeeting.setStatus(MeetingStatus.FINALIZED);
        sixthMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        sixthMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        sixthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        sixthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        sixthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(5))));

        // User1 => Mircea

        // Proposed
        Meeting seventhMeeting = new Meeting();
        seventhMeeting.setBuyer(buyer1);//mircea == user
        seventhMeeting.setSeller(seller2);//DONIA
        seventhMeeting.setMeetingPlace(meetingPlace1);
        seventhMeeting.setDate(LocalDateTime.now().minusDays(2));
        seventhMeeting.setStatus(MeetingStatus.INITIALIZED);
        seventhMeeting.setBuyerDistinctiveSign("Je suis chauve et je porte des lunettes rouges.");
        seventhMeeting.setSellerDistinctiveSign("Je porterai un grand cabas doré.");
        seventhMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        seventhMeeting.setSellerAdditionalInfo("Merci de porter un masque.");
        seventhMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(6))));

        // ToConfirm
        Meeting eighthMeeting = new Meeting();
        eighthMeeting.setBuyer(buyer3);//lea
        eighthMeeting.setSeller(seller1);//mircea == user
        eighthMeeting.setMeetingPlace(meetingPlace2);
        eighthMeeting.setDate(LocalDateTime.now());
        eighthMeeting.setStatus(MeetingStatus.INITIALIZED);
        eighthMeeting.setBuyerDistinctiveSign("Je porterai un chapeau bleu.");
        eighthMeeting.setSellerDistinctiveSign("Je porterai un manteau vert.");
        eighthMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        eighthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        eighthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(7))));

        // Planned
        Meeting ninthMeeting = new Meeting();
        ninthMeeting.setBuyer(buyer1);// mircea == user
        ninthMeeting.setSeller(seller4);// eri
        ninthMeeting.setMeetingPlace(meetingPlace3);
        ninthMeeting.setDate(LocalDateTime.now());
        ninthMeeting.setStatus(MeetingStatus.ACCEPTED);
        ninthMeeting.setBuyerDistinctiveSign("Je porterai une écharpe rouge.");
        ninthMeeting.setSellerDistinctiveSign("Je porterai un sac à dos noir.");
        ninthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        ninthMeeting.setSellerAdditionalInfo("Merci de confirmer l'heure du rendez-vous.");
        ninthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(8))));

        Meeting tenth = new Meeting();
        tenth.setBuyer(buyer5);// julien
        tenth.setSeller(seller1);//mircea == user
        tenth.setMeetingPlace(meetingPlace1);
        tenth.setDate(LocalDateTime.now());
        tenth.setStatus(MeetingStatus.ACCEPTED);
        tenth.setBuyerDistinctiveSign("Je porterai une chemise blanche.");
        tenth.setSellerDistinctiveSign("Je porterai des lunettes de soleil.");
        tenth.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        tenth.setSellerAdditionalInfo("Merci de respecter la distance sociale.");
        tenth.setAds(new HashSet<>(Arrays.asList(existingAds.get(9))));

        //FINALIZED
        Meeting eleventh = new Meeting();
        eleventh.setBuyer(buyer6);// cameron
        eleventh.setSeller(seller1);// mircea == user
        eleventh.setMeetingPlace(meetingPlace2);
        eleventh.setDate(LocalDateTime.now().minusDays(1));
        eleventh.setStatus(MeetingStatus.FINALIZED);
        eleventh.setBuyerDistinctiveSign("Je porterai un pull rouge.");
        eleventh.setSellerDistinctiveSign("Je porterai un manteau noir.");
        eleventh.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        eleventh.setSellerAdditionalInfo("Merci d'apporter l'article.");
        eleventh.setAds(new HashSet<>(Arrays.asList(existingAds.get(10))));

        Meeting twelfth = new Meeting();
        twelfth.setBuyer(buyer1); // mircea == user
        twelfth.setSeller(seller7); // sofia
        twelfth.setMeetingPlace(meetingPlace3);
        twelfth.setDate(LocalDateTime.now().minusDays(2));
        twelfth.setStatus(MeetingStatus.FINALIZED);
        twelfth.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        twelfth.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        twelfth.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        twelfth.setSellerAdditionalInfo("Merci de venir à l'heure.");
        twelfth.setAds(new HashSet<>(Arrays.asList(existingAds.get(11))));


        //User3 = Lea
        // Proposed
        Meeting thirteenthMeeting = new Meeting();
        thirteenthMeeting.setBuyer(buyer3);//lea == user
        thirteenthMeeting.setSeller(seller1);//mircea
        thirteenthMeeting.setMeetingPlace(meetingPlace1);
        thirteenthMeeting.setDate(LocalDateTime.now().minusDays(2));
        thirteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        thirteenthMeeting.setBuyerDistinctiveSign("Je suis chauve et je porte des lunettes rouges.");
        thirteenthMeeting.setSellerDistinctiveSign("Je porterai un grand cabas doré.");
        thirteenthMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        thirteenthMeeting.setSellerAdditionalInfo("Merci de porter un masque.");
        thirteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(12))));

        // ToConfirm
        Meeting fourteenthMeeting = new Meeting();
        fourteenthMeeting.setBuyer(buyer2);//donia
        fourteenthMeeting.setSeller(seller3);//lea == user
        fourteenthMeeting.setMeetingPlace(meetingPlace2);
        fourteenthMeeting.setDate(LocalDateTime.now());
        fourteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        fourteenthMeeting.setBuyerDistinctiveSign("Je porterai un chapeau bleu.");
        fourteenthMeeting.setSellerDistinctiveSign("Je porterai un manteau vert.");
        fourteenthMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        fourteenthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        fourteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(13))));

        // Planned
        Meeting fifteenthMeeting = new Meeting();
        fifteenthMeeting.setBuyer(buyer3);// lea == user
        fifteenthMeeting.setSeller(seller4);// eri
        fifteenthMeeting.setMeetingPlace(meetingPlace3);
        fifteenthMeeting.setDate(LocalDateTime.now());
        fifteenthMeeting.setStatus(MeetingStatus.ACCEPTED);
        fifteenthMeeting.setBuyerDistinctiveSign("Je porterai une écharpe rouge.");
        fifteenthMeeting.setSellerDistinctiveSign("Je porterai un sac à dos noir.");
        fifteenthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        fifteenthMeeting.setSellerAdditionalInfo("Merci de confirmer l'heure du rendez-vous.");
        fifteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(14))));

        Meeting sixteenthMeeting = new Meeting();
        sixteenthMeeting.setBuyer(buyer5);// julien
        sixteenthMeeting.setSeller(seller3);//lea == user
        sixteenthMeeting.setMeetingPlace(meetingPlace1);
        sixteenthMeeting.setDate(LocalDateTime.now());
        sixteenthMeeting.setStatus(MeetingStatus.ACCEPTED);
        sixteenthMeeting.setBuyerDistinctiveSign("Je porterai une chemise blanche.");
        sixteenthMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil.");
        sixteenthMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        sixteenthMeeting.setSellerAdditionalInfo("Merci de respecter la distance sociale.");
        sixteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(15))));

        //FINALIZED
        Meeting seventeenthMeeting = new Meeting();
        seventeenthMeeting.setBuyer(buyer6);// cameron
        seventeenthMeeting.setSeller(seller3);// lea == user
        seventeenthMeeting.setMeetingPlace(meetingPlace2);
        seventeenthMeeting.setDate(LocalDateTime.now().minusDays(1));
        seventeenthMeeting.setStatus(MeetingStatus.FINALIZED);
        seventeenthMeeting.setBuyerDistinctiveSign("Je porterai un pull rouge.");
        seventeenthMeeting.setSellerDistinctiveSign("Je porterai un manteau noir.");
        seventeenthMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        seventeenthMeeting.setSellerAdditionalInfo("Merci d'apporter l'article.");
        seventeenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(16))));

        Meeting eighteenthMeeting = new Meeting();
        eighteenthMeeting.setBuyer(buyer3); // lea == user
        eighteenthMeeting.setSeller(seller7); // sofia
        eighteenthMeeting.setMeetingPlace(meetingPlace3);
        eighteenthMeeting.setDate(LocalDateTime.now().minusDays(2));
        eighteenthMeeting.setStatus(MeetingStatus.FINALIZED);
        eighteenthMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        eighteenthMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        eighteenthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        eighteenthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        eighteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(17))));

        //User4 = Erika
        // Proposed
        Meeting nineteenthMeeting = new Meeting();
        nineteenthMeeting.setBuyer(buyer4);//Erika == user
        nineteenthMeeting.setSeller(seller1);//mircea
        nineteenthMeeting.setMeetingPlace(meetingPlace1);
        nineteenthMeeting.setDate(LocalDateTime.now().minusDays(2));
        nineteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        nineteenthMeeting.setBuyerDistinctiveSign("Je suis chauve et je porte des lunettes rouges.");
        nineteenthMeeting.setSellerDistinctiveSign("Je porterai un grand cabas doré.");
        nineteenthMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        nineteenthMeeting.setSellerAdditionalInfo("Merci de porter un masque.");
        nineteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(18))));

        // ToConfirm
        Meeting twentiethMeeting = new Meeting();
        twentiethMeeting.setBuyer(buyer2);//donia
        twentiethMeeting.setSeller(seller4);//Erika == user
        twentiethMeeting.setMeetingPlace(meetingPlace2);
        twentiethMeeting.setDate(LocalDateTime.now());
        twentiethMeeting.setStatus(MeetingStatus.INITIALIZED);
        twentiethMeeting.setBuyerDistinctiveSign("Je porterai un chapeau bleu.");
        twentiethMeeting.setSellerDistinctiveSign("Je porterai un manteau vert.");
        twentiethMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        twentiethMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        twentiethMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(19))));

        // Planned
        Meeting twentyfirstMeeting = new Meeting();
        twentyfirstMeeting.setBuyer(buyer4);// Erika == user
        twentyfirstMeeting.setSeller(seller4);// eri
        twentyfirstMeeting.setMeetingPlace(meetingPlace3);
        twentyfirstMeeting.setDate(LocalDateTime.now());
        twentyfirstMeeting.setStatus(MeetingStatus.ACCEPTED);
        twentyfirstMeeting.setBuyerDistinctiveSign("Je porterai une écharpe rouge.");
        twentyfirstMeeting.setSellerDistinctiveSign("Je porterai un sac à dos noir.");
        twentyfirstMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        twentyfirstMeeting.setSellerAdditionalInfo("Merci de confirmer l'heure du rendez-vous.");
        twentyfirstMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(20))));

        Meeting twentysecondMeeting = new Meeting();
        twentysecondMeeting.setBuyer(buyer5);// julien
        twentysecondMeeting.setSeller(seller4);// Erika == user
        twentysecondMeeting.setMeetingPlace(meetingPlace1);
        twentysecondMeeting.setDate(LocalDateTime.now());
        twentysecondMeeting.setStatus(MeetingStatus.ACCEPTED);
        twentysecondMeeting.setBuyerDistinctiveSign("Je porterai une chemise blanche.");
        twentysecondMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil.");
        twentysecondMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        twentysecondMeeting.setSellerAdditionalInfo("Merci de respecter la distance sociale.");
        twentysecondMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(21))));

        //FINALIZED
        Meeting twentythirdMeeting = new Meeting();
        twentythirdMeeting.setBuyer(buyer6);// cameron
        twentythirdMeeting.setSeller(seller4);// Erika == user
        twentythirdMeeting.setMeetingPlace(meetingPlace2);
        twentythirdMeeting.setDate(LocalDateTime.now().minusDays(1));
        twentythirdMeeting.setStatus(MeetingStatus.FINALIZED);
        twentythirdMeeting.setBuyerDistinctiveSign("Je porterai un pull rouge.");
        twentythirdMeeting.setSellerDistinctiveSign("Je porterai un manteau noir.");
        twentythirdMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        twentythirdMeeting.setSellerAdditionalInfo("Merci d'apporter l'article.");
        twentythirdMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(22))));

        Meeting twentyfourthMeeting = new Meeting();
        twentyfourthMeeting.setBuyer(buyer4); // Erika == user
        twentyfourthMeeting.setSeller(seller7); // sofia
        twentyfourthMeeting.setMeetingPlace(meetingPlace3);
        twentyfourthMeeting.setDate(LocalDateTime.now().minusDays(2));
        twentyfourthMeeting.setStatus(MeetingStatus.FINALIZED);
        twentyfourthMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        twentyfourthMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        twentyfourthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        twentyfourthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        twentyfourthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(23))));





        List<Meeting> meetings = Arrays.asList(
                firstMeeting, secondMeeting, thirdMeeting, fourthMeeting, fifthMeeting,
                sixthMeeting, seventhMeeting, eighthMeeting, ninthMeeting, tenth,
                eleventh, twelfth, thirteenthMeeting, fourteenthMeeting, fifteenthMeeting,
                sixteenthMeeting, seventeenthMeeting, eighteenthMeeting, nineteenthMeeting,
                twentiethMeeting, twentyfirstMeeting, twentysecondMeeting, twentythirdMeeting,
                twentyfourthMeeting
        );
        this.meetingRepository.saveAll(meetings);
    }
}
