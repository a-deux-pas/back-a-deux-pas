package adeuxpas.back.dto;

import org.springframework.stereotype.Service;

import adeuxpas.back.entity.PreferredSchedule;

@Service
public class PreferredScheduleMapperService {

    public PreferredScheduleDto mapToDto(PreferredSchedule preferredSchedule) {
        return new PreferredScheduleDto(
            preferredSchedule.getId(),
            preferredSchedule.getWeekDay(),
            preferredSchedule.getStartTime(),
            preferredSchedule.getEndTime(),
            preferredSchedule.getUser().getId()
        );
    }
    
}
