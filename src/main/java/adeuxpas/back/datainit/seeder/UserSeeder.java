package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class UserSeeder {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${first.pass}")
    private String pass1;
    @Value("${second.pass}")
    private String pass2;
    @Value("${third.pass}")
    private String pass3;
    @Value("${fourth.pass}")
    private String pass4;
    @Value("${fifth.pass}")
    private String pass5;
    @Value("${sixth.pass}")
    private String pass6;

    public UserSeeder(@Autowired BCryptPasswordEncoder passwordEncoder,
                      @Autowired UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    /**
     * This method creates user entities.
     * Each user is initialized with sample data such as email, password, role, status, and creation timestamp.
     * @return a list of users.
     */
    public List<User> createUsers(){
        User first = new User();
        first.setEmail("mbardan@email.ro");
        first.setAlias("Koroviev");
        first.setPassword(passwordEncoder.encode(pass1));
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
        second.setPassword(passwordEncoder.encode(pass2));
        second.setAlias("Dounia");
        second.setBio("bio2");
        second.setCountry("France");
        second.setCity("Paris");
        second.setStreet("5, Garibaldi");
        second.setPostalCode("75001");
        second.setProfilePicture("profilePictureUrl2");
        second.setInscriptionDate(LocalDateTime.now());
        second.setAccountStatus(AccountStatus.REPORTED);
        second.setRole(UserRole.USER);

        User third = new User();
        third.setEmail("lhadida@email.com");
        third.setPassword(passwordEncoder.encode(pass3));
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
        fourth.setPassword(passwordEncoder.encode(pass4));
        fourth.setAlias("Eri");
        fourth.setBio("bio4");
        fourth.setCountry("France");
        fourth.setCity("Lyon");
        fourth.setStreet("5, rue Gabriel Peri");
        fourth.setPostalCode("69002");
        fourth.setProfilePicture("profilePictureUrl4");
        fourth.setInscriptionDate(LocalDateTime.now());
        fourth.setAccountStatus(AccountStatus.ACTIVE);
        fourth.setRole(UserRole.ADMIN);

        User fifth = new User();
        fifth.setEmail("jmoukmir@email.com");
        fifth.setPassword(passwordEncoder.encode(pass5));
        fifth.setAlias("theRabbi");
        fifth.setBio("bio5");
        fifth.setCountry("France");
        fifth.setCity("Lyon");
        fifth.setStreet("7bis, rue de la Synagogue");
        fifth.setPostalCode("66004");
        fifth.setProfilePicture("profilePictureUrl5");
        fifth.setInscriptionDate(LocalDateTime.now());
        fifth.setAccountStatus(AccountStatus.ACTIVE);
        fifth.setRole(UserRole.ADMIN);

        User sixth = new User();
        sixth.setEmail("random@email.com");
        sixth.setPassword(passwordEncoder.encode(pass6));
        sixth.setAlias("Rando");
        sixth.setBio("bio6");
        sixth.setCountry("France");
        sixth.setCity("Lyon");
        sixth.setStreet("sous un pont");
        sixth.setPostalCode("66008");
        sixth.setProfilePicture("profilePictureUrl6");
        sixth.setInscriptionDate(LocalDateTime.now());
        sixth.setAccountStatus(AccountStatus.ACTIVE);
        sixth.setRole(UserRole.USER);

        return Arrays.asList(first, second, third, fourth, fifth, sixth);
    }

    /**
     * Seeds the database with sample user data.
     * @param users the list of users to save.
     */
    public void seedUsers(List<User> users){
        this.userRepository.saveAll(users);
    }
}
