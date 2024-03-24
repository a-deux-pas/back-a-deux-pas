package adeuxpas.back.datainit;

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
 * Class responsible for seeding the database with initial sample data.
 * It contains methods to seed different entities in the database, such as users.
 * <p>
 * The {@code seedDatabase()} method orchestrates the seeding process by calling individual seeding methods for each entity.
 * </p>
 * <p>
 * This component is annotated with {@code @Component} to mark it as a Spring-managed component
 * and enable automatic detection and registration by Spring's component scanning mechanism.
 *</p>
 *
 * @author Mircea Bardan
 */
@Component
public class DatabaseSeeder {
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final ArticlePictureRepository articlePictureRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs a new instance of DatabaseSeeder.
     *
     * @param userRepository  The repository for managing user entities.
     * @param adRepository  The repository for managing ad entities.
     * @param passwordEncoder The password encoder used to encode user passwords before storing them in the database.
     */
    public DatabaseSeeder(@Autowired UserRepository userRepository,
                          @Autowired AdRepository adRepository,
                          @Autowired ArticlePictureRepository articlePictureRepository,
                          @Autowired BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.passwordEncoder = passwordEncoder;
        this.articlePictureRepository = articlePictureRepository;
    }

    /**
     * Seeds the database with initial sample data.
     * This method delegates the seeding process to individual seeding methods for different entities.
     */
    public void seedDatabase(){
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
        third.setAccountStatus(AccountStatus.SUSPENDED);
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
        fifth.setPostalCode("69000");
        fifth.setProfilePicture("profilePictureUrl5");
        fifth.setInscriptionDate(LocalDateTime.now());
        fifth.setAccountStatus(AccountStatus.ACTIVE);
        fifth.setRole(UserRole.ADMIN);

        return Arrays.asList(first, second, third, fourth, fifth);
    }

    /**
     * Seeds the database with sample user data.
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
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmvcDyC-LjyFt6_RI_dYLUcHhaJahuXUexcw&usqp=CAU"), ads.getLast()));

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
        firstAd.setPrice(BigDecimal.valueOf(45));
        firstAd.setTitle("Trail Bike");
        firstAd.setPublisher(users.get(0));
        firstAd.setArticleState("Neuf avec étiquette");
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
        secondAd.setArticleState("Neuf sans étiquette");
        List<Ad> secondUserAds = new ArrayList<>();
        secondUserAds.add(secondAd);
        users.get(1).setAds(secondUserAds);
        secondAd.setStatus(AdStatus.AVAILABLE);

        Ad thirdAd = new Ad();
        thirdAd.setArticleDescription("Light grey, italian wool, size 48");
        thirdAd.setCreationDate(LocalDateTime.now().plusMinutes(2));
        thirdAd.setPrice(BigDecimal.valueOf(9));
        thirdAd.setTitle("3 piece suit");
        thirdAd.setPublisher(users.get(4));
        thirdAd.setArticleState("Neuf avec étiquette");
        List<Ad> fifthUserAds = new ArrayList<>();
        fifthUserAds.add(thirdAd);
        users.get(4).setAds(fifthUserAds);
        thirdAd.setStatus(AdStatus.AVAILABLE);

        Ad fourthAd = new Ad();
        fourthAd.setArticleDescription("mechanical swiss made, limited edition");
        fourthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        fourthAd.setPrice(BigDecimal.valueOf(10));
        fourthAd.setTitle("Frédérique Constant 1988");
        fourthAd.setPublisher(users.get(0));
        fourthAd.setArticleState("Satisfaisant");
        firstUserAds.add(fourthAd);
        users.get(0).setAds(firstUserAds);
        fourthAd.setStatus(AdStatus.AVAILABLE);

        Ad fifthAd = new Ad();
        fifthAd.setArticleDescription("Bose quiet comfort, noise cancelling headphones");
        fifthAd.setCreationDate(LocalDateTime.now().minusMinutes(1));
        fifthAd.setPrice(BigDecimal.valueOf(19));
        fifthAd.setTitle("Bose headphones");
        fifthAd.setPublisher(users.get(2));
        fifthAd.setArticleState("Bon état");
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
        List<Ad> fourthUserAds = new ArrayList<>();
        fourthUserAds.add(sixthAd);
        users.get(3).setAds(fourthUserAds);
        sixthAd.setStatus(AdStatus.AVAILABLE);

        Ad seventhAd = new Ad();
        seventhAd.setArticleDescription("Blue satin silk shirt");
        seventhAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
        seventhAd.setPrice(BigDecimal.valueOf(35));
        seventhAd.setTitle("Blue silk shirt");
        seventhAd.setPublisher(users.get(3));
        seventhAd.setArticleState("Neuf avec étiquette");
        fourthUserAds.add(seventhAd);
        users.get(3).setAds(fourthUserAds);
        seventhAd.setStatus(AdStatus.AVAILABLE);

        Ad eighthAd = new Ad();
        eighthAd.setArticleDescription("Nassim N. Taleb's best selling collection; includes: Anti-Fragile, The Black San, Skin in the Game etc ");
        eighthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
        eighthAd.setPrice(BigDecimal.valueOf(150));
        eighthAd.setTitle("Incerto Book Collection");
        eighthAd.setPublisher(users.get(2));
        eighthAd.setArticleState("Neuf sans étiquette");
        thirdUserAds.add(eighthAd);
        users.get(2).setAds(thirdUserAds);
        eighthAd.setStatus(AdStatus.AVAILABLE);

        return Arrays.asList(firstAd, secondAd, thirdAd, fourthAd, fifthAd, sixthAd, seventhAd, eighthAd);
    }

    /**
     * Seeds the database with sample ad data.
     * @param ads the list of ads to save.
     */
    private void seedAds(List<Ad> ads){
        this.adRepository.saveAll(ads);
    }

}
