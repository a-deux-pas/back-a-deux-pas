package adeuxpas.back.dto.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import adeuxpas.back.dto.PreferredMeetingPlaceDTO;
import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.ProfilePageUserDTO;
import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.WeekDays;

/**
 * Mapper interface for mapping User entities to DTOs (Data Transfer Objects).
 * <p>
 * This mapper provides methods for mapping User entities to various DTOs,
 * such as ProfilePageUserDTO, PreferredScheduleDTO, and PreferredMeetingPlaceDTO.
 * </p>
 * <p>
 * It uses MapStruct annotations for mapping and Spring component model for integration with Spring.
 * </p>
 * <p>
 * This interface defines mapping methods for converting User entity attributes to corresponding DTO fields.
 * </p>
 * <p>
 * It also includes helper methods.
 * </p>
 * 
 * @author Léa Hadida
 */
@Mapper(
    componentModel = "spring"
)
public interface UserMapper {

    /**
     * Singleton mapper instance for {@code User} to {@code UserDto} conversion.
     */
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Maps a User entity to a ProfilePageUserDTO.
     *
     * @param user The User entity to be mapped.
     * @return The mapped ProfilePageUserDTO.
     */
    @Mapping(source = "inscriptionDate", target = "inscriptionDate", qualifiedByName = "convertDateToString")
    ProfilePageUserDTO mapUserToProfilePageUserDTO(User user);

    /**
     * Converts a LocalDate to a String.
     *
     * @param date The LocalDate to be converted.
     * @return The converted String representation of the date.
     */
    @Named("convertDateToString")
    default String dateToString(LocalDate date) {
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
     * Maps a PreferredMeetingPlace entity to a PreferredMeetingPlaceDTO.
     *
     * @param preferredMeetingPlace The PreferredMeetingPlace entity to be mapped.
     * @return The mapped PreferredMeetingPlaceDTO.
     */
    @Mapping(source = "user.id", target = "userId")
    PreferredMeetingPlaceDTO mapPreferredMettingPlaceToDTO(PreferredMeetingPlace preferredMeetingPlace);
}
