package adeuxpas.back.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import adeuxpas.back.dto.PreferredMeetingPlaceDTO;
import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.ProfilePageUserDTO;
import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.WeekDays;

@Mapper(
    componentModel = "spring"
)
public interface UserMapper {

    ProfilePageUserDTO mapUserToProfilePageUserDTO(User user);

    @Mapping(source = "weekDay", target = "daysOfWeek", qualifiedByName = "convertWeekDayToList")
    @Mapping(source = "user.id", target = "userId")
    PreferredScheduleDTO mapPreferredScheduleToDTO(PreferredSchedule preferredSchedule);
    
    @Named("convertWeekDayToList")
    default List<Integer> convertWeekDayToList(WeekDays weekDay) {
        List<Integer> daysOfWeek = new ArrayList<>();
        daysOfWeek.add(weekDay.getDayOfWeek());
        return daysOfWeek;
    }

    @Mapping(source = "user.id", target = "userId")
    PreferredMeetingPlaceDTO mapPreferredMettingPlaceToDTO(PreferredMeetingPlace preferredMeetingPlace);
}
