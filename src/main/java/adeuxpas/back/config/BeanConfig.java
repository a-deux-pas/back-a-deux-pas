package adeuxpas.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// this class registers any external dependencies that we might
// need to use, to our app's Spring Application Context, so that they can be
// found by Spring at Component Scanning.
@Configuration
public class BeanConfig {

    // Bean used to register an external password encoder
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
