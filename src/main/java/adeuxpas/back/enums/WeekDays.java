package adeuxpas.back.enums;

public enum WeekDays {
    DIMANCHE(0),
    LUNDI(1),
    MARDI(2),
    MERCREDI(3),
    JEUDI(4),
    VENDREDI(5),
    SAMEDI(6);

    private final Integer dayOfWeek;

    WeekDays(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }
}