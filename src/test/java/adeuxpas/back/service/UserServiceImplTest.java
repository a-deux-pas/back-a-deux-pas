package adeuxpas.back.service;

import adeuxpas.back.dto.ProfilePageUserDTO;
import adeuxpas.back.dto.mapper.UserMapper;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private final Faker faker = new Faker();

    // Create the instance of UserSerive and automatically inject the studentRepository mock
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    // Create a mock version of the object
    @Mock 
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;


    @Test
    public void testFindUserProfileInfoById_UserFound() {
        // Mocking User data
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setAlias(faker.name().firstName());
        user.setBio(faker.lorem().paragraph());
        user.setCountry(faker.address().country());
        user.setCity(faker.address().city());
        user.setStreet(faker.address().streetAddress());
        user.setPostalCode(faker.address().zipCode());
        user.setProfilePicture(faker.internet().url());
        user.setInscriptionDate(LocalDate.now());
        // Mocking User DTO data
        ProfilePageUserDTO mappedUser = new ProfilePageUserDTO();
        mappedUser.setId(user.getId());
        mappedUser.setAlias(user.getAlias());
        mappedUser.setBio(user.getBio());
        mappedUser.setCountry(user.getCountry());
        mappedUser.setCity(user.getCity());
        mappedUser.setStreet(user.getStreet());
        mappedUser.setPostalCode(user.getPostalCode());
        mappedUser.setProfilePicture(user.getProfilePicture());
        mappedUser.setInscriptionDate(user.getInscriptionDate().toString());

        // Mocking repository behavior
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        // Mocking userMapper behavior
        when(userMapper.mapUserToProfilePageUserDTO(any(User.class))).thenReturn(mappedUser);
        // Call the method to test
        ProfilePageUserDTO userProfileInfo = userServiceImpl.findUserProfileInfoById(userId);
        // Assertions
        assertNotNull(userProfileInfo);
        assertEquals(userId, userProfileInfo.getId());
        assertEquals(user.getAlias(), userProfileInfo.getAlias());
        assertEquals(user.getBio(), userProfileInfo.getBio());
        assertEquals(user.getCountry(), userProfileInfo.getCountry());
        assertEquals(user.getCity(), userProfileInfo.getCity());
        assertEquals(user.getStreet(), userProfileInfo.getStreet());
        assertEquals(user.getPostalCode(), userProfileInfo.getPostalCode());
        assertEquals(user.getProfilePicture(), userProfileInfo.getProfilePicture());
        assertEquals(user.getInscriptionDate(), userProfileInfo.getInscriptionDate());
    }

    @Test
    public void testFindUserProfileInfoById_UserNotFound() {
        // Mocking data
        Long userId = 2L;
        // Mocking repository behavior
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        // Call the method to test
        Throwable exception = assertThrows(EntityNotFoundException.class, 
                    () -> userServiceImpl.findUserProfileInfoById(userId));
        // Assertion
        assertEquals("User with ID : 2 not Found", exception.getMessage());
        }
    }

