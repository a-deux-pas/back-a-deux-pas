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

import org.json.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.time.*;
import java.util.*;

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
public class UserDatabaseSeeder {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PreferredScheduleRepository preferredScheduleRepository;
    private final PreferredMeetingPlaceRepository preferredMeetingPlaceRepository;
    
    private static final String FRANCE = "France";
    private static final String PARIS = "Paris";

    private SecureRandom random = new SecureRandom(); 
    final Logger logger = LoggerFactory.getLogger(UserDatabaseSeeder.class);

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
    
    /**
     * Constructs a new instance of DatabaseSeeder.
     *
     * @param userRepository  The repository for managing user entities.
     * @param passwordEncoder The password encoder used to encode user passwords before storing them in the database.
     * @param preferredScheduleRepository
     */
    public UserDatabaseSeeder(
        @Autowired UserRepository userRepository,
        @Autowired BCryptPasswordEncoder passwordEncoder,
        @Autowired PreferredScheduleRepository preferredScheduleRepository,
        @Autowired PreferredMeetingPlaceRepository preferredMeetingPlaceRepository
        ){
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

        for (User user : users) {
            //Seeds the database with users preferred schedules
            generateSchedulesForUser(user);
            //Seeds the database with users preferred meeting places
            generatePreferredMeetingPlacesForUser(user);
        }
    }

    /**
     * This method creates user entities.
     * Each user is initialized with sample data such as email, password, role, status, and creation timestamp.
     * @return a list of users.
     */
    private List<User> createUsers(){
        User first = new User("mbardan@email.ro", "Koroviev", passwordEncoder.encode(pass1), "bio1", FRANCE, "Maisons-Alfort", "Victor Hugo", "94700", "profilePictureUrl1", LocalDate.now(), AccountStatus.ACTIVE, UserRole.USER);
        // first.setEmail("mbardan@email.ro");
        // first.setAlias("Koroviev");
        // first.setPassword(passwordEncoder.encode(pass1));
        // first.setBio("bio1");
        // first.setCountry(FRANCE);
        // first.setCity("Maisons-Alfort");
        // first.setStreet("Victor Hugo");
        // first.setPostalCode("94700");
        // first.setProfilePicture("profilePictureUrl1");
        // first.setInscriptionDate(LocalDate.now());
        // first.setAccountStatus(AccountStatus.ACTIVE);
        // first.setRole(UserRole.USER);

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
    private void seedUsers(List<User> users){
        this.userRepository.saveAll(users);
    }

    /**
     * Generates random schedules for a given user.
     * @param user The user for whom schedules are generated.
     */
    public void generateSchedulesForUser(User user) {
        for (int i = 0; i < 6; i++) {
            // Generate a new set of schedules until there are no overlaps
            Set<PreferredSchedule> newSchedules;
            do {
                newSchedules = generateRandomScheduleForUser(user);
            } while (isScheduleOverlapping(user, newSchedules));
            for (PreferredSchedule schedule : newSchedules) {
                schedule.setUser(user);
                this.preferredScheduleRepository.save(schedule);
            }
        }
    }

    /**
     * Checks if the new schedules overlap with existing schedules for a user.
     * @param user The user for whom schedules are checked.
     * @param newSchedules The new schedules to check for overlap.
     * @return true if there is an overlap, false otherwise.
     */
    private boolean isScheduleOverlapping(User user, Set<PreferredSchedule> newSchedules) {
        List<PreferredSchedule> existingSchedules = this.preferredScheduleRepository.findPreferredSchedulesByUser(user);
        return existingSchedules.stream()
            .flatMap(existingSchedule -> newSchedules.stream()
                .filter(newSchedule -> existingSchedule.getWeekDay() == newSchedule.getWeekDay() &&
                    existingSchedule.getEndTime().isAfter(newSchedule.getStartTime()) &&
                    existingSchedule.getStartTime().isBefore(newSchedule.getEndTime())))
            .findFirst()
            .isPresent();
    }
    
    /**
     * Generates a set of random schedules.
     * @return A set of random schedules.
    */
    private Set<PreferredSchedule> generateRandomScheduleForUser(User user) {
        Set<PreferredSchedule> schedules = new HashSet<>();
        WeekDays randomDay = WeekDays.values()[random.nextInt(WeekDays.values().length)]; 
        LocalTime startTime = generateRandomTime();
        LocalTime endTime = generateRandomEndTime(startTime);
        PreferredSchedule schedule = new PreferredSchedule(randomDay, startTime, endTime, user);
        schedules.add(schedule);
        return schedules;
    }
    
    /**
     * Generates a random time between 8 AM and 9 PM.
     * @return A random time between 8 AM and 9 PM.
     */
    private LocalTime generateRandomTime() {
        // Generate a random hour between 8 and 21
        int hour = random.nextInt(13) + 8;
        return LocalTime.of(hour, 0); 
    }

    /**
     * Generates a random end time based on the start time.
     * @param startTime The start time of the schedule.
     * @return A random end time based on the start time.
     */
    private LocalTime generateRandomEndTime(LocalTime startTime) {
        // Generate a random hour between the start time and 12 hours later
        int hour = startTime.getHour() + random.nextInt(12) + 1; 
        // Ensure end time doesn't exceed 10:00 PM (22:00)
        hour = Math.min(hour, 22); 
        return LocalTime.of(hour, 0); 
    }

    /**
     * Fetches addresses from the data.gouv.fr api and returns them as a list of PreferredMeetingPlace objects.
     * @return List of PreferredMeetingPlace objects fetched from the API
     */
    private void generatePreferredMeetingPlacesForUser(User user){
        String userPostalCode = user.getPostalCode();
        try {
            // Construct URI for API endpoint
            URI uri = new URI("https://api-adresse.data.gouv.fr/search/?q=8+bd+du+port&postcode=" + userPostalCode); 
            
            // Open connection to the API
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check the response code
            int responsecode = connection.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                // Simplify reading text from a character input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // String concatenation
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Parse JSON response
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response.toString());
                JsonNode addresses = jsonResponse.get("features");
                for (JsonNode address : addresses) {
                    JsonNode addressDetails = address.get("properties");
                
                    // Extract data and create PreferredMeetingPlace object
                    String name = addressDetails.get("street").asText();
                    String street = addressDetails.get("name").asText();
                    String city = addressDetails.get("city").asText();
                    String postalCode = addressDetails.get("postcode").asText();
                
                    PreferredMeetingPlace preferredMeetingPlace = new PreferredMeetingPlace(name, street, city, postalCode, user);
                    this.preferredMeetingPlaceRepository.save(preferredMeetingPlace);
                }
            }
        } catch (Exception e) {
            logger.atError().log("Error occurred while fetching data from the API: " + e.getMessage());
        }
    }
}
