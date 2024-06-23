package adeuxpas.back.dto.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import adeuxpas.back.dto.CityAndPostalCodeResponseDTO;
import adeuxpas.back.dto.NotificationDTO;
import org.mapstruct.*;

import adeuxpas.back.dto.PreferredMeetingPlaceDTO;
import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.SellerHomeResponseDTO;
import adeuxpas.back.dto.UserProfileResponseDTO;
import adeuxpas.back.dto.UserProfileRequestDTO;
import adeuxpas.back.entity.Notification;
import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.EventNames;
import adeuxpas.back.enums.WeekDays;

/**
 * Mapper interface for mapping user-related entities to DTOs (Data Transfer
 * Objects).
 * <p>
 * This mapper provides methods for mapping user-related entities to various
 * DTOs
 * </p>
 * <p>
 * It uses MapStruct annotations for mapping and Spring component model for
 * integration with Spring.
 * </p>
 * <p>
 * This interface defines mapping methods for converting user-related entities
 * attributes to corresponding DTO fields and DTO fiels user-related entities
 * attributes.
 * </p>
 * <p>
 * It also includes helper methods.
 * </p>
 *
 * @author Léa Hadida
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    /**
     * Maps a ProfileRequestDTO to an User entity.
     *
     * @param profiledDto The UserProfileRequestDTO to be mapped.
     * @param user        The target entity.
     */
    void mapProfileUserToUser(UserProfileRequestDTO profileDto, @MappingTarget User user);

    /**
     * Maps a User entity to a UserProfileResponseDTO.
     *
     * @param user The User entity to be mapped.
     * @return The mapped UserProfileResponseDTO.
     */
    @Mapping(source = "inscriptionDate", target = "inscriptionDate", qualifiedByName = "convertDateToString")
    UserProfileResponseDTO mapUserToUserProfileResponseDTO(User user);

    /**
     * Converts a LocalDate to a String.
     *
     * @param date The LocalDate to be converted.
     * @return The converted String representation of the date.
     */
    @Named("convertDateToString")
    default String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Maps a PreferredSchedule entity to a PreferredScheduleDTO.
     *
     * @param preferredSchedule The PreferredSchedule entity to be mapped.
     * @return The mapped PreferredScheduleDTO.
     */
    @Mapping(source = "weekDay", target = "daysOfWeek", qualifiedByName = "convertWeekDayToIntegerList")
    @Mapping(source = "user.id", target = "userId")
    PreferredScheduleDTO mapPreferredScheduleToDTO(PreferredSchedule preferredSchedule);

    /**
     * Maps a PreferredScheduleDTO entity to a PreferredSchedule entity.
     *
     * @param preferredScheduleDTO The PreferredScheduleDTO to be mapped.
     * @return The mapped PreferredSchedule entity.
     */
    @Mapping(source = "daysOfWeek", target = "weekDay", qualifiedByName = "convertIntegerListToWeekDay")
    @Mapping(source = "userId", target = "user.id")
    PreferredSchedule mapDTOtoPreferredSchedule(PreferredScheduleDTO preferredScheduleDTO);

    /**
     * Converts a WeekDays enum value to an Integer list.
     *
     * @param weekDay The WeekDays enum value to be converted.
     * @return The converted Integer list representing the day of the week.
     */
    @Named("convertWeekDayToIntegerList")
    default List<Integer> convertWeekDayToIntegerList(WeekDays weekDay) {
        List<Integer> daysOfWeek = new ArrayList<>();
        daysOfWeek.add(weekDay.getDayOfWeek());
        return daysOfWeek;
    }

    /**
     * Converts an Integer list containing one element to a WeekDay enum.
     *
     * @param daysOfWeek integers to be converted.
     * @return the day of the week.
     */
    @Named("convertIntegerListToWeekDay")
    default WeekDays convertIntegerToWeekDay(List<Integer> daysOfWeek) {
        return WeekDays.getWeekDayfromInteger(daysOfWeek.get(0));
    }

    /**
     * Maps a PreferredMeetingPlace entity to a PreferredMeetingPlaceDTO.
     *
     * @param preferredMeetingPlace The PreferredMeetingPlace entity to be mapped.
     * @return The mapped PreferredMeetingPlaceDTO.
     */
    @Mapping(source = "user.id", target = "userId")
    PreferredMeetingPlaceDTO mapPreferredMeetingPlaceToDTO(PreferredMeetingPlace preferredMeetingPlace);

    /**
     * Maps a PreferredMeetingPlaceDTO to a PreferredMeetingPlace entity.
     *
     * @param preferredMeetingPlace The PreferredMeetingPlaceDTO to be mapped.
     * @return The mapped PreferredMeetingPlaceDTO entity.
     */
    @Mapping(source = "userId", target = "user.id")
    PreferredMeetingPlace mapDTOtoPreferredMeetingPlace(PreferredMeetingPlaceDTO preferredMeetingPlaceDTO);

    /**
     * Maps a NotificationDTO to a Notification entity.
     *
     * @param notificationDTO The notificationDTO to be mapped.
     * @return The mapped Notification entity.
     */
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventName", target = "eventName", qualifiedByName = "convertStringToEventName")
    Notification mapDTOtoNotification(NotificationDTO notificationDTO);

    /**
     * Converts a string to an EventNames enum.
     *
     * @param value string to be converted.
     * @return event name.
     */
    @Named("convertStringToEventName")
    default EventNames convertStringToEventName(String value) {
        return EventNames.getEventNamefromString(value);
    }

    /**
     * Maps a user entity to a CityAndPostalCodeResponseDTO.
     *
     * @param user The user to be mapped.
     * @return The mapped CityAndPostalCodeResponseDTO.
     */
    CityAndPostalCodeResponseDTO userToCityAndPostalCodeDTO(User user);

    /**
     * Maps a user entity to a SellerHomeResponseDTO.
     *
     * @param user The user to be mapped.
     * @return The mapped SellerHomeResponseDTO.
     */
    SellerHomeResponseDTO userToSellerHomeResponseDTO(User user);
}
