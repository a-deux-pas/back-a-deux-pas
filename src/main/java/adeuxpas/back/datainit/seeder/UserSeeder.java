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
import java.util.*;

/**
 * Seeder class responsible for generating sample user entities.
 * Each user is initialized with sample data such as email, password, alias, bio
 * etc.
 */
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
        @Value("${seventh.pass}")
        private String pass7;
        @Value("${eighth.pass}")
        private String pass8;

        @Value("${cloudinary.url}")
        private String cloudinaryUrl;

        public UserSeeder(@Autowired BCryptPasswordEncoder passwordEncoder,
                        @Autowired UserRepository userRepository) {
                this.passwordEncoder = passwordEncoder;
                this.userRepository = userRepository;
        }

        /**
         * This method creates user entities.
         * Each user is initialized with sample data such as email, password, role,
         * status, and creation timestamp.
         * 
         * @return a list of users.
         */
        public List<User> createUsers() {
                User first = new User();
                first.setEmail("mbardan@email.ro");
                first.setAlias("Koroviev");
                first.setPassword(passwordEncoder.encode(pass1));
                first.setBio(
                                "J'aime les trouvailles uniques et les bonnes affaires, et je vous propose une sélection éclectique d'articles.");
                first.setCountry("France");
                first.setCity("Maisons-Alfort");
                first.setStreet("10 rue Victor Hugo");
                first.setPostalCode("94700");
                first.setProfilePicture(cloudinaryUrl
                                + "s--s0NpLV2P--/c_fill,h_208,r_200,w_208/v1720983854/profile_picture_koroviev.webp");
                first.setInscriptionDate(LocalDateTime.now().minusDays(2).minusMonths(12));
                first.setAccountStatus(AccountStatus.ACTIVE);
                first.setRole(UserRole.USER);

                User second = new User();
                second.setEmail("daouali@email.com");
                second.setPassword(passwordEncoder.encode(pass2));
                second.setAlias("Dounia");
                second.setBio(
                                "Passionnée d'upcycling, chaque objet que je mets en vente ici est transformé avec soin ce qui les rend uniques.");
                second.setCountry("France");
                second.setCity("Maisons-Alfort");
                second.setStreet("35 Rue Georges Médéric");
                second.setPostalCode("94700");
                second.setProfilePicture(cloudinaryUrl
                                + "s--9rdP4C_B--/c_fill,h_208,r_200,w_208/v1720984693/profile_picture_dounia.webp");
                second.setInscriptionDate(LocalDateTime.now().minusDays(4).minusMonths(11));
                second.setAccountStatus(AccountStatus.REPORTED);
                second.setRole(UserRole.USER);

                User third = new User();
                third.setEmail("lhadida@email.com");
                third.setPassword(passwordEncoder.encode(pass3));
                third.setAlias("Leahad");
                third.setBio(
                                "J'adore chiner mais aussi vendre certains objets de ma collection qui dorment dans mes placards");
                third.setCountry("France");
                third.setCity("Paris");
                third.setStreet("130 rue des Pyrénées");
                third.setPostalCode("75020");
                third.setProfilePicture(cloudinaryUrl
                                + "s--_uDaxAoO--/c_fill,h_308,r_200,w_308/v1720879460/profile_picture_leahad.webp");
                third.setInscriptionDate(LocalDateTime.now().minusDays(1).minusMonths(8));
                third.setAccountStatus(AccountStatus.ACTIVE);
                third.setRole(UserRole.USER);

                User fourth = new User();
                fourth.setEmail("erikaike@email.fr");
                fourth.setPassword(passwordEncoder.encode(pass4));
                fourth.setAlias("Eri");
                fourth.setBio("Passionnée de mode, je vends mes vêtements pour permettre aux plus grand nombre d’être stylé");
                fourth.setCountry("France");
                fourth.setCity("Paris");
                fourth.setStreet("17 Rue du retrait");
                fourth.setPostalCode("75020");
                fourth.setProfilePicture(cloudinaryUrl
                                + "s--5b9phm9D--/c_fill,r_200,w_208/v1720882650/profile_picture_eri.webp");
                fourth.setInscriptionDate(LocalDateTime.now().minusDays(3).minusMonths(6));
                fourth.setAccountStatus(AccountStatus.ACTIVE);
                fourth.setRole(UserRole.ADMIN);

                User fifth = new User();
                fifth.setEmail("jules@email.com");
                fifth.setPassword(passwordEncoder.encode(pass5));
                fifth.setAlias("Julius");
                fifth.setBio("Contactez-moi si vous voulez vous débarasser de vos vieux vinyles :)");
                fifth.setCountry("France");
                fifth.setCity("Paris");
                fifth.setStreet("140 avenue Gambetta");
                fifth.setPostalCode("75020");
                fifth.setProfilePicture(cloudinaryUrl
                                + "s---keIfWVu--/c_fill,e_brightness:57,h_208,r_200,w_208/v1720985054/profile_picture_julius.webp");
                fifth.setInscriptionDate(LocalDateTime.now().minusDays(7).minusMonths(5));
                fifth.setAccountStatus(AccountStatus.ACTIVE);
                fifth.setRole(UserRole.ADMIN);

                User sixth = new User();
                sixth.setEmail("cameron@email.com");
                sixth.setPassword(passwordEncoder.encode(pass6));
                sixth.setAlias("Cameron");
                sixth.setBio("Américain à Paris, je vends des produits de chez moi mais aussi quelques objets locaux.");
                sixth.setCountry("France");
                sixth.setCity("Paris");
                sixth.setStreet("10 rue de la Folie-Méricourt");
                sixth.setPostalCode("75011");
                sixth.setProfilePicture(cloudinaryUrl
                                + "s--adsfqmAb--/c_fill,h_208,r_200,w_208/v1720987563/profile_picture_cameron.webp");
                sixth.setInscriptionDate(LocalDateTime.now().minusDays(8).minusMonths(4));
                sixth.setAccountStatus(AccountStatus.ACTIVE);
                sixth.setRole(UserRole.USER);

                User seventh = new User();
                seventh.setEmail("sofia@email.com");
                seventh.setPassword(passwordEncoder.encode(pass7));
                seventh.setAlias("Sofia");
                seventh.setBio("Aidez-moi à faire le vide dans mon appart :)");
                seventh.setCountry("France");
                seventh.setCity("Paris");
                seventh.setStreet("10 rue de la Folie-Méricourt");
                seventh.setPostalCode("75011");
                seventh.setProfilePicture(cloudinaryUrl
                                + "s--WFqIBn8W--/c_fill,h_208,r_200,w_208/v1720987858/profile_picture_sofia.webp");
                seventh.setInscriptionDate(LocalDateTime.now().minusDays(30));
                seventh.setAccountStatus(AccountStatus.ACTIVE);
                seventh.setRole(UserRole.USER);

                User eighth = new User();
                eighth.setEmail("maxime@email.com");
                eighth.setPassword(passwordEncoder.encode(pass8));
                eighth.setAlias("Max");
                eighth.setBio("Salut moi c'est Max, j'aime mes voisins et les vide-greniers. À trés vite !");
                eighth.setCountry("France");
                eighth.setCity("Paris");
                eighth.setStreet("150 avenue Parmentier");
                eighth.setPostalCode("75011");
                eighth.setProfilePicture(cloudinaryUrl
                                + "s--dWO0ULuj--/c_fill,h_208,r_200,w_208/v1720988533/profile_picture_max.webp");
                eighth.setInscriptionDate(LocalDateTime.now().minusDays(15));
                eighth.setAccountStatus(AccountStatus.ACTIVE);
                eighth.setRole(UserRole.USER);

                return Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eighth);
        }

        /**
         * Seeds the database with sample user data.
         * 
         * @param users the list of users to save.
         */
        public void seedUsers(List<User> users) {
                this.userRepository.saveAll(users);
        }
}
