package adeuxpas.back.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.enums.WeekDays;

@Service
public class PreferredScheduleMapperService {

    public PreferredScheduleDto mapToDto(PreferredSchedule preferredSchedule) {
        List<Integer> dayOfWeekList = convertWeekDaysToList(preferredSchedule.getWeekDay());

        return new PreferredScheduleDto(
            preferredSchedule.getId(),
            dayOfWeekList,
            preferredSchedule.getStartTime(),
            preferredSchedule.getEndTime(),
            preferredSchedule.getUser().getId()
        );
        
    }

    private List<Integer> convertWeekDaysToList(WeekDays weekDay) {
        List<Integer> dayOfWeekList = new ArrayList<>();
        dayOfWeekList.add(weekDay.getDayOfWeek());
        return dayOfWeekList;
    }

    public List<PreferredScheduleDto> groupByTimes(List<PreferredScheduleDto> preferredSchedules) {
        List<PreferredScheduleDto> result = new ArrayList<>();

        // Regroupement par startTime et endTime
        Map<String, Map<String, List<PreferredScheduleDto>>> groupedByTime = preferredSchedules.stream()
                .collect(Collectors.groupingBy(PreferredScheduleDto::getStartTime,
                        Collectors.groupingBy(PreferredScheduleDto::getEndTime)));

        // Parcours des groupes
        groupedByTime.forEach((startTime, endTimeMap) -> {
            endTimeMap.forEach((endTime, schedules) -> {
                // Récupération des jours de la semaine sous forme de liste d'entiers
                // Récupération des jours de la semaine sous forme de liste d'entiers
                List<Integer> daysOfWeek = schedules.stream()
                    .flatMap(dto -> dto.getDaysOfWeek().stream()) // Aplatir les listes de jours de la semaine
                    .collect(Collectors.toList()); // Utilisation des valeurs déjà converties
                Long id = schedules.get(0).getId();
                Long userId = schedules.get(0).getUserId();

                // Création du nouveau PreferredScheduleDto et ajout à la liste résultante
                result.add(new PreferredScheduleDto(id, daysOfWeek, LocalTime.parse(startTime), LocalTime.parse(endTime), userId));
            });
        });

        return result;
    }

}
