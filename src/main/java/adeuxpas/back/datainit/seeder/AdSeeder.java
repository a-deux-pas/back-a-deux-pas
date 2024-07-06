package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.UsersFavoriteAds;
import adeuxpas.back.entity.UsersFavoriteAdsKey;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UsersFavoriteAdsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Seeder class responsible for generating sample ad entities.
 * Each ad is initialized with sample data such as title, price, creation date,
 * publisher, etc.
 */
@Component
public class AdSeeder {

    private final AdRepository adRepository;
    private final UsersFavoriteAdsRepository favoriteRepository;

    /**
     * Constructs a new AdSeeder with the provided AdRepository.
     * 
     * @param adRepository the AdRepository to be used for database seeding
     *                     operations.
     */
    AdSeeder(@Autowired AdRepository adRepository, @Autowired UsersFavoriteAdsRepository favoriteRepository) {
        this.adRepository = adRepository;
        this.favoriteRepository = favoriteRepository;
    }

    /**
     * This method creates ad entities.
     * Each ad is initialized with sample data such as title, price, creation date,
     * publisher etc.
     * 
     * @param users the list of potential ad publishers.
     * @return a list of ads.
     */
    public List<Ad> createAds(List<User> users) {
        Ad firstAd = new Ad();
        firstAd.setArticleDescription("Rocky Mountain Growler 40, perfect condition ");
        firstAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
        firstAd.setPrice(BigDecimal.valueOf(1999));
        firstAd.setTitle("Trail Bike");
        firstAd.setPublisher(users.get(0));
        firstAd.setArticleState("Très bon état");
        firstAd.setCategory("Loisirs");
        firstAd.setSubcategory("Sport");
        List<Ad> firstUserAds = new ArrayList<>();
        firstUserAds.add(firstAd);
        users.get(0).setAds(firstUserAds);
        firstAd.setStatus(AdStatus.AVAILABLE);

        Ad secondAd = new Ad();
        secondAd.setArticleDescription("Everlast leather boxing gloves, mint condition");
        secondAd.setCreationDate(LocalDateTime.now().plusMinutes(4));
        secondAd.setPrice(BigDecimal.valueOf(8));
        secondAd.setTitle("Vintage boxing gloves");
        secondAd.setPublisher(users.get(1));
        secondAd.setArticleState("Satisfaisant");
        secondAd.setCategory("Loisirs");
        secondAd.setSubcategory("Sport");
        List<Ad> secondUserAds = new ArrayList<>();
        secondUserAds.add(secondAd);
        users.get(1).setAds(secondUserAds);
        secondAd.setStatus(AdStatus.AVAILABLE);

        Ad thirdAd = new Ad();
        thirdAd.setArticleDescription("Light grey, italian wool, size 48");
        thirdAd.setCreationDate(LocalDateTime.now().plusMinutes(2));
        thirdAd.setPrice(BigDecimal.valueOf(59));
        thirdAd.setTitle("3 piece suit");
        thirdAd.setPublisher(users.get(4));
        thirdAd.setArticleState("Neuf avec étiquette");
        thirdAd.setCategory("Mode");
        thirdAd.setSubcategory("Hauts");
        thirdAd.setArticleGender("Homme");
        List<Ad> fifthUserAds = new ArrayList<>();
        fifthUserAds.add(thirdAd);
        users.get(4).setAds(fifthUserAds);
        thirdAd.setStatus(AdStatus.AVAILABLE);

        Ad fourthAd = new Ad();
        fourthAd.setArticleDescription("mechanical swiss made, limited edition");
        fourthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        fourthAd.setPrice(BigDecimal.valueOf(999));
        fourthAd.setTitle("Frédérique Constant 1988");
        fourthAd.setPublisher(users.get(0));
        fourthAd.setArticleState("Satisfaisant");
        fourthAd.setCategory("Mode");
        fourthAd.setSubcategory("Accessoires");
        firstUserAds.add(fourthAd);
        users.get(0).setAds(firstUserAds);
        fourthAd.setStatus(AdStatus.AVAILABLE);

        Ad fifthAd = new Ad();
        fifthAd.setArticleDescription("Bose quiet comfort, noise cancelling headphones");
        fifthAd.setCreationDate(LocalDateTime.now().minusMinutes(1));
        fifthAd.setPrice(BigDecimal.valueOf(199));
        fifthAd.setTitle("Bose headphones");
        fifthAd.setPublisher(users.get(2));
        fifthAd.setArticleState("Bon état");
        fifthAd.setCategory("Loisirs");
        fifthAd.setSubcategory("Musique");
        List<Ad> thirdUserAds = new ArrayList<>();
        thirdUserAds.add(fifthAd);
        users.get(2).setAds(thirdUserAds);
        fifthAd.setStatus(AdStatus.AVAILABLE);

        Ad sixthAd = new Ad();
        sixthAd.setArticleDescription("Wonderlust phone case");
        sixthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        sixthAd.setPrice(BigDecimal.valueOf(20));
        sixthAd.setTitle("Phone case");
        sixthAd.setPublisher(users.get(3));
        sixthAd.setArticleState("Satisfaisant");
        sixthAd.setCategory("Électronique");
        sixthAd.setSubcategory("Autre");
        List<Ad> fourthUserAds = new ArrayList<>();
        fourthUserAds.add(sixthAd);
        users.get(3).setAds(fourthUserAds);
        sixthAd.setStatus(AdStatus.AVAILABLE);

        Ad seventhAd = new Ad();
        seventhAd.setArticleDescription("Blue satin silk shirt");
        seventhAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
        seventhAd.setPrice(BigDecimal.valueOf(15));
        seventhAd.setTitle("Blue silk shirt");
        seventhAd.setPublisher(users.get(3));
        seventhAd.setArticleState("Neuf avec étiquette");
        seventhAd.setCategory("Mode");
        seventhAd.setSubcategory("Hauts");
        seventhAd.setArticleGender("Femme");
        fourthUserAds.add(seventhAd);
        users.get(3).setAds(fourthUserAds);
        seventhAd.setStatus(AdStatus.AVAILABLE);

        Ad eighthAd = new Ad();
        eighthAd.setArticleDescription(
                "Nassim N. Taleb's best selling collection; includes: Anti-Fragile, The Black San, Skin in the Game etc ");
        eighthAd.setCreationDate(LocalDateTime.now().plusMinutes(6));
        eighthAd.setPrice(BigDecimal.valueOf(150));
        eighthAd.setTitle("Incerto Book Collection");
        eighthAd.setPublisher(users.get(2));
        eighthAd.setArticleState("Neuf sans étiquette");
        eighthAd.setCategory("Loisirs");
        eighthAd.setSubcategory("Livres");
        thirdUserAds.add(eighthAd);
        users.get(2).setAds(thirdUserAds);
        eighthAd.setStatus(AdStatus.AVAILABLE);

        Ad ninethAd = new Ad();
        ninethAd.setArticleDescription("Vulgar description");
        ninethAd.setCreationDate(LocalDateTime.now().minusMinutes(10));
        ninethAd.setPrice(BigDecimal.valueOf(10.2));
        ninethAd.setTitle("Handmade bracelet");
        ninethAd.setPublisher(users.get(5));
        ninethAd.setArticleState("Neuf sans étiquette");
        ninethAd.setCategory("Autre");
        ninethAd.setSubcategory("Autre");
        List<Ad> sixthUserAds = new ArrayList<>();
        sixthUserAds.add(ninethAd);
        users.get(5).setAds(sixthUserAds);
        ninethAd.setStatus(AdStatus.SUSPENDED);

        Ad tenthAd = new Ad();
        tenthAd.setArticleDescription("Maroon leather handbag");
        tenthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
        tenthAd.setPrice(BigDecimal.valueOf(45));
        tenthAd.setTitle("Handbag");
        tenthAd.setPublisher(users.get(0));
        tenthAd.setArticleState("Très bon état");
        tenthAd.setCategory("Mode");
        tenthAd.setSubcategory("Accessoires");
        firstUserAds.add(tenthAd);
        users.get(0).setAds(firstUserAds);
        tenthAd.setStatus(AdStatus.AVAILABLE);

        Ad eleventhAd = new Ad();
        eleventhAd.setArticleDescription("Baoding balls");
        eleventhAd.setCreationDate(LocalDateTime.now().plusMinutes(4));
        eleventhAd.setPrice(BigDecimal.valueOf(8));
        eleventhAd.setTitle("Chinese stress balls");
        eleventhAd.setPublisher(users.get(1));
        eleventhAd.setArticleState("Neuf sans étiquette");
        eleventhAd.setCategory("Loisirs");
        eleventhAd.setSubcategory("Sport");
        secondUserAds.add(eleventhAd);
        users.get(1).setAds(secondUserAds);
        eleventhAd.setStatus(AdStatus.AVAILABLE);

        Ad twelfthAd = new Ad();
        twelfthAd.setArticleDescription("Travel suitcase");
        twelfthAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
        twelfthAd.setPrice(BigDecimal.valueOf(9));
        twelfthAd.setTitle("Travel suitcase");
        twelfthAd.setPublisher(users.get(4));
        twelfthAd.setArticleState("Neuf sans étiquette");
        twelfthAd.setCategory("Autre");
        twelfthAd.setSubcategory("Autre");
        fifthUserAds.add(twelfthAd);
        users.get(4).setAds(fifthUserAds);
        twelfthAd.setStatus(AdStatus.AVAILABLE);

        Ad thirteenthAd = new Ad();
        thirteenthAd.setArticleDescription("black backpack");
        thirteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        thirteenthAd.setPrice(BigDecimal.valueOf(20));
        thirteenthAd.setTitle("Black Backpack");
        thirteenthAd.setPublisher(users.get(0));
        thirteenthAd.setArticleState("Satisfaisant");
        thirteenthAd.setCategory("Mode");
        thirteenthAd.setSubcategory("Accessoires");
        firstUserAds.add(thirteenthAd);
        users.get(0).setAds(firstUserAds);
        thirteenthAd.setStatus(AdStatus.AVAILABLE);

        Ad fourteenthAd = new Ad();
        fourteenthAd.setArticleDescription("Original cowboy hat");
        fourteenthAd.setCreationDate(LocalDateTime.now().minusMinutes(1));
        fourteenthAd.setPrice(BigDecimal.valueOf(49));
        fourteenthAd.setTitle("Cowboy hat");
        fourteenthAd.setPublisher(users.get(2));
        fourteenthAd.setArticleState("Satisfaisant");
        fourteenthAd.setCategory("Mode");
        fourteenthAd.setSubcategory("Accessoires");
        thirdUserAds.add(fourteenthAd);
        users.get(2).setAds(thirdUserAds);
        fourteenthAd.setStatus(AdStatus.AVAILABLE);

        Ad fifteenthAd = new Ad();
        fifteenthAd.setArticleDescription("slick cap");
        fifteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        fifteenthAd.setPrice(BigDecimal.valueOf(20));
        fifteenthAd.setTitle("Marlboro cap");
        fifteenthAd.setPublisher(users.get(3));
        fifteenthAd.setArticleState("Bon état");
        fifteenthAd.setCategory("Mode");
        fifteenthAd.setSubcategory("Accessoires");
        fourthUserAds.add(fifteenthAd);
        users.get(3).setAds(fourthUserAds);
        fifteenthAd.setStatus(AdStatus.AVAILABLE);

        Ad sixteenthAd = new Ad();
        sixteenthAd.setArticleDescription("hippie bracelet");
        sixteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
        sixteenthAd.setPrice(BigDecimal.valueOf(5));
        sixteenthAd.setTitle("Tree of Life Bracelet");
        sixteenthAd.setPublisher(users.get(5));
        sixteenthAd.setArticleState("Bon état");
        sixteenthAd.setCategory("Mode");
        sixteenthAd.setSubcategory("Accessoires");
        sixthUserAds.add(sixteenthAd);
        users.get(5).setAds(fourthUserAds);
        sixteenthAd.setStatus(AdStatus.AVAILABLE);

        Ad seventeenthAd = new Ad();
        seventeenthAd.setArticleDescription(" etc ");
        seventeenthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
        seventeenthAd.setPrice(BigDecimal.valueOf(39));
        seventeenthAd.setTitle("Cactus bracelet");
        seventeenthAd.setPublisher(users.get(2));
        seventeenthAd.setArticleState("Neuf avec étiquette");
        seventeenthAd.setCategory("Mode");
        seventeenthAd.setSubcategory("Accessoires");
        thirdUserAds.add(seventeenthAd);
        users.get(2).setAds(thirdUserAds);
        seventeenthAd.setStatus(AdStatus.AVAILABLE);

        Ad eighteenthAd = new Ad();
        eighteenthAd.setArticleDescription("Walter White Coaster");
        eighteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
        eighteenthAd.setPrice(BigDecimal.valueOf(4.99));
        eighteenthAd.setTitle("Heisenberg Cork Coaster");
        eighteenthAd.setPublisher(users.get(2));
        eighteenthAd.setArticleState("Neuf sans étiquette");
        eighteenthAd.setCategory("Maison");
        eighteenthAd.setSubcategory("Autre");
        thirdUserAds.add(eighteenthAd);
        users.get(2).setAds(thirdUserAds);
        eighteenthAd.setStatus(AdStatus.AVAILABLE);

        return Arrays.asList(firstAd, secondAd, thirdAd, fourthAd, fifthAd, sixthAd, seventhAd, eighthAd, ninethAd,
                tenthAd, eleventhAd, twelfthAd, thirteenthAd, fourteenthAd, fifteenthAd, sixteenthAd, seventeenthAd,
                eighteenthAd);
    }

