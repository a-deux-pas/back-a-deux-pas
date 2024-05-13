package adeuxpas.back.testdatainit;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.enums.UserRole;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.ArticlePictureRepository;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible for seeding the test database with initial sample data.
 * <p>
 * The {@code seedTestDatabase()} method orchestrates the seeding process by calling individual seeding methods for each entity.
 * </p>
 *
 * @author Mircea Bardan
 */

// In order for the tests to remain relevant, please :
// - do not add or remove the existing objects
// - do not modify the existing objects properties' values (feel free to add new properties, along with their values, when needed)

@Component
public class IntegrationTestDatabaseSeeder {
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final ArticlePictureRepository articlePictureRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    /**
     * Constructs a new instance of IntegrationTestDatabaseSeeder.
     *
     * @param userRepository  The repository for managing user entities.
     * @param adRepository  The repository for managing ad entities.
     * @param passwordEncoder The password encoder used to encode user passwords before storing them in the database.
     */
    public IntegrationTestDatabaseSeeder(@Autowired UserRepository userRepository,
                                         @Autowired AdRepository adRepository,
                                         @Autowired ArticlePictureRepository articlePictureRepository,
                                         @Autowired BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.passwordEncoder = passwordEncoder;
        this.articlePictureRepository = articlePictureRepository;
    }

    /**
     * Seeds the test database with initial sample data.
     * This method delegates the seeding process to individual seeding methods for different entities.
     */
    public void seedTestDatabase(){
        List<User> users = createUsers();
        seedUsers(users);

        List<Ad> ads = createAds(users);
        seedAds(ads);

        seedArticlePictures(ads);
    }

    /**
     * This method creates user entities.
     * Each user is initialized with sample data such as email, password, role, status, and creation timestamp.
     * @return a list of users.
     */
    private List<User> createUsers(){
        User first = new User();
        first.setEmail("mbardan@email.ro");
        first.setAlias("Koroviev");
        first.setPassword(passwordEncoder.encode("pass1"));
        first.setBio("bio1");
        first.setCountry("France");
        first.setCity("Maisons-Alfort");
        first.setStreet("Victor Hugo");
        first.setPostalCode("94700");
        first.setProfilePicture("profilePictureUrl1");
        first.setInscriptionDate(LocalDateTime.now());
        first.setAccountStatus(AccountStatus.ACTIVE);
        first.setRole(UserRole.USER);

        User second = new User();
        second.setEmail("daouali@email.com");
        second.setPassword(passwordEncoder.encode("pass2"));
        second.setAlias("Dounia");
        second.setBio("bio2");
        second.setCountry("France");
        second.setCity("Paris");
        second.setStreet("5, Garibaldi");
        second.setPostalCode("75000");
        second.setProfilePicture("profilePictureUrl2");
        second.setInscriptionDate(LocalDateTime.now());
        second.setAccountStatus(AccountStatus.REPORTED);
        second.setRole(UserRole.USER);

        User third = new User();
        third.setEmail("lhadida@email.com");
        third.setPassword(passwordEncoder.encode("pass3"));
        third.setAlias("Leahad");
        third.setBio("bio3");
        third.setCountry("France");
        third.setCity("Charenton");
        third.setStreet("5, Av de la Liberte");
        third.setPostalCode("75018");
        third.setProfilePicture("profilePictureUrl3");
        third.setInscriptionDate(LocalDateTime.now());
        third.setAccountStatus(AccountStatus.ACTIVE);
        third.setRole(UserRole.USER);

        User fourth = new User();
        fourth.setEmail("erikaike@email.fr");
        fourth.setPassword(passwordEncoder.encode("pass4"));
        fourth.setAlias("Eri");
        fourth.setBio("bio4");
        fourth.setCountry("France");
        fourth.setCity("Lyon");
        fourth.setStreet("5, rue Gabriel Peri");
        fourth.setPostalCode("69000");
        fourth.setProfilePicture("profilePictureUrl4");
        fourth.setInscriptionDate(LocalDateTime.now());
        fourth.setAccountStatus(AccountStatus.ACTIVE);
        fourth.setRole(UserRole.ADMIN);

        User fifth = new User();
        fifth.setEmail("jmoukmir@email.com");
        fifth.setPassword(passwordEncoder.encode("pass5"));
        fifth.setAlias("theRabbi");
        fifth.setBio("bio5");
        fifth.setCountry("France");
        fifth.setCity("Lyon");
        fifth.setStreet("7bis, rue de la Synagogue");
        fifth.setPostalCode("66000");
        fifth.setProfilePicture("profilePictureUrl5");
        fifth.setInscriptionDate(LocalDateTime.now());
        fifth.setAccountStatus(AccountStatus.ACTIVE);
        fifth.setRole(UserRole.ADMIN);

        User sixth = new User();
        sixth.setEmail("random@email.com");
        sixth.setPassword(passwordEncoder.encode("pass6"));
        sixth.setAlias("Rando");
        sixth.setBio("bio6");
        sixth.setCountry("France");
        sixth.setCity("Lyon");
        sixth.setStreet("sous un pont");
        sixth.setPostalCode("66000");
        sixth.setProfilePicture("profilePictureUrl6");
        sixth.setInscriptionDate(LocalDateTime.now());
        sixth.setAccountStatus(AccountStatus.ACTIVE);
        sixth.setRole(UserRole.USER);

        return Arrays.asList(first, second, third, fourth, fifth, sixth);
    }

