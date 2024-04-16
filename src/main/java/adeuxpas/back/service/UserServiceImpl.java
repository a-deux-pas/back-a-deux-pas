package adeuxpas.back.service;

import adeuxpas.back.dto.CityAndPostalCodeDTO;
import adeuxpas.back.dto.mapper.AdMapper;
import adeuxpas.back.dto.mapper.UserMapper;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation class for the UserService interface.
 * <p>
 * This service class provides implementations for user-related operations.
 * </p>
 * <p>
 * It interacts with the UserRepository to perform database operations related to users.
 * </p>
 *
 * @author Mircea Bardan
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper mapper;

    /**
     * Constructor for UserServiceImpl.
     *
     * @param userRepository The UserRepository for interacting with user-related database operations.
     */
    public UserServiceImpl(@Autowired UserRepository userRepository, @Autowired UserMapper mapper){
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    /**
     * Saves a user to the database.
     *
     * @param user The user to save.
     */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return An optional containing the user if found, or an empty optional otherwise.
     */
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Set<CityAndPostalCodeDTO> getUniqueCitiesAndPostalCodes() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(mapper::userToCityAndPostalCodeDTO).collect(Collectors.toSet());
    }
}
