package adeuxpas.back.datainit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


// executes custom logic or tasks at the start of the application
// (after the Spring application context is initialized and before the application starts serving requests)
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final DatabaseSeeder seeder;

    public CommandLineRunnerImpl(@Autowired DatabaseSeeder seeder){
        this.seeder = seeder;
    }

    @Override
    public void run(String... args) throws Exception {
        seeder.seedDatabase();
    }
}
