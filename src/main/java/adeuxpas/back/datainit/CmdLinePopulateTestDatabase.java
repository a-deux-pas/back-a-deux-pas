package adeuxpas.back.datainit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * This class is used to populate The Test database with mock data, for Integration Test purposes,
 * when running the application with the 'test' profile active
 * <p>
 * The run method delegates the seeding of the database to the same DatabaseSeeder component that seeds the main Database.
 * </p>
 * <p>
 * The {@code DatabaseSeeder} is injected via constructor-based dependency injection.
 * Upon execution, the run method calls the seedDatabase method of the injected {@code DatabaseSeeder} instance
 * to populate the IntegrationTestDB with initial data
 * </p>
 * @author Mircea Bardan
 */
@Component
@Profile("test")
public class CmdLinePopulateTestDatabase implements CommandLineRunner {
    private final TestDatabaseSeeder seeder;

    public CmdLinePopulateTestDatabase(@Autowired TestDatabaseSeeder seeder){
        this.seeder = seeder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seeder.seedDatabase();
    }
}
