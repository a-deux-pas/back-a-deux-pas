package adeuxpas.back.datainit;

import adeuxpas.back.datainit.seeder.AdSeeder;
import adeuxpas.back.datainit.seeder.ArticlePictureSeeder;
import adeuxpas.back.datainit.seeder.UserSeeder;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Executes custom logic or tasks at the start of the application,
 * after the Spring application context is initialized and before the application starts serving requests.
 * <p>
 * This class implements the {@code CommandLineRunner} interface, allowing it to be executed as part of the application startup sequence,
 * when the "default profile" is active.
 * </p>
 * <p>
 * The run method is invoked by Spring Boot when the application is started.
 * It delegates the seeding of the database to the DatabaseSeeder component.
 * </p>
 * <p>
 * The {@code DatabaseSeeder} is injected via constructor-based dependency injection.
 * Upon execution, the run method calls the seedDatabase method of the injected {@code DatabaseSeeder} instance to populate the database with initial data.
 * </p>
 * This class is annotated with {@code @Component} to mark it as a Spring-managed component and enable automatic detection and registration by Spring's component scanning mechanism.
 *
 * @author Mircea Bardan
 */

@Component
public class CmdLinePopulateDatabase implements CommandLineRunner {
    private final UserSeeder userSeeder;
    private final AdSeeder adSeeder;
    private final ArticlePictureSeeder articlePictureSeeder;


    /**
     * Constructor for CmdLinePopulateDatabase.
     * @param userSeeder
     * @param adSeeder
     * @param articlePictureSeeder
     */
    public CmdLinePopulateDatabase(@Autowired UserSeeder userSeeder,
                                   @Autowired AdSeeder adSeeder,
                                   @Autowired ArticlePictureSeeder articlePictureSeeder){
        this.userSeeder = userSeeder;
        this.adSeeder =adSeeder;
        this.articlePictureSeeder = articlePictureSeeder;
    }

    /**
     * Executes custom logic or tasks at the start of the application.
     * This method is invoked by Spring Boot when the application is started.
     * It delegates the seeding of the database to the individual Seeder components.
     *
     * @param args Command-line arguments passed to the application.
     * @throws Exception If an error occurs during the execution of the run method.
     */
    @Override
    public void run(String... args) throws Exception {
        List<User> users = this.userSeeder.createUsers();
        this.userSeeder.seedUsers(users);

        List<Ad> ads = this.adSeeder.createAds(users);
        this.adSeeder.seedAds(ads);

        this.articlePictureSeeder.seedArticlePictures(ads);
    }
}
