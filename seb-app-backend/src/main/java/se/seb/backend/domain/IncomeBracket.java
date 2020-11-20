package se.seb.backend.domain;

public enum IncomeBracket {

    NO_INCOME(0.00, 0.00),
    LOW_INCOME(1.00, 12000.00),
    MEDIUM_INCOME(12001.00, 40000.00),
    HIGH_INCOME(40001.00, null);

    private Double floor, ceiling;

    private IncomeBracket(Double floor, Double ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public IncomeBracket convertIncomeToBracket(Double income) {
        if (income == 0.00) {
            return IncomeBracket.NO_INCOME;
        } else if (income >=1 && income <= 12000.00) {
            return IncomeBracket.LOW_INCOME;
        } else if (income >= 12001.00 && income <= 40000.00) {
            return IncomeBracket.MEDIUM_INCOME;
        } else if (income >= 40001.00) {
            return IncomeBracket.HIGH_INCOME;
        }
        return null;
    }
}
