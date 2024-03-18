package adeuxpas.back.datainit;

import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import adeuxpas.back.enums.WeekDays;
import adeuxpas.back.repository.PreferredMeetingPlaceRepository;
import adeuxpas.back.repository.PreferredScheduleRepository;
import adeuxpas.back.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final BCryptPasswordEncoder passwordEncoder;
    private final PreferredScheduleRepository preferredScheduleRepository;
    private final PreferredMeetingPlaceRepository preferredMeetingPlaceRepository;

    /**
     * Constructs a new instance of DatabaseSeeder.
     *
     * @param userRepository  The repository for managing user entities.
     * @param passwordEncoder The password encoder used to encode user passwords before storing them in the database.
     * @param preferredScheduleRepository
     */
    public DatabaseSeeder(@Autowired UserRepository userRepository,
                            @Autowired BCryptPasswordEncoder passwordEncoder,
                            @Autowired PreferredScheduleRepository preferredScheduleRepository,
                            @Autowired PreferredMeetingPlaceRepository preferredMeetingPlaceRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.preferredScheduleRepository = preferredScheduleRepository;
        this.preferredMeetingPlaceRepository = preferredMeetingPlaceRepository;
    }

    /**
     * Seeds the database with initial sample data.
     * This method delegates the seeding process to individual seeding methods for different entities.
     */
    public void seedDatabase(){
        List<User> users = createUsers();
        seedUsers(users);

        seedPreferredSchedules();
        
        seedPreferredMeetingPlaces();
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
        first.setInscriptionDate(LocalDate.now());
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
        second.setInscriptionDate(LocalDate.now());
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
        third.setProfilePicture("https://urlz.fr/pVV1");
        third.setInscriptionDate(LocalDate.now());
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
        fourth.setProfilePicture("https://urlz.fr/pVV3");
        fourth.setInscriptionDate(LocalDate.now());
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
        fifth.setInscriptionDate(LocalDate.now());
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


    private void seedPreferredSchedules(){
        User Lea = userRepository.findById(3L).orElse(null); 
        PreferredSchedule preferredSchedule1 = new PreferredSchedule(WeekDays.LUNDI, LocalTime.of(8, 0), LocalTime.of(10, 0));
        preferredSchedule1.setUser(Lea);
        this.preferredScheduleRepository.save(preferredSchedule1);
        PreferredSchedule preferredSchedule2 = new PreferredSchedule(WeekDays.MARDI, LocalTime.of(8, 0), LocalTime.of(10, 0));
        preferredSchedule2.setUser(Lea);
        this.preferredScheduleRepository.save(preferredSchedule2);
        PreferredSchedule preferredSchedule3 = new PreferredSchedule(WeekDays.MERCREDI, LocalTime.of(18, 0), LocalTime.of(20, 0));
        preferredSchedule3.setUser(Lea);
        this.preferredScheduleRepository.save(preferredSchedule3);

        User Eri = userRepository.findById(4L).orElse(null); 
        PreferredSchedule preferredSchedule4 = new PreferredSchedule(WeekDays.SAMEDI, LocalTime.of(20, 0), LocalTime.of(21, 0));
        preferredSchedule4.setUser(Eri);
        this.preferredScheduleRepository.save(preferredSchedule4);
        PreferredSchedule preferredSchedule5 = new PreferredSchedule(WeekDays.DIMANCHE, LocalTime.of(15, 0), LocalTime.of(20, 0));
        preferredSchedule5.setUser(Eri);
        this.preferredScheduleRepository.save(preferredSchedule5);
    }

    private void seedPreferredMeetingPlaces(){
        User Lea = userRepository.findById(3L).orElse(null); 
        PreferredMeetingPlace meetingPlace1 = new PreferredMeetingPlace("France", "75020", "Paris", "61 place Gambetta", "Mairie du 20e arrondissement");
        meetingPlace1.setUser(Lea);
        this.preferredMeetingPlaceRepository.save(meetingPlace1);
        PreferredMeetingPlace meetingPlace2 = new PreferredMeetingPlace("France", "75020", "Paris", "place Martin Nadaud", "Place Martin Nadaud");
        meetingPlace2.setUser(Lea);
        this.preferredMeetingPlaceRepository.save(meetingPlace2);
        PreferredMeetingPlace meetingPlace3 = new PreferredMeetingPlace("France", "75020", "Paris", "120 rue Orfila", "Métro Pelleport");
        meetingPlace3.setUser(Lea);
        this.preferredMeetingPlaceRepository.save(meetingPlace3);
        PreferredMeetingPlace meetingPlace4 = new PreferredMeetingPlace("France", "75011", "Paris", "Avenue de la République", "Métro Père Lachaise");
        meetingPlace4.setUser(Lea);
        this.preferredMeetingPlaceRepository.save(meetingPlace4);
        PreferredMeetingPlace meetingPlace5 = new PreferredMeetingPlace("France", "75020", "Paris", "Rue de Paris", "Métro Ménilmontant");
        meetingPlace5.setUser(Lea);
        this.preferredMeetingPlaceRepository.save(meetingPlace5);
    }
}
