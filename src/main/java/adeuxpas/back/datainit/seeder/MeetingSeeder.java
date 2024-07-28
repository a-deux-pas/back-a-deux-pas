package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.*;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

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
        User user1 = userRepository.findById(1L).get();
        User user2 = userRepository.findById(2L).get();
        User user3 = userRepository.findById(3L).get();
        User user4 = userRepository.findById(4L).get();
        User user5 = userRepository.findById(5L).get();

        List<PreferredMeetingPlace> meetingPlacesUser1 = preferredMeetingPlaceRepository
                .findPreferredMeetingPlacesByUser(user1);
        List<PreferredMeetingPlace> meetingPlacesUser2 = preferredMeetingPlaceRepository
                .findPreferredMeetingPlacesByUser(user2);
        List<PreferredMeetingPlace> meetingPlacesUser3 = preferredMeetingPlaceRepository
                .findPreferredMeetingPlacesByUser(user3);
        List<PreferredMeetingPlace> meetingPlacesUser4 = preferredMeetingPlaceRepository
                .findPreferredMeetingPlacesByUser(user4);

        List<Ad> adsUser1 = adRepository.findAdsByPublisherId(1L);
        List<Ad> adsUser2 = adRepository.findAdsByPublisherId(2L);
        List<Ad> adsUser3 = adRepository.findAdsByPublisherId(3L);
        List<Ad> adsUser4 = adRepository.findAdsByPublisherId(4L);

        // user2 => Donia
        // Proposed
        Meeting firstMeeting = new Meeting();
        firstMeeting.setBuyer(user2);// Dounia == user
        firstMeeting.setSeller(user1);// Koroviev
        firstMeeting.setMeetingPlace(meetingPlacesUser1.get(0));
        firstMeeting.setDate(LocalDateTime.now().plusDays(3));
        firstMeeting.setStatus(MeetingStatus.INITIALIZED);
        firstMeeting.setBuyerDistinctiveSign("Je porterai un grand cabas doré.");
        firstMeeting.setSellerDistinctiveSign("Je porte des lunettes rouges.");
        firstMeeting.setBuyerAdditionalInfo("J'ai hâte de voir l'article en vrai !.");
        firstMeeting.setSellerAdditionalInfo("Merci de porter un masque.");
        firstMeeting.setAds(new HashSet<>(Arrays.asList(adsUser1.get(0))));
        adsUser1.get(0).setStatus(AdStatus.RESERVED);

        // ToConfirm
        Meeting secondMeeting = new Meeting();
        secondMeeting.setBuyer(user1); // Koroviev
        secondMeeting.setSeller(user2); // Dounia == user
        secondMeeting.setMeetingPlace(meetingPlacesUser2.get(0));
        secondMeeting.setDate(LocalDateTime.now().plusDays(3));
        secondMeeting.setStatus(MeetingStatus.INITIALIZED);
        secondMeeting.setBuyerDistinctiveSign("Je porterai un chapeau bleu.");
        secondMeeting.setSellerDistinctiveSign("Je porterai un manteau vert.");
        secondMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        secondMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        secondMeeting.setAds(new HashSet<>(Arrays.asList(adsUser2.get(0))));
        adsUser2.get(0).setStatus(AdStatus.RESERVED);

        // Planned
        Meeting fourthMeeting = new Meeting();
        fourthMeeting.setBuyer(user1); // Koroviev
        fourthMeeting.setSeller(user2); // Dounia == user
        fourthMeeting.setMeetingPlace(meetingPlacesUser2.get(0));
        fourthMeeting.setDate(LocalDateTime.now().plusDays(3));
        fourthMeeting.setStatus(MeetingStatus.ACCEPTED);
        fourthMeeting.setBuyerDistinctiveSign("Je porterai une chemise blanche.");
        fourthMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil.");
        fourthMeeting.setBuyerAdditionalInfo("Je préfère un paiement en espèces.");
        fourthMeeting.setSellerAdditionalInfo("Merci de respecter la distance sociale.");
        fourthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser2.get(1))));
        adsUser2.get(1).setStatus(AdStatus.RESERVED);

        // Finalized
        Meeting fifthMeeting = new Meeting();
        fifthMeeting.setBuyer(user1); // Koroviev
        fifthMeeting.setSeller(user2); // Dounia == user
        fifthMeeting.setMeetingPlace(meetingPlacesUser2.get(0));
        fifthMeeting.setDate(LocalDateTime.now().minusDays(1));
        fifthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        fifthMeeting.setBuyerDistinctiveSign("Je porterai un pull rouge.");
        fifthMeeting.setSellerDistinctiveSign("Je porterai un manteau noir.");
        fifthMeeting.setBuyerAdditionalInfo("Je préfère une transaction rapide.");
        fifthMeeting.setSellerAdditionalInfo("Merci d'apporter l'article.");
        fifthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser2.get(2))));
        adsUser2.get(2).setStatus(AdStatus.RESERVED);

        Meeting sixthMeeting = new Meeting();
        sixthMeeting.setBuyer(user2); // Dounia == user
        sixthMeeting.setSeller(user1); // Koroviev
        sixthMeeting.setMeetingPlace(meetingPlacesUser1.get(0));
        sixthMeeting.setDate(LocalDateTime.now().minusDays(2));
        sixthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        sixthMeeting.setBuyerDistinctiveSign("Je porterai un bonnet jaune.");
        sixthMeeting.setSellerDistinctiveSign("Je porterai une veste en cuir.");
        sixthMeeting.setBuyerAdditionalInfo("Je préfère un paiement par virement.");
        sixthMeeting.setSellerAdditionalInfo("Merci de venir à l'heure.");
        sixthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser1.get(1))));
        adsUser1.get(1).setStatus(AdStatus.RESERVED);

        // User1 => Koroviev
        // Proposed
        Meeting seventhMeeting = new Meeting();
        seventhMeeting.setBuyer(user1); // Koroviev == user
        seventhMeeting.setSeller(user2); // Dounia
        seventhMeeting.setMeetingPlace(meetingPlacesUser2.get(0));
        seventhMeeting.setDate(LocalDateTime.now().plusDays(3));
        seventhMeeting.setStatus(MeetingStatus.INITIALIZED);
        seventhMeeting.setBuyerDistinctiveSign("Je porterai un manteau noir et une écharpe bleue.");
        seventhMeeting.setSellerDistinctiveSign("J'aurai une mallette en cuir marron.");
        seventhMeeting.setBuyerAdditionalInfo("Merci d'apporter la facture originale si possible.");
        seventhMeeting.setSellerAdditionalInfo("Je risque d'avoir quelques minutes de retard.");
        seventhMeeting.setAds(new HashSet<>(Arrays.asList(adsUser2.get(3))));
        adsUser2.get(3).setStatus(AdStatus.RESERVED);

        // Planned
        Meeting eighthMeeting = new Meeting();
        eighthMeeting.setBuyer(user2); // Dounia
        eighthMeeting.setSeller(user1); // Koroviev == user
        eighthMeeting.setMeetingPlace(meetingPlacesUser1.get(0));
        eighthMeeting.setDate(LocalDateTime.now().plusDays(3));
        eighthMeeting.setStatus(MeetingStatus.ACCEPTED);
        eighthMeeting.setBuyerDistinctiveSign("Je porterai un pull vert foncé et un pantalon beige.");
        eighthMeeting.setSellerDistinctiveSign("J'aurai un sac en toile écru sur l'épaule.");
        eighthMeeting.setBuyerAdditionalInfo("Pouvez-vous apporter l'emballage d'origine ?");
        eighthMeeting.setSellerAdditionalInfo("Je préfère effectuer la transaction dans un lieu public.");
        eighthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser1.get(2))));
        adsUser1.get(2).setStatus(AdStatus.RESERVED);

        // Finalized
        Meeting ninthMeeting = new Meeting();
        ninthMeeting.setBuyer(user2); // Dounia
        ninthMeeting.setSeller(user1); // Koroviev == user
        ninthMeeting.setMeetingPlace(meetingPlacesUser1.get(0));
        ninthMeeting.setDate(LocalDateTime.now().minusDays(1));
        ninthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        ninthMeeting.setBuyerDistinctiveSign("Je serai en chemise blanche et pantalon noir.");
        ninthMeeting.setSellerDistinctiveSign("J'aurai une sacoche pour ordinateur portable.");
        ninthMeeting.setBuyerAdditionalInfo("Je souhaite vérifier le fonctionnement de l'objet sur place.");
        ninthMeeting.setSellerAdditionalInfo("Merci de prévoir une garantie écrite si applicable.");
        ninthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser1.get(3))));
        adsUser1.get(3).setStatus(AdStatus.RESERVED);

        Meeting tenthMeeting = new Meeting();
        tenthMeeting.setBuyer(user1); // Koroviev == user
        tenthMeeting.setSeller(user2); // Dounia
        tenthMeeting.setMeetingPlace(meetingPlacesUser2.get(1));
        tenthMeeting.setDate(LocalDateTime.now().minusDays(2));
        tenthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        tenthMeeting.setBuyerDistinctiveSign("Je porterai un blouson en cuir marron et un jean.");
        tenthMeeting.setSellerDistinctiveSign("J'aurai des écouteurs autour du cou.");
        tenthMeeting.setBuyerAdditionalInfo("Je peux payer par chèque si vous préférez.");
        tenthMeeting.setSellerAdditionalInfo("Pouvez-vous fournir un historique d'entretien de l'objet ?");
        tenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser2.get(4))));
        adsUser2.get(4).setStatus(AdStatus.RESERVED);

        // User3 = Leahad
        // Proposed
        Meeting eleventhMeeting = new Meeting();
        eleventhMeeting.setBuyer(user3); // Leahad == user
        eleventhMeeting.setSeller(user4); // Eri
        eleventhMeeting.setMeetingPlace(meetingPlacesUser4.get(0));
        eleventhMeeting.setDate(LocalDateTime.now().plusDays(3));
        eleventhMeeting.setStatus(MeetingStatus.INITIALIZED);
        eleventhMeeting.setBuyerDistinctiveSign("J'aurai une casquette bleue marine et des baskets blanches.");
        eleventhMeeting.setSellerDistinctiveSign("Je porterai une montre argentée visible.");
        eleventhMeeting.setBuyerAdditionalInfo("Je préfère un échange rapide, mon temps est limité.");
        eleventhMeeting.setSellerAdditionalInfo("Merci d'informer de tout défaut connu à l'avance.");
        eleventhMeeting.setAds(new HashSet<>(Arrays.asList(adsUser4.get(2))));
        adsUser4.get(2).setStatus(AdStatus.RESERVED);

        Meeting twelfthMeeting = new Meeting();
        twelfthMeeting.setBuyer(user5); // Julius
        twelfthMeeting.setSeller(user3); // Leahad == user
        twelfthMeeting.setMeetingPlace(meetingPlacesUser3.get(2));
        twelfthMeeting.setDate(LocalDateTime.now().plusDays(3));
        twelfthMeeting.setStatus(MeetingStatus.ACCEPTED);
        twelfthMeeting.setBuyerDistinctiveSign("Je serai en tailleur noir avec une broche dorée.");
        twelfthMeeting.setSellerDistinctiveSign("J'aurai un porte-documents en cuir noir.");
        twelfthMeeting.setBuyerAdditionalInfo("Je souhaite un reçu détaillé pour ma comptabilité.");
        twelfthMeeting.setSellerAdditionalInfo("Merci pour votre réservation !");
        twelfthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser3.get(2))));
        adsUser3.get(2).setStatus(AdStatus.RESERVED);

        // Finalized
        Meeting thirteenthMeeting = new Meeting();
        thirteenthMeeting.setBuyer(user4); // Eri
        thirteenthMeeting.setSeller(user3); // Leahad == user
        thirteenthMeeting.setMeetingPlace(meetingPlacesUser3.get(3));
        thirteenthMeeting.setDate(LocalDateTime.now().minusDays(1));
        thirteenthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        thirteenthMeeting.setBuyerDistinctiveSign("Je porterai un polo bleu ciel et un pantalon kaki.");
        thirteenthMeeting.setSellerDistinctiveSign("J'aurai une sacoche en bandoulière grise.");
        thirteenthMeeting.setBuyerAdditionalInfo("Je risque d'avoir 5 minutes de retard.");
        thirteenthMeeting.setSellerAdditionalInfo("Je vous attendrai prêt du kiosque à journaux.");
        thirteenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser3.get(3))));
        adsUser3.get(3).setStatus(AdStatus.RESERVED);

        Meeting fourteenthMeeting = new Meeting();
        fourteenthMeeting.setBuyer(user5); // Julius
        fourteenthMeeting.setSeller(user3); // Leahad == user
        fourteenthMeeting.setMeetingPlace(meetingPlacesUser3.get(1));
        fourteenthMeeting.setDate(LocalDateTime.now().minusDays(2));
        fourteenthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        fourteenthMeeting.setBuyerDistinctiveSign("Je porterai un blazer blanc.");
        fourteenthMeeting.setSellerDistinctiveSign("Je porterai des lunettes de soleil sur la tête.");
        fourteenthMeeting.setBuyerAdditionalInfo("Pouvez-vous fournir une preuve d'achat ?");
        fourteenthMeeting.setSellerAdditionalInfo("Je vous apporterai la facture.");
        fourteenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser3.get(4))));
        adsUser3.get(4).setStatus(AdStatus.RESERVED);

        // User4 = Eri
        // Proposed
        Meeting fifteenthMeeting = new Meeting();
        fifteenthMeeting.setBuyer(user4); // Eri == user
        fifteenthMeeting.setSeller(user3); // Leahad
        fifteenthMeeting.setMeetingPlace(meetingPlacesUser3.get(2));
        fifteenthMeeting.setDate(LocalDateTime.now().plusDays(3));
        fifteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        fifteenthMeeting.setBuyerDistinctiveSign("J'aurai une écharpe rouge et des gants en cuir.");
        fifteenthMeeting.setSellerDistinctiveSign("Je tiendrai un journal sous le bras.");
        fifteenthMeeting.setBuyerAdditionalInfo("Je préfère négocier le prix final après inspection.");
        fifteenthMeeting
                .setSellerAdditionalInfo("Je vous conseille de prévoir un diable pour le transport.");
        fifteenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser3.get(5))));
        adsUser3.get(5).setStatus(AdStatus.RESERVED);

        // ToConfirm
        Meeting sixteenthMeeting = new Meeting();
        sixteenthMeeting.setBuyer(user3);// Leahad
        sixteenthMeeting.setSeller(user4); // Eri == user
        sixteenthMeeting.setMeetingPlace(meetingPlacesUser4.get(2));
        sixteenthMeeting.setDate(LocalDateTime.now().plusDays(3));
        sixteenthMeeting.setStatus(MeetingStatus.INITIALIZED);
        sixteenthMeeting.setBuyerDistinctiveSign("Je serai en costume bleu marine avec une pochette.");
        sixteenthMeeting.setSellerDistinctiveSign("J'aurai une cravate à motifs géométriques.");
        sixteenthMeeting.setBuyerAdditionalInfo("J'ai hâte de procéder à l'échange !");
        sixteenthMeeting
                .setSellerAdditionalInfo("Bravo pour avoir choisi ce magnifique objet, vous avez beaucoup de goût :)");
        sixteenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser4.get(4))));
        adsUser4.get(4).setStatus(AdStatus.RESERVED);

        // Planned
        Meeting seventeenthMeeting = new Meeting();
        seventeenthMeeting.setBuyer(user5); // Julius
        seventeenthMeeting.setSeller(user4); // Eri == user
        seventeenthMeeting.setMeetingPlace(meetingPlacesUser4.get(0));
        seventeenthMeeting.setDate(LocalDateTime.now().plusDays(2));
        seventeenthMeeting.setStatus(MeetingStatus.ACCEPTED);
        seventeenthMeeting.setBuyerDistinctiveSign("Je serai en pull col roulé noir et pantalon gris.");
        seventeenthMeeting.setSellerDistinctiveSign("Je porterai une montre connectée visible.");
        seventeenthMeeting.setBuyerAdditionalInfo("Je préfère payer avec de la monnaie");
        seventeenthMeeting.setSellerAdditionalInfo("Pouvez-vous confirmer la compatibilité avec mon équipement ?");
        seventeenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser4.get(5))));
        adsUser4.get(5).setStatus(AdStatus.RESERVED);

        // Finalized
        Meeting eighteenthMeeting = new Meeting();
        eighteenthMeeting.setBuyer(user5); // Julius
        eighteenthMeeting.setSeller(user4); // Eri == user
        eighteenthMeeting.setMeetingPlace(meetingPlacesUser4.get(1));
        eighteenthMeeting.setDate(LocalDateTime.now().minusDays(1));
        eighteenthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        eighteenthMeeting.setBuyerDistinctiveSign("J'aurai une veste matelassée bleue et une écharpe grise.");
        eighteenthMeeting.setSellerDistinctiveSign("Je tiendrai un dossier avec des papiers visibles.");
        eighteenthMeeting.setBuyerAdditionalInfo("J'aimerais avoir la possibilité d'un retour sous conditions.");
        eighteenthMeeting.setSellerAdditionalInfo("Merci de prévoir un temps pour répondre à mes questions.");
        eighteenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser4.get(6))));
        adsUser4.get(6).setStatus(AdStatus.RESERVED);

        Meeting nineteenthMeeting = new Meeting();
        nineteenthMeeting.setBuyer(user4); // Erika == user
        nineteenthMeeting.setSeller(user3); // Leahad
        nineteenthMeeting.setMeetingPlace(meetingPlacesUser3.get(0));
        nineteenthMeeting.setDate(LocalDateTime.now().minusDays(2));
        nineteenthMeeting.setStatus(MeetingStatus.TOBEFINALIZED);
        nineteenthMeeting.setBuyerDistinctiveSign("Je serai en chemise à carreaux et jean foncé.");
        nineteenthMeeting.setSellerDistinctiveSign("J'aurai une sacoche pour appareil photo.");
        nineteenthMeeting.setBuyerAdditionalInfo("Article incroyable, hâte de le découvrir IRL !");
        nineteenthMeeting.setSellerAdditionalInfo("Merci pour votre réservation !");
        nineteenthMeeting.setAds(new HashSet<>(Arrays.asList(adsUser3.get(7))));
        adsUser3.get(7).setStatus(AdStatus.RESERVED);

        List<Ad> allAds = new ArrayList<>();
        allAds.addAll(adsUser1);
        allAds.addAll(adsUser2);
        allAds.addAll(adsUser3);
        allAds.addAll(adsUser4);
        adRepository.saveAll(allAds);

        List<Meeting> meetings = Arrays.asList(
                firstMeeting, secondMeeting, fourthMeeting, fifthMeeting,
                sixthMeeting, seventhMeeting, eighthMeeting, ninthMeeting, tenthMeeting,
                eleventhMeeting, twelfthMeeting, thirteenthMeeting, fourteenthMeeting, fifteenthMeeting,
                sixteenthMeeting, seventeenthMeeting, eighteenthMeeting,
                nineteenthMeeting);
        this.meetingRepository.saveAll(meetings);
    }
}
