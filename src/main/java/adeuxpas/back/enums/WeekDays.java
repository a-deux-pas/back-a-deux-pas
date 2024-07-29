package adeuxpas.back.enums;

/**
 * Enumeration representing the days of the week.
 * <p>
 * This enum provides constants for the days of the week,
 * with associated integer values representing their order in the week.
 * </p>
 * <p>
 * Each constant represents a specific day of the week (e.g., SUNDAY, MONDAY).
 * </p>
 * 
 * @author Léa Hadida
 */
public enum WeekDays {
    DIMANCHE(0),
    LUNDI(1),
    MARDI(2),
    MERCREDI(3),
    JEUDI(4),
    VENDREDI(5),
    SAMEDI(6);

    private final Integer dayOfWeek;

    /**
     * Constructor for WeekDays enum with an associated integer value.
     *
     * @param dayOfWeek The integer value representing the day of the week.
     */
    WeekDays(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Get the integer value representing the day of the week.
     *
     * @return The integer value representing the day of the week.
     */
    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Get the WeekDays enum corresponding to the given integer value.
     *
     * @param dayOfWeek The integer value representing the day of the week.
     * @return The corresponding WeekDays enum, or null if no matching enum is
     *         found.
     */
    public static WeekDays getWeekDayfromInteger(Integer dayOfWeek) {
        for (WeekDays day : WeekDays.values()) {
            if (day.getDayOfWeek().equals(dayOfWeek)) {
                return day;
            }
        }
        return null;
    }
}
