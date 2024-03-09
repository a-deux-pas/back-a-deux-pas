package adeuxpas.back.datainit;

import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import adeuxpas.back.repository.PreferredScheduleRepository;
import adeuxpas.back.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    /**
     * Constructs a new instance of DatabaseSeeder.
     *
     * @param userRepository  The repository for managing user entities.
     * @param passwordEncoder The password encoder used to encode user passwords before storing them in the database.
     * @param preferredScheduleRepository
     */
    public DatabaseSeeder(@Autowired UserRepository userRepository,
                            @Autowired BCryptPasswordEncoder passwordEncoder,
                            @Autowired PreferredScheduleRepository preferredScheduleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.preferredScheduleRepository = preferredScheduleRepository;
    }

    /**
     * Seeds the database with initial sample data.
     * This method delegates the seeding process to individual seeding methods for different entities.
     */
    public void seedDatabase(){
        seedUsers();
    }

    /**
     * Seeds the database with sample user data.
     * This method creates and saves user entities to the userRepository.
     * Each user is initialized with sample data such as email, password, role, status, and creation timestamp.
     */

    private void seedUsers(){
        this.userRepository.save(new User("mbardan@email.ro", passwordEncoder.encode("pass1"), "Koroviev", "bio1", "Romania", "Cluj-Napoca", "Victor Hugo", "94700", "profilePictureUrl1", LocalDateTime.now(), AccountStatus.ACTIVE,  UserRole.USER));
        this.userRepository.save(new User("daouali@email.com", passwordEncoder.encode("pass2"), "Dounia", "bio2", "Algeria", "Alger", "5, Garibaldi", "75000", "profilePictureUrl2", LocalDateTime.now(), AccountStatus.REPORTED,  UserRole.USER));
        this.userRepository.save(new User("lhadida@email.com", passwordEncoder.encode("pass3"), "Leahad", "bio3", "Germany", "Dortmund", "40 Av de la Liberte", "13000", "profilePictureUrl3", LocalDateTime.now(), AccountStatus.SUSPENDED,  UserRole.USER));
        this.userRepository.save(new User("erikaike@email.po", passwordEncoder.encode("pass4"), "Eri", "bio4", "Portugal", "Porto", "Prom. Germain Sablon", "69000", "profilePictureUrl4", LocalDateTime.now(), AccountStatus.ACTIVE,  UserRole.ADMIN));
        this.userRepository.save(new User("jmoukmir@email.com", passwordEncoder.encode("pass5"), "theRabbi", "bio5", "Israel", "Tel-Aviv", "6 rue de la Synagogue", "93000", "profilePictureUrl5", LocalDateTime.now(), AccountStatus.ACTIVE,  UserRole.ADMIN));
    
        User Lea = userRepository.findById(3L).orElse(null); 
        PreferredSchedule preferredSchedule1 = new PreferredSchedule("Lundi", LocalTime.of(8, 0), LocalTime.of(10, 0));
        preferredSchedule1.setUser(Lea);
        this.preferredScheduleRepository.save(preferredSchedule1);
        PreferredSchedule preferredSchedule2 = new PreferredSchedule("Mardi", LocalTime.of(17, 0), LocalTime.of(18, 0));
        preferredSchedule2.setUser(Lea);
        this.preferredScheduleRepository.save(preferredSchedule2);

        User Eri = userRepository.findById(4L).orElse(null); 
        PreferredSchedule preferredSchedule3 = new PreferredSchedule("Mercredi", LocalTime.of(20, 0), LocalTime.of(21, 0));
        preferredSchedule3.setUser(Eri);
        this.preferredScheduleRepository.save(preferredSchedule3);
        PreferredSchedule preferredSchedule4 = new PreferredSchedule("Samedi", LocalTime.of(15, 0), LocalTime.of(20, 0));
        preferredSchedule4.setUser(Eri);
        this.preferredScheduleRepository.save(preferredSchedule4);
    }

}
