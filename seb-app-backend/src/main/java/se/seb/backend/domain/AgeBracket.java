package se.seb.backend.domain;

public enum AgeBracket {

    JUNIOR(0, 17),
    ADULT(18, 64),
    SENIOR(65, null);

    private Integer floor, ceiling;

    private AgeBracket(Integer floor, Integer ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public AgeBracket convertAgeToBracket(Integer age) {
        if (age >= 0 && age <= 17) {
            return AgeBracket.JUNIOR;
        } else if (age >= 18 && age <= 64) {
            return AgeBracket.ADULT;
        } else if (age >= 65) {
            return AgeBracket.SENIOR;
        }
        return null;
    }
}
