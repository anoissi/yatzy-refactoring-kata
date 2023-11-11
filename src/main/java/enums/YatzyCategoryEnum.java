package enums;

public enum YatzyCategoryEnum {
    ONES(1),

    TWOS(2),

    THREES(3),

    FOURS(4),

    FIVES(5),

    SIXES(6),

    THREE_OF_KIND(3),

    FOUR_OF_KIND(4);


    private final int targetValue;

    YatzyCategoryEnum(int targetValue) {
        this.targetValue = targetValue;
    }

    public int getTargetValue() {
        return targetValue;
    }
}
