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

    public MeetingSeeder(
            @Autowired MeetingRepository meetingRepository,
            @Autowired UserRepository userRepository,
            @Autowired PreferredMeetingPlaceRepository preferredMeetingPlaceRepository,
            @Autowired AdRepository adRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.preferredMeetingPlaceRepository = preferredMeetingPlaceRepository;
        this.adRepository = adRepository;
    }

    public void seedMeetings() {
        User buyer1 = userRepository.findById(1L).get();
        User seller1 = userRepository.findById(1L).get();

        User buyer2 = userRepository.findById(2L).get();
        User seller2 = userRepository.findById(2L).get();

        User buyer3 = userRepository.findById(3L).get();
        User seller3 = userRepository.findById(3L).get();

        User buyer4 = userRepository.findById(4L).get();
        User seller4 = userRepository.findById(4L).get();

        User buyer5 = userRepository.findById(5L).get();

        User buyer6 = userRepository.findById(6L).get();

        User seller7 = userRepository.findById(7L).get();

        PreferredMeetingPlace meetingPlace1 = preferredMeetingPlaceRepository.findById(1L).get();
        PreferredMeetingPlace meetingPlace2 = preferredMeetingPlaceRepository.findById(2L).get();
        PreferredMeetingPlace meetingPlace3 = preferredMeetingPlaceRepository.findById(3L).get();
        PreferredMeetingPlace meetingPlace4 = preferredMeetingPlaceRepository.findById(4L).get();
        PreferredMeetingPlace meetingPlace5 = preferredMeetingPlaceRepository.findById(5L).get();
        PreferredMeetingPlace meetingPlace6 = preferredMeetingPlaceRepository.findById(6L).get();

        List<Ad> existingAds = adRepository.findAll();

        // user2 => Donia
        // Proposed
        Meeting firstMeeting = new Meeting();
        firstMeeting.setBuyer(buyer2);// donia == user
        firstMeeting.setSeller(seller1);// mircea
        firstMeeting.setMeetingPlace(meetingPlace1);
        firstMeeting.setDate(LocalDateTime.now().plusDays(3));
        firstMeeting.setStatus(MeetingStatus.INITIALIZED);
        firstMeeting.setBuyerDistinctiveSign("Je suis chauve et je porte des lunettes rouges.");
        firstMeeting.setSellerDistinctiveSign("Je porterai un grand cabas doré.");
        firstMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        firstMeeting.setSellerAdditionalInfo("Merci de porter un masque.");
        firstMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(0))));

        // ToConfirm
        Meeting secondMeeting = new Meeting();
        secondMeeting.setBuyer(buyer3);// lea
        secondMeeting.setSeller(seller2);// donia == user
        secondMeeting.setMeetingPlace(meetingPlace2);
        secondMeeting.setDate(LocalDateTime.now().plusDays(3));
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
        fourthMeeting.setSeller(seller2);// donia == user
        fourthMeeting.setMeetingPlace(meetingPlace4);
        fourthMeeting.setDate(LocalDateTime.now().plusDays(3));
        fourthMeeting.setStatus(MeetingStatus.ACCEPTED);
        fourthMeeting.setBuyerDistinctiveSign("Je porterai une chemise blanche.");
        fourthMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil.");
        fourthMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        fourthMeeting.setSellerAdditionalInfo("Merci de respecter la distance sociale.");
        fourthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(3))));

        // Finalized
        Meeting fifthMeeting = new Meeting();
        fifthMeeting.setBuyer(buyer6);// cameron
        fifthMeeting.setSeller(seller2);// donia == user
        fifthMeeting.setMeetingPlace(meetingPlace5);
        fifthMeeting.setDate(LocalDateTime.now().minusDays(1));
        fifthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        fifthMeeting.setBuyerDistinctiveSign("Je porterai un pull rouge.");
        fifthMeeting.setSellerDistinctiveSign("Je porterai un manteau noir.");
        fifthMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        fifthMeeting.setSellerAdditionalInfo("Merci d'apporter l'article.");
        fifthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(4))));

        Meeting sixthMeeting = new Meeting();
        sixthMeeting.setBuyer(buyer2); // donia == user
        sixthMeeting.setSeller(seller7); // sofia
        sixthMeeting.setMeetingPlace(meetingPlace6);
        sixthMeeting.setDate(LocalDateTime.now().minusDays(2));
        sixthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        sixthMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        sixthMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        sixthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        sixthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        sixthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(5))));

        // User1 => Mircea
        // Proposed
        Meeting seventhMeeting = new Meeting();
        seventhMeeting.setBuyer(buyer1);// mircea == user
        seventhMeeting.setSeller(seller2);// DONIA
        seventhMeeting.setMeetingPlace(meetingPlace1);
        seventhMeeting.setDate(LocalDateTime.now().plusDays(3));
        seventhMeeting.setStatus(MeetingStatus.INITIALIZED);
        seventhMeeting.setBuyerDistinctiveSign("Je porterai un manteau noir et une écharpe bleue.");
        seventhMeeting.setSellerDistinctiveSign("J'aurai une mallette en cuir marron.");
        seventhMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement bancaire.");
        seventhMeeting.setSellerAdditionalInfo("Merci d'apporter la facture originale si possible.");
        seventhMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(6))));

        // ToConfirm
        Meeting eighthMeeting = new Meeting();
        eighthMeeting.setBuyer(buyer3);// lea
        eighthMeeting.setSeller(seller1);// mircea == user
        eighthMeeting.setMeetingPlace(meetingPlace2);
        eighthMeeting.setDate(LocalDateTime.now().plusDays(2));
        eighthMeeting.setStatus(MeetingStatus.INITIALIZED);
        eighthMeeting.setBuyerDistinctiveSign("Je serai en costume gris avec une cravate rouge.");
        eighthMeeting.setSellerDistinctiveSign("Je porterai un sac à dos noir de marque.");
        eighthMeeting.setBuyerAdditionalInfo("Je souhaite examiner l'objet en détail avant l'achat.");
        eighthMeeting.setSellerAdditionalInfo("Pouvez-vous fournir un certificat d'authenticité ?");
        eighthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(7))));

        // Planned
        Meeting ninthMeeting = new Meeting();
        ninthMeeting.setBuyer(buyer1);// mircea == user
        ninthMeeting.setSeller(seller4);// eri
        ninthMeeting.setMeetingPlace(meetingPlace3);
        ninthMeeting.setDate(LocalDateTime.now().plusDays(3));
        ninthMeeting.setStatus(MeetingStatus.ACCEPTED);
        ninthMeeting.setBuyerDistinctiveSign("J'aurai une veste en jean et des lunettes de vue.");
        ninthMeeting.setSellerDistinctiveSign("Je tiendrai un parapluie noir classique.");
        ninthMeeting.setBuyerAdditionalInfo("Je peux payer en espèces ou par carte bancaire.");
        ninthMeeting.setSellerAdditionalInfo("Merci de prévoir un reçu pour la transaction.");
        ninthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(8))));

        Meeting tenth = new Meeting();
        tenth.setBuyer(buyer5);// julien
        tenth.setSeller(seller1);// mircea == user
        tenth.setMeetingPlace(meetingPlace4);
        tenth.setDate(LocalDateTime.now().plusDays(3));
        tenth.setStatus(MeetingStatus.ACCEPTED);
        tenth.setBuyerDistinctiveSign("Je porterai un pull vert foncé et un pantalon beige.");
        tenth.setSellerDistinctiveSign("J'aurai un sac en toile écru sur l'épaule.");
        tenth.setBuyerAdditionalInfo("Je préfère effectuer la transaction dans un lieu public.");
        tenth.setSellerAdditionalInfo("Pouvez-vous apporter l'emballage d'origine ?");
        tenth.setAds(new HashSet<>(Arrays.asList(existingAds.get(9))));

        // Finalized
        Meeting eleventh = new Meeting();
        eleventh.setBuyer(buyer6);// cameron
        eleventh.setSeller(seller1);// mircea == user
        eleventh.setMeetingPlace(meetingPlace5);
        eleventh.setDate(LocalDateTime.now().minusDays(1));
        eleventh.setStatus(MeetingStatus.TOBEFINALIZED);
        eleventh.setBuyerDistinctiveSign("Je serai en chemise blanche et pantalon noir.");
        eleventh.setSellerDistinctiveSign("J'aurai une sacoche pour ordinateur portable.");
        eleventh.setBuyerAdditionalInfo("Je souhaite vérifier le fonctionnement de l'objet sur place.");
        eleventh.setSellerAdditionalInfo("Merci de prévoir une garantie écrite si applicable.");
        eleventh.setAds(new HashSet<>(Arrays.asList(existingAds.get(10))));

        Meeting twelfth = new Meeting();
        twelfth.setBuyer(buyer1); // mircea == user
        twelfth.setSeller(seller7); // sofia
        twelfth.setMeetingPlace(meetingPlace6);
        twelfth.setDate(LocalDateTime.now().minusDays(2));
        twelfth.setStatus(MeetingStatus.TOBEFINALIZED);
        twelfth.setBuyerDistinctiveSign("Je porterai un blouson en cuir marron et un jean.");
        twelfth.setSellerDistinctiveSign("J'aurai des écouteurs autour du cou.");
        twelfth.setBuyerAdditionalInfo("Je peux payer par chèque si vous préférez.");
        twelfth.setSellerAdditionalInfo("Pouvez-vous fournir un historique d'entretien de l'objet ?");
        twelfth.setAds(new HashSet<>(Arrays.asList(existingAds.get(11))));

        // User3 = Lea
        // Proposed
        Meeting thirteenthMeeting = new Meeting();
        thirteenthMeeting.setBuyer(buyer3);// lea == user
        thirteenthMeeting.setSeller(seller1);// mircea
        thirteenthMeeting.setMeetingPlace(meetingPlace1);
        thirteenthMeeting.setDate(LocalDateTime.now().plusDays(3));
        thirteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        thirteenthMeeting.setBuyerDistinctiveSign("J'aurai une casquette bleue marine et des baskets blanches.");
        thirteenthMeeting.setSellerDistinctiveSign("Je porterai une montre argentée visible.");
        thirteenthMeeting.setBuyerAdditionalInfo("Je préfère un échange rapide, mon temps est limité.");
        thirteenthMeeting.setSellerAdditionalInfo("Merci d'informer de tout défaut connu à l'avance.");
        thirteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(12))));

        // ToConfirm
        Meeting fourteenthMeeting = new Meeting();
        fourteenthMeeting.setBuyer(buyer2);// donia
        fourteenthMeeting.setSeller(seller3);// lea == user
        fourteenthMeeting.setMeetingPlace(meetingPlace2);
        fourteenthMeeting.setDate(LocalDateTime.now().plusDays(1));
        fourteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        fourteenthMeeting.setBuyerDistinctiveSign("Je serai en tailleur noir avec une broche dorée.");
        fourteenthMeeting.setSellerDistinctiveSign("J'aurai un porte-documents en cuir noir.");
        fourteenthMeeting.setBuyerAdditionalInfo("Je souhaite un reçu détaillé pour ma comptabilité.");
        fourteenthMeeting.setSellerAdditionalInfo("Pouvez-vous démontrer le fonctionnement de l'objet ?");
        fourteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(13))));

        // Planned
        Meeting fifteenthMeeting = new Meeting();
        fifteenthMeeting.setBuyer(buyer3);// lea == user
        fifteenthMeeting.setSeller(seller4);// eri
        fifteenthMeeting.setMeetingPlace(meetingPlace3);
        fifteenthMeeting.setDate(LocalDateTime.now().plusDays(2));
        fifteenthMeeting.setStatus(MeetingStatus.ACCEPTED);
        fifteenthMeeting.setBuyerDistinctiveSign("J'aurai une casquette bleue marine et des baskets blanches.");
        fifteenthMeeting.setSellerDistinctiveSign("Je porterai une montre argentée visible.");
        fifteenthMeeting.setBuyerAdditionalInfo("Je préfère un échange rapide, mon temps est limité.");
        fifteenthMeeting.setSellerAdditionalInfo("Merci d'informer de tout défaut connu à l'avance.");
        fifteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(14))));

        Meeting sixteenthMeeting = new Meeting();
        sixteenthMeeting.setBuyer(buyer5);// julien
        sixteenthMeeting.setSeller(seller3);// lea == user
        sixteenthMeeting.setMeetingPlace(meetingPlace4);
        sixteenthMeeting.setDate(LocalDateTime.now().plusDays(3));
        sixteenthMeeting.setStatus(MeetingStatus.ACCEPTED);
        sixteenthMeeting.setBuyerDistinctiveSign("Je serai en tailleur noir avec une broche dorée.");
        sixteenthMeeting.setSellerDistinctiveSign("J'aurai un porte-documents en cuir noir.");
        sixteenthMeeting.setBuyerAdditionalInfo("Je souhaite un reçu détaillé pour ma comptabilité.");
        sixteenthMeeting.setSellerAdditionalInfo("Pouvez-vous démontrer le fonctionnement de l'objet ?");
        sixteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(15))));

        // Finalized
        Meeting seventeenthMeeting = new Meeting();
        seventeenthMeeting.setBuyer(buyer6);// cameron
        seventeenthMeeting.setSeller(seller3);// lea == user
        seventeenthMeeting.setMeetingPlace(meetingPlace5);
        seventeenthMeeting.setDate(LocalDateTime.now().minusDays(1));
        seventeenthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        seventeenthMeeting.setBuyerDistinctiveSign("Je porterai un polo bleu ciel et un pantalon kaki.");
        seventeenthMeeting.setSellerDistinctiveSign("J'aurai une sacoche en bandoulière grise.");
        seventeenthMeeting.setBuyerAdditionalInfo("Je préfère payer par virement instantané si possible.");
        seventeenthMeeting.setSellerAdditionalInfo("Merci de préparer tous les accessoires inclus.");
        seventeenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(16))));

        Meeting eighteenthMeeting = new Meeting();
        eighteenthMeeting.setBuyer(buyer3); // lea == user
        eighteenthMeeting.setSeller(seller7); // sofia
        eighteenthMeeting.setMeetingPlace(meetingPlace6);
        eighteenthMeeting.setDate(LocalDateTime.now().minusDays(2));
        eighteenthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        eighteenthMeeting.setBuyerDistinctiveSign("Je serai en robe noire avec un blazer blanc.");
        eighteenthMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil sur la tête.");
        eighteenthMeeting.setBuyerAdditionalInfo("J'apporterai un appareil pour vérifier l'authenticité.");
        eighteenthMeeting.setSellerAdditionalInfo("Pouvez-vous fournir une preuve d'achat ?");
        eighteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(17))));

        // User4 = Erika
        // Proposed
        Meeting nineteenthMeeting = new Meeting();
        nineteenthMeeting.setBuyer(buyer4);// Erika == user
        nineteenthMeeting.setSeller(seller1);// mircea
        nineteenthMeeting.setMeetingPlace(meetingPlace1);
        nineteenthMeeting.setDate(LocalDateTime.now().plusDays(3));
        nineteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        nineteenthMeeting.setBuyerDistinctiveSign("J'aurai une écharpe rouge et des gants en cuir.");
        nineteenthMeeting.setSellerDistinctiveSign("Je tiendrai un journal sous le bras.");
        nineteenthMeeting.setBuyerAdditionalInfo("Je préfère négocier le prix final après inspection.");
        nineteenthMeeting.setSellerAdditionalInfo("Merci de prévoir un emballage sécurisé pour le transport.");
        nineteenthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(18))));

        // ToConfirm
        Meeting twentiethMeeting = new Meeting();
        twentiethMeeting.setBuyer(buyer2);// donia
        twentiethMeeting.setSeller(seller4);// Erika == user
        twentiethMeeting.setMeetingPlace(meetingPlace2);
        twentiethMeeting.setDate(LocalDateTime.now().plusDays(3));
        twentiethMeeting.setStatus(MeetingStatus.INITIALIZED);
        twentiethMeeting.setBuyerDistinctiveSign("Je serai en costume bleu marine avec une pochette.");
        twentiethMeeting.setSellerDistinctiveSign("J'aurai une cravate à motifs géométriques.");
        twentiethMeeting.setBuyerAdditionalInfo("Je peux payer par PayPal ou virement bancaire.");
        twentiethMeeting.setSellerAdditionalInfo("Pouvez-vous fournir les spécifications techniques de l'objet ?");
        twentiethMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(19))));

        // Planned
        Meeting twentyfirstMeeting = new Meeting();
        twentyfirstMeeting.setBuyer(buyer4);// Erika == user
        twentyfirstMeeting.setSeller(seller7);// eri
        twentyfirstMeeting.setMeetingPlace(meetingPlace3);
        twentyfirstMeeting.setDate(LocalDateTime.now().plusDays(3));
        twentyfirstMeeting.setStatus(MeetingStatus.ACCEPTED);
        twentyfirstMeeting.setBuyerDistinctiveSign("Je porterai un trench beige et des chaussures marron.");
        twentyfirstMeeting.setSellerDistinctiveSign("J'aurai un parapluie rétractable noir.");
        twentyfirstMeeting.setBuyerAdditionalInfo("Je souhaite effectuer la transaction dans un café calme.");
        twentyfirstMeeting.setSellerAdditionalInfo("Merci d'apporter toute documentation pertinente.");
        twentyfirstMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(20))));

        Meeting twentysecondMeeting = new Meeting();
        twentysecondMeeting.setBuyer(buyer5);// julien
        twentysecondMeeting.setSeller(seller4);// Erika == user
        twentysecondMeeting.setMeetingPlace(meetingPlace4);
        twentysecondMeeting.setDate(LocalDateTime.now().plusDays(2));
        twentysecondMeeting.setStatus(MeetingStatus.ACCEPTED);
        twentysecondMeeting.setBuyerDistinctiveSign("Je serai en pull col roulé noir et pantalon gris.");
        twentysecondMeeting.setSellerDistinctiveSign("Je porterai une montre connectée visible.");
        twentysecondMeeting.setBuyerAdditionalInfo("Je préfère payer avec des billets de petite coupure.");
        twentysecondMeeting.setSellerAdditionalInfo("Pouvez-vous confirmer la compatibilité avec mon équipement ?");
        twentysecondMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(21))));

        // Finalized
        Meeting twentythirdMeeting = new Meeting();
        twentythirdMeeting.setBuyer(buyer6);// cameron
        twentythirdMeeting.setSeller(seller4);// Erika == user
        twentythirdMeeting.setMeetingPlace(meetingPlace5);
        twentythirdMeeting.setDate(LocalDateTime.now().minusDays(1));
        twentythirdMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        twentythirdMeeting.setBuyerDistinctiveSign("J'aurai une veste matelassée bleue et une écharpe grise.");
        twentythirdMeeting.setSellerDistinctiveSign("Je tiendrai un dossier avec des papiers visibles.");
        twentythirdMeeting.setBuyerAdditionalInfo("J'aimerais avoir la possibilité d'un retour sous conditions.");
        twentythirdMeeting.setSellerAdditionalInfo("Merci de prévoir un temps pour répondre à mes questions.");
        twentythirdMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(22))));

        Meeting twentyfourthMeeting = new Meeting();
        twentyfourthMeeting.setBuyer(buyer4); // Erika == user
        twentyfourthMeeting.setSeller(seller7); // sofia
        twentyfourthMeeting.setMeetingPlace(meetingPlace6);
        twentyfourthMeeting.setDate(LocalDateTime.now().minusDays(2));
        twentyfourthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        twentyfourthMeeting.setBuyerDistinctiveSign("Je serai en chemise à carreaux et jean foncé.");
        twentyfourthMeeting.setSellerDistinctiveSign("J'aurai une sacoche pour appareil photo.");
        twentyfourthMeeting.setBuyerAdditionalInfo("Je peux payer par chèque de banque si le montant est élevé.");
        twentyfourthMeeting.setSellerAdditionalInfo("Pouvez-vous fournir un certificat de conformité ?");
        twentyfourthMeeting.setAds(new HashSet<>(Arrays.asList(existingAds.get(23))));

        List<Meeting> meetings = Arrays.asList(
                firstMeeting, secondMeeting, thirdMeeting, fourthMeeting, fifthMeeting,
                sixthMeeting, seventhMeeting, eighthMeeting, ninthMeeting, tenth,
                eleventh, twelfth, thirteenthMeeting, fourteenthMeeting, fifteenthMeeting,
                sixteenthMeeting, seventeenthMeeting, eighteenthMeeting, nineteenthMeeting,
                twentiethMeeting, twentyfirstMeeting, twentysecondMeeting, twentythirdMeeting,
                twentyfourthMeeting);
        this.meetingRepository.saveAll(meetings);
    }
}
