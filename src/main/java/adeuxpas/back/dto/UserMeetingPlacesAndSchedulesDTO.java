package adeuxpas.back.dto;

import java.util.List;

public class UserMeetingPlacesAndSchedulesDTO {
    private List<PreferredMeetingPlaceDTO> preferredMeetingPlaces;
    private List<PreferredScheduleDTO> preferredSchedules;

    public List<PreferredMeetingPlaceDTO> getPreferredMeetingPlaces() {
        return preferredMeetingPlaces;
    }

    public void setPreferredMeetingPlaces(List<PreferredMeetingPlaceDTO> preferredMeetingPlaces) {
        this.preferredMeetingPlaces = preferredMeetingPlaces;
    }

    public List<PreferredScheduleDTO> getPreferredSchedules() {
        return preferredSchedules;
    }

    public void setPreferredSchedules(List<PreferredScheduleDTO> preferredSchedules) {
        this.preferredSchedules = preferredSchedules;
    }
}
