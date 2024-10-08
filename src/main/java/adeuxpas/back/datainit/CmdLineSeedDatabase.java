package adeuxpas.back.datainit;

import adeuxpas.back.datainit.seeder.*;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Executes custom logic or tasks at the start of the application,
 * after the Spring application context is initialized and before the
 * application starts serving requests.
 * <p>
 * This class implements the {@code CommandLineRunner} interface, allowing it to
 * be executed as part of the application startup sequence.
 * </p>
 * <p>
 * The run method is invoked by Spring Boot when the application is started.
 * It delegates the seeding of the database to the Seeder components.
 * </p>
 * <p>
 * </p>
 * This class is annotated with {@code @Component} to mark it as a
 * Spring-managed component and enable automatic detection and registration by
 * Spring's component scanning mechanism.
 *
 * @author Mircea Bardan
 */

@Component
public class CmdLineSeedDatabase implements CommandLineRunner {
    private final UserSeeder userSeeder;
    private final AdSeeder adSeeder;
    private final ArticlePictureSeeder articlePictureSeeder;
    private final PreferredScheduleSeeder preferredScheduleSeeder;
    private final PreferredMeetingPlaceSeeder preferredMeetingPlaceSeeder;
    private final MeetingSeeder meetingSeeder;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final Environment environment;

    /**
     * Constructor for CmdLineSeedDatabase.
     * 
     * @param userSeeder
     * @param adSeeder
     * @param preferredScheduleSeeder
     * @param preferredMeetingPlaceSeeder
     * @param meetingSeeder
     */
    public CmdLineSeedDatabase(@Autowired UserSeeder userSeeder,
            @Autowired AdSeeder adSeeder,
            @Autowired ArticlePictureSeeder articlePictureSeeder,
            @Autowired PreferredScheduleSeeder preferredScheduleSeeder,
            @Autowired PreferredMeetingPlaceSeeder preferredMeetingPlaceSeeder,
            @Autowired MeetingSeeder meetingSeeder,
            @Autowired UserRepository userRepository,
            @Autowired AdRepository adRepository,
            @Autowired Environment environment) {
        this.userSeeder = userSeeder;
        this.adSeeder = adSeeder;
        this.articlePictureSeeder = articlePictureSeeder;
        this.preferredScheduleSeeder = preferredScheduleSeeder;
        this.preferredMeetingPlaceSeeder = preferredMeetingPlaceSeeder;
        this.meetingSeeder = meetingSeeder;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.environment = environment;
    }

    /**
     * Delegates the seeding of the database to the specific Seeder components.
     *
     * @param args Command-line arguments passed to the application.
     * @throws Exception If an error occurs during the execution of the run method.
     */
    @Override
    public void run(String... args) throws Exception {
        // Determine the active profile
        boolean isDevProfile = Arrays.asList(environment.getActiveProfiles()).contains("dev");
        if (isDevProfile) {
            // Development profile: Insert data regardless of existing records
            seedData();
        } else {
            // Production profile: Insert data only if tables are empty
            if (userRepository.count() == 0 && adRepository.count() == 0) {
                seedData();
            }
        }
    }

    private void seedData() {
        List<User> users = this.userSeeder.createUsers();
        this.userSeeder.seedUsers(users);

        List<Ad> ads = this.adSeeder.createAds(users);
        this.adSeeder.seedAds(ads);
        this.articlePictureSeeder.seedArticlePictures(ads);

        for (User user : users) {
            // Seeds the database with users preferred schedules
            this.preferredScheduleSeeder.generateSchedulesForUser(user);
            // Seeds the database with users preferred meeting places
            this.preferredMeetingPlaceSeeder.generatePreferredMeetingPlacesForUser(user);
        }

        // Seed meetings
        this.meetingSeeder.seedMeetings();
        // Seed favorite ads
        this.adSeeder.seedFavoritesAds();
    }
}
