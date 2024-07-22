package adeuxpas.back.dto;

import java.util.List;

public class SellerCheckoutDTO {
    private Long id;
    private String bankAccountHolder;
    private String bankAccountNumber;
    private List<PreferredMeetingPlaceDTO> preferredMeetingPlaces;
    private List<PreferredScheduleDTO> preferredSchedules;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccountHolder() {
        return bankAccountHolder;
    }

    public void setBankAccountHolder(String bankAccountHolder) {
        this.bankAccountHolder = bankAccountHolder;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
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