    /**
     * Seeds the test database with sample user data.
     * @param users the list of users to save.
     */
    private void seedUsers(List<User> users){
        this.userRepository.saveAll(users);
    }

    private void seedArticlePictures(List<Ad> ads) {
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/90b00509-dd97-46e3-b832-24eb8eaa8e0b_large.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d2yn9m4p3q9iyv.cloudfront.net/rockymountain/2023/growler-40/thumbs/1000/62903.webp"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/13185af6-1f03-4cdc-8545-af767b7e6bb3_large.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.ytimg.com/vi/2B1bWKBTROE/sddefault.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/cf15ac41-1979-4264-9193-3b0c2d302907_large.jpg"), ads.getFirst()));

        this.articlePictureRepository.save(new ArticlePicture(("https://i.ebayimg.com/images/g/5DMAAOSwpIVldRxH/s-l1200.webp"), ads.get(1)));
        this.articlePictureRepository.save(new ArticlePicture(("https://andreemilio.com/wp-content/uploads/2021/03/Mens-Light-gray-3-Piece-Suit-2.jpg"), ads.get(2)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsejXk4iTIzAqvORn0DSomectnXd0l3A3fVQ&usqp=CAU"), ads.get(3)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBezaIpsONE88lxdf3WCBUKcVLDd9gWxBCEQ&usqp=CAU"), ads.get(4)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWRH_Z_65KbwGUDdA2KcCzqroUzVXmmq68NA&usqp=CAU"), ads.get(5)));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.pinimg.com/originals/bc/e9/1b/bce91b03652cc6172ddb755cc9128f45.jpg"), ads.get(6)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmvcDyC-LjyFt6_RI_dYLUcHhaJahuXUexcw&usqp=CAU"), ads.get(7)));

        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_00e44_XkRJUZhGUrNwxbLGfBs8erYr/f800/1711863484.jpeg?s=27fff3a33315f91019270a1a3bb53e9615c2f962"), ads.get(8)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_018e1_N2JsK2fxtrZn1xk16xNo9kyr/f800/1711856284.jpeg?s=091fb7a67a8dc1adab7bd7994442b87c7f7b112e"), ads.get(9)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01ff4_Ly9usSkKK7x4ZLUNJYEJY7vV/f800/1711768613.jpeg?s=eda3db9c2dabce566e20a82b06293f490b7054f7"), ads.get(10)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01441_xix4NLunHyRfpdhiqMyka3BL/f800/1711760473.jpeg?s=8abaf00255f5ec6a3422800b711dc43e8e3d7cfe"), ads.get(11)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_009bf_biwV7SSYALHvZjeMx9DzEgPF/f800/1711666972.jpeg?s=796daddfcb0cd0dec79c877ac08569f87e693da5"), ads.get(12)));

        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_01107_AQkaWBDeUJWEFWcx4sxwaFTe/f800/1702265856.jpeg?s=19117baf0b87627abb81a9e27574b0ac30133f07"), ads.get(13)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_021d2_GVTqnTQ3oFnegSHv1mJU9Amz/f800/1686792993.jpeg?s=1451865905183fb060fd76197322267ae23cc159"), ads.get(14)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/02_009c7_eEKstfbvuKJyXRHLbJvUQhVy/f800/1711815268.jpeg?s=4b5e6eded4ab45c8c932850aa25179984646042b"), ads.get(15)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/02_020f4_3yFUxux5NH3ajCpZwWfR6P5n/f800/1707671737.jpeg?s=d603b47dc9eec529765f9eae6c152c752a0954bd"), ads.get(16)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_0017b_9CsbxFBTWrpcWW1YRLbLaiuk/f800/1711794225.jpeg?s=e8e59ac218eeba65a2b6f543f20c0ae7e526c60e"), ads.get(5)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01b2f_QECcdvwJTvYk5dWoHqheetyQ/f800/1707104826.jpeg?s=d8b8a122fd856daf5a0141ab2a7083418509a327"), ads.get(6)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_003bc_zRPMb8VY3YDhXxrghu6gJcUM/f800/1710778480.jpeg?s=e35e3cb9915ac7f628d1d00785971d264e31f47e"), ads.get(17)));

    }

    /**
     * This method creates ad entities.
     * Each ad is initialized with sample data such as title, price, creation date, publisher etc.
     * @param users the list of potential ad publishers.
     * @return a list of ads.
     */
    private List<Ad> createAds(List<User> users){
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
        eighthAd.setArticleDescription("Nassim N. Taleb's best selling collection; includes: Anti-Fragile, The Black San, Skin in the Game etc ");
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
        thirteenthAd.setArticleDescription("blue back pack");
        thirteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        thirteenthAd.setPrice(BigDecimal.valueOf(20));
        thirteenthAd.setTitle("Blue Backpack");
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
        fifteenthAd.setCategory("Accessoires");
        fifteenthAd.setSubcategory("Autre");
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
        seventeenthAd.setTitle("Murano glass bracelet");
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
                tenthAd, eleventhAd, twelfthAd, thirteenthAd, fourteenthAd, fifteenthAd, sixteenthAd, seventeenthAd, eighteenthAd);
    }

    /**
     * Seeds the test database with sample ad data.
     * @param ads the list of ads to save.
     */
    private void seedAds(List<Ad> ads){
        this.adRepository.saveAll(ads);
    }

}