    /**
     * Seeds the database with sample ad data.
     * 
     * @param ads The list of ads to save.
     */
    public void seedAds(List<Ad> ads) {
        this.adRepository.saveAll(ads);
    }

    /**
     * Seeds the database with favorites ads.
     * 
     * @param user The user who adds the ad as favorite.
     * @param ad   The ad added as favorite.
     */
    private UsersFavoriteAds createFavoriteAd(User user, Ad ad) {
        UsersFavoriteAdsKey favoriteKey = new UsersFavoriteAdsKey(user.getId(), ad.getId());
        UsersFavoriteAds favoriteAd = new UsersFavoriteAds(favoriteKey, user, ad, LocalDateTime.now());
        this.favoriteRepository.save(favoriteAd);
        return favoriteAd;
    }

    /**
     * Adds up to two ads to the user's favorites if the postal code of the ad's
     * publisher matches the user's postal code.
     *
     * @param user The list of users.
     * @param ads  The list of ads.
     */
    public void seedFavoritesAds(List<User> users, List<Ad> ads) {
        for (User user : users) {
            List<Ad> matchedAds = ads.stream()
                    .filter(ad -> !ad.getPublisher().equals(user)
                            && ad.getPublisher().getPostalCode().equals(user.getPostalCode()))
                    .limit(2)
                    .toList();

            for (Ad ad : matchedAds) {
                createFavoriteAd(user, ad);
            }
        }
    }
}
