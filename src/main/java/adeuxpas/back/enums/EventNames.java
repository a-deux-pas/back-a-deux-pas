package adeuxpas.back.enums;

/**
 * Enumeration representing the notification events.
 * <p>
 * Each constant represents a specific event.
 * </p>
 * 
 * @author Léa Hadida
 */
public enum EventNames {
    MEETING_PROPOSAL("meetingProposal"),
    MEETING_UPDATE("meetingUpdate"),
    MEETING_REMINDER("meetingReminder"),
    MEETING_TO_FINALIZE("meetingToFinalize"),
    MEETING_CANCELLED("meetingCancelled");

    private final String value;

    /**
     * Constructor for EventNames enum.
     *
     * @param eventName The value representing the event name.
     */
    EventNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Get the EventNames enum corresponding to the given string value.
     *
     * @param value The string value representing the event names.
     * @return The corresponding EventNames enum, or null if no matching enum is
     *         found.
     */
    public static EventNames getEventNamefromString(String value) {
        for (EventNames eventName : EventNames.values()) {
            if (eventName.getValue().equals(value)) {
                return eventName;
            }
        }
        return null;
    }
}
