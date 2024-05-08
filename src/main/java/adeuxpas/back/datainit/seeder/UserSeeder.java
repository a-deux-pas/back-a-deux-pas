package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class UserSeeder {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserSeeder(@Autowired BCryptPasswordEncoder passwordEncoder,
                      @Autowired UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    private static final String FRANCE = "France";
    private static final String PARIS = "Paris";

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
        first.setCountry(FRANCE);
        first.setCity("Maisons-Alfort");
        first.setStreet("Victor Hugo");
        first.setPostalCode("94700");
        first.setProfilePicture("profilePictureUrl1");
        first.setInscriptionDate(LocalDate.now());
        first.setAccountStatus(AccountStatus.ACTIVE);
        first.setRole(UserRole.USER);

        User second = new User();
        second.setEmail("daouali@email.com");
        second.setPassword(passwordEncoder.encode(pass2));
        second.setAlias("Dounia");
        second.setBio("bio2");
        second.setCountry(FRANCE);
        second.setCity(PARIS);
        second.setStreet("5, Garibaldi");
        second.setPostalCode("75001");
        second.setProfilePicture("profilePictureUrl2");
        second.setInscriptionDate(LocalDate.now());
        second.setAccountStatus(AccountStatus.REPORTED);
        second.setRole(UserRole.USER);

        User third = new User();
        third.setEmail("lhadida@email.com");
        third.setPassword(passwordEncoder.encode(pass3));
        third.setAlias("Leahad");
        third.setBio("Partageuse de trésors. Chaque objet a son histoire, maintenant prêt à en écrire une nouvelle avec vous.");
        third.setCountry(FRANCE);
        third.setCity(PARIS);
        third.setStreet("5, Av de la Liberte");
        third.setPostalCode("75020");
        third.setProfilePicture("https://media.licdn.com/dms/image/D4E03AQFGWeJUTwRTrg/profile-displayphoto-shrink_400_400/0/1668536321799?e=1716422400&v=beta&t=IZtxwRxoipWf34Qrv9OYUda7lHhtRWLMDOhqrcovAAA");
        third.setInscriptionDate(LocalDate.now());
        third.setAccountStatus(AccountStatus.SUSPENDED);
        third.setRole(UserRole.USER);

        User fourth = new User();
        fourth.setEmail("erikaike@email.fr");
        fourth.setPassword(passwordEncoder.encode(pass4));
        fourth.setAlias("Eri");
        fourth.setBio("Passionnée de mode, je vends mes vêtements pour permettre aux plus grand nombre d’être stylé.");
        fourth.setCountry(FRANCE);
        fourth.setCity("Lyon");
        fourth.setStreet("5, rue Gabriel Peri");
        fourth.setPostalCode("69002");
        fourth.setProfilePicture("https://media.licdn.com/dms/image/C4D03AQEPCyzoBB3WHQ/profile-displayphoto-shrink_200_200/0/1559041227281?e=1717027200&v=beta&t=bo3fSv0ufHuLbS1IHuTLJ9YwwixGq-HCiF3CkcshrQc");
        fourth.setInscriptionDate(LocalDate.now());
        fourth.setAccountStatus(AccountStatus.ACTIVE);
        fourth.setRole(UserRole.ADMIN);

        User fifth = new User();
        fifth.setEmail("jmoukmir@email.com");
        fifth.setPassword(passwordEncoder.encode(pass5));
        fifth.setAlias("theRabbi");
        fifth.setBio("bio5");
        fifth.setCountry(FRANCE);
        fifth.setCity("Lyon");
        fifth.setStreet("7bis, rue de la Synagogue");
        fifth.setPostalCode("69003");
        fifth.setProfilePicture("profilePictureUrl5");
        fifth.setInscriptionDate(LocalDate.now());
        fifth.setAccountStatus(AccountStatus.ACTIVE);
        fifth.setRole(UserRole.ADMIN);

        return Arrays.asList(first, second, third, fourth, fifth);
    }

    /**
     * Seeds the database with sample user data.
     * @param users the list of users to save.
     */
    public void seedUsers(List<User> users){
        this.userRepository.saveAll(users);
    }

}
