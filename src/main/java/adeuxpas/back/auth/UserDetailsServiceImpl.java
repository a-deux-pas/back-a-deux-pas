package adeuxpas.back.auth;

import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation class for the Spring Security {@code UserDetailsService} interface.
 * <p>
 * The {@code UserDetailsService} interface is used by Spring Security for loading
 * user-specific data during authentication.
 * </p>
 * <p>
 * This implementation retrieves user information from the database using a {@code UserRepository}.
 * It loads the user by their email address and constructs a {@code UserDetails} object,
 * which contains information such as username, password, and authorities (roles).
 * </p>
 * <p>
 * By implementing the {@code UserDetailsService} interface and returning a {@code UserDetails} object,
 * we comply with Spring Security's requirements, allowing it to authenticate users
 * based on the information provided by this custom implementation.
 * </p>
 * <p>
 * This class is annotated with {@code @Service} to indicate that it is a service component
 * and should be automatically detected and registered as a Spring bean.
 * </p>
 *
 * @see org.springframework.security.core.userdetails.UserDetailsService
 * @see org.springframework.security.core.userdetails.UserDetails
 *
 * @author MirceaBardan
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@code UserDetailsServiceImpl} with the specified {@code UserRepository}.
     *
     * @param userRepository The repository for accessing user data from the database.
     */
    public UserDetailsServiceImpl(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Loads user-specific data by the given email address and constructs a {@code UserDetails} object.
     * <p>
     * This method retrieves the user from the database based on the provided email address.
     * If the user is found, their details are loaded into a {@code UserDetails} object,
     * which includes their username (email), password, and authorities (role).
     * </p>
     * <p>
     * If the user is not found, a {@code UsernameNotFoundException} is thrown.
     * </p>
     *
     * @param email The email address of the user to load.
     * @return A {@code UserDetails} object representing the user.
     * @throws UsernameNotFoundException If the user with the given email address is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<adeuxpas.back.entity.User> userInDB = this.userRepository.findByEmail(email);
        if (userInDB.isPresent()) {
            adeuxpas.back.entity.User user = userInDB.get();
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("" + user.getRole()));

            return User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
