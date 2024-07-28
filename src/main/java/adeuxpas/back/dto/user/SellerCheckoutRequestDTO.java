package adeuxpas.back.dto.user;

import java.util.List;

public class SellerCheckoutRequestDTO {
    private Long id;
    private String bankAccountTokenId;
    private List<PreferredMeetingPlaceDTO> preferredMeetingPlaces;
    private List<PreferredScheduleDTO> preferredSchedules;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccountTokenId() {
        return bankAccountTokenId;
    }

    public void setBankAccountTokenId(String bankAccountTokenId) {
        this.bankAccountTokenId = bankAccountTokenId;
    }

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
