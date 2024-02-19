package adeuxpas.back.datainit;

import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


// class used to seed the database with initial sample data
@Component
public class DatabaseSeeder {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DatabaseSeeder(@Autowired UserRepository userRepository,
                            @Autowired BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void seedDatabase(){
        seedUsers();
    }

    private void seedUsers(){
        this.userRepository.save(new User("mbardan@email.com", passwordEncoder.encode("password1"), "Koroviev", "bio", "France", "Maisons Alfort", "Victor Hugo", "94700", "profilePictureUrl", LocalDateTime.now(), AccountStatus.ACTIVE,  UserRole.USER));
        this.userRepository.save(new User("daouali@email.fr", passwordEncoder.encode("password2"), "Dounia", "bio", "France", "Paris", "5, Garibaldi", "75000", "profilePictureUrl", LocalDateTime.now(), AccountStatus.REPORTED,  UserRole.USER));
        this.userRepository.save(new User("lhadida@email.com", passwordEncoder.encode("password3"), "Leahad", "bio", "France", "Marseille", "40 Av de la Liberte", "13000", "profilePictureUrl", LocalDateTime.now(), AccountStatus.SUSPENDED,  UserRole.USER));
        this.userRepository.save(new User("erikaike@email.com", passwordEncoder.encode("password4"), "Eri", "bio", "France", "Lyon", "Prom. Germain Sablon", "69000", "profilePictureUrl", LocalDateTime.now(), AccountStatus.ACTIVE,  UserRole.ADMIN));
        this.userRepository.save(new User("jmoukmir@email.com", passwordEncoder.encode("password5"), "theRabbi", "bio", "France", "Bobigny", "6 rue de la Synagogue", "93000", "profilePictureUrl", LocalDateTime.now(), AccountStatus.ACTIVE,  UserRole.ADMIN));
    }
}
