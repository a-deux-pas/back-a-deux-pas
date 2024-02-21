package adeuxpas.back.datainit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Executes custom logic or tasks at the start of the application,
 * after the Spring application context is initialized and before the application starts serving requests.
 * <p>
 * This class implements the {@code CommandLineRunner} interface, allowing it to be executed as part of the application startup sequence.
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
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final DatabaseSeeder seeder;

    public CommandLineRunnerImpl(@Autowired DatabaseSeeder seeder){
        this.seeder = seeder;
    }

    /**
     * Executes custom logic or tasks at the start of the application.
     * This method is invoked by Spring Boot when the application is started.
     * It delegates the seeding of the database to the DatabaseSeeder component.
     *
     * @param args Command-line arguments passed to the application.
     * @throws Exception If an error occurs during the execution of the run method.
     */
    @Override
    public void run(String... args) throws Exception {
        seeder.seedDatabase();
    }
}
