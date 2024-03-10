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

    /**
     * This method creates ad entities.
     * Each ad is initialized with sample data such as title, price, creation date, publisher etc.
     * @param users the list of potential ad publishers.
     * @return a list of ads.
     */
    private List<Ad> createAds(List<User> users){
        Ad firstAd = new Ad();
        firstAd.setArticleDescription("first description");
        firstAd.setCreationDate(LocalDateTime.now());
        firstAd.setPrice(BigDecimal.valueOf(9.99));
        firstAd.setTitle("First ad");
        firstAd.setPublisher(users.get(0));
        List<Ad> firstUserAds = new ArrayList<>();
        firstUserAds.add(firstAd);
        users.get(0).setAds(firstUserAds);
        firstAd.setStatus(AdStatus.AVAILABLE);

        Ad secondAd = new Ad();
        secondAd.setArticleDescription("second description");
        secondAd.setCreationDate(LocalDateTime.now());
        secondAd.setPrice(BigDecimal.valueOf(12.99));
        secondAd.setTitle("second ad");
        secondAd.setPublisher(users.get(1));
        List<Ad> secondUserAds = new ArrayList<>();
        secondUserAds.add(secondAd);
        users.get(1).setAds(secondUserAds);
        secondAd.setStatus(AdStatus.AVAILABLE);

        Ad thirdAd = new Ad();
        thirdAd.setArticleDescription("third description");
        thirdAd.setCreationDate(LocalDateTime.now());
        thirdAd.setPrice(BigDecimal.valueOf(965132));
        thirdAd.setTitle("third ad");
        thirdAd.setPublisher(users.get(0));
        firstUserAds.add(thirdAd);
        users.get(0).setAds(firstUserAds);
        thirdAd.setStatus(AdStatus.AVAILABLE);

        Ad fourthAd = new Ad();
        fourthAd.setArticleDescription("fourth description");
        fourthAd.setCreationDate(LocalDateTime.now());
        fourthAd.setPrice(BigDecimal.valueOf(999.99));
        fourthAd.setTitle("Fourth ad");
        fourthAd.setPublisher(users.get(0));
        firstUserAds.add(fourthAd);
        users.get(0).setAds(firstUserAds);
        fourthAd.setStatus(AdStatus.AVAILABLE);

        Ad fifthAd = new Ad();
        fifthAd.setArticleDescription("fifth description");
        fifthAd.setCreationDate(LocalDateTime.now());
        fifthAd.setPrice(BigDecimal.valueOf(0.99));
        fifthAd.setTitle("Fifth ad");
        fifthAd.setPublisher(users.get(2));
        List<Ad> thirdUserAds = new ArrayList<>();
        thirdUserAds.add(fifthAd);
        users.get(2).setAds(thirdUserAds);
        fifthAd.setStatus(AdStatus.AVAILABLE);

        return Arrays.asList(firstAd, secondAd, thirdAd, fourthAd, fifthAd);
    }

    /**
     * Seeds the database with sample ad data.
     * @param ads the list of ads to save.
     */
    private void seedAds(List<Ad> ads){
        this.adRepository.saveAll(ads);
    }

    private void seedArticlePictures(List<Ad> ads) {
        this.articlePictureRepository.save(new ArticlePicture(("picture_url1"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("picture_url2"), ads.get(1)));
        this.articlePictureRepository.save(new ArticlePicture(("picture_url2"), ads.get(2)));
        this.articlePictureRepository.save(new ArticlePicture(("picture_url2"), ads.getLast()));
    }

}
