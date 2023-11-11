import enums.YatzyCategoryEnum;

import static enums.YatzyCategoryEnum.*;

/**
 * The Yatzy record represents a Yatzy game with methods to calculate scores for different categories.
 */
public record Yatzy(int[] dice) {

    public Yatzy(int[] dice) {
        this.dice = dice;
        validateDiceLength();
    }

    /**
     * Calculate the score for the "chance" category, which is the sum of all dice.
     *
     * @return The score for the "chance" category.
     */
    public int chance() {
        int socre = 0;
        for (int die : dice) {
            socre += die;
        }
        return socre;
    }

    /**
     * Calculate the score for the "yatzy" category, which is 50 if all dice are the same, otherwise 0.
     *
     * @return The score for the "yatzy" category.
     */
    public int yatzy() {
        int firstDie = dice[0];
        for (int i = 1; i < 5; i++) {
            if (firstDie != dice[i]) {
                return 0;
            }
        }
        return 50;
    }

    /**
     * Calculate the score for the "ones" category, which the sum of the dice that reads one.
     *
     * @return The score for the "ones" category.
     */
    public int ones() {
        return calculateScoreForSimpleCategory(ONES);
    }

    /**
     * Calculate the score for the "twos" category, which the sum of the dice that reads two.
     *
     * @return The score for the "twos" category.
     */
    public int twos() {
        return calculateScoreForSimpleCategory(TWOS);
    }

    /**
     * Calculate the score for the "threes" category, which the sum of the dice that reads three.
     *
     * @return The score for the "threes" category.
     */
    public int threes() {
        return calculateScoreForSimpleCategory(THREES);
    }

    /**
     * Calculate the score for the "fours" category, which the sum of the dice that reads four.
     *
     * @return The score for the "fours" category.
     */
    public int fours() {
        return calculateScoreForSimpleCategory(FOURS);
    }

    /**
     * Calculate the score for the "fives" category, which the sum of the dice that reads five.
     *
     * @return The score for the "fives" category.
     */
    public int fives() {
        return calculateScoreForSimpleCategory(FIVES);
    }

    /**
     * Calculate the score for the "sixes" category, which the sum of the dice that reads six.
     *
     * @return The score for the "sixes" category.
     */
    public int sixes() {
        return calculateScoreForSimpleCategory(SIXES);
    }

    /**
     * Calculate the score for the "pair" category, which is the sum of the highest pair.
     *
     * @return The score for the "pair" category.
     */
    public int pair() {
        int[] diceCounts = calculateDiceOccurrences();
        return calculateScoreForPair(diceCounts);
    }

    /**
     * Calculate the score for the "two pair" category, which is the sum of two pairs.
     *
     * @return The score for the "two pair" category.
     */
    public int twoPair() {
        int[] diceCounts = calculateDiceOccurrences();
        return calculateScoreForTwoPair(diceCounts);
    }

    /**
     * Calculate the score for the "three of a kind" category, which is the sum of three dice with the same value.
     *
     * @return The score for the "three of a kind" category.
     */
    public int threeOfAKind() {
        int[] diceCounts = calculateDiceOccurrences();
        return calculateScoreForAKind(diceCounts, THREE_OF_KIND);
    }

    /**
     * Calculate the score for the "four of a kind" category, which is the sum of four dice with the same value.
     *
     * @return The score for the "four of a kind" category.
     */
    public int fourOfAKind() {
        int[] diceCounts = calculateDiceOccurrences();
        return calculateScoreForAKind(diceCounts, FOUR_OF_KIND);
    }

    /**
     * Calculate the score for the "small straight" category, which is 15 if the dice form a small straight, otherwise 0.
     *
     * @return The score for the "small straight" category.
     */
    public int smallStraight() {
        int[] diceCounts = calculateDiceOccurrences();
        return calculateScoreForSmallStraight(diceCounts);
    }

    /**
     * Calculate the score for the "large straight" category, which is 20 if the dice form a large straight, otherwise 0.
     *
     * @return The score for the "large straight" category.
     */
    public int largeStraight() {
        int[] diceCounts = calculateDiceOccurrences();
        return calculateScoreForLargeStraight(diceCounts);
    }

    /**
     * Calculate the score for the "full house" category, which is the sum of a pair and three of a kind.
     *
     * @return The score for the "full house" category.
     */
    public int fullHouse() {
        int[] diceCounts = calculateDiceOccurrences();
        int pairScore = calculateScoreForPair(diceCounts);
        int threeOfAKindScore = calculateScoreForAKind(diceCounts, THREE_OF_KIND);
        return calculateScoreForFullHouse(pairScore, threeOfAKindScore);
    }

    /**
     * Private method to validate the length of the dice array.
     */
    private void validateDiceLength() {
        if (dice.length != 5) {
            throw new IllegalArgumentException("Invalid dice array length. Expected: 5, Actual: " + dice.length);
        }
    }

    /**
     * Private method to calculate the occurrences of each dice value.
     *
     * @return An array representing the occurrences of each dice value.
     */
    private int[] calculateDiceOccurrences() {
        int[] diceCounts = new int[6];

        for (int i = 0; i < 5; i++) {
            diceCounts[dice[i] - 1]++;
        }

        return diceCounts;
    }

    /**
     * Private method to calculate the score for "ones", "twos", "threes", "fours", "fives" or "sixes" category.
     *
     * @param yatzyCategoryEnum The category for which to calculate the score.
     * @return The score for the specified category.
     */
    private int calculateScoreForSimpleCategory(YatzyCategoryEnum yatzyCategoryEnum) {
        int sum = 0;
        int targetValue = yatzyCategoryEnum.getTargetValue();
        for (int die : dice) {
            if (die == targetValue) {
                sum += targetValue;
            }
        }
        return sum;
    }

    /**
     * Private method to calculate the score for the "pair" category.
     *
     * @param diceCounts An array representing the occurrences of each dice value.
     * @return The score for the "pair" category.
     */
    private int calculateScoreForPair(int[] diceCounts) {
        for (int i = 0; i < 6; i++) {
            if (diceCounts[6 - i - 1] >= 2) {
                return (6 - i) * 2;
            }
        }
        return 0;
    }

    /**
     * Private method to calculate the score for the "two pair" category.
     *
     * @param diceCounts An array representing the occurrences of each dice value.
     * @return The score for the "two pair" category.
     */
    private int calculateScoreForTwoPair(int[] diceCounts) {
        int score = 0;
        int numberOfPairs = 0;

        for (int i = 0; i < 6; i++) {
            if (diceCounts[6 - i - 1] >= 2) {
                numberOfPairs++;
                score += (6 - i);
            }
        }

        if (numberOfPairs == 2) {
            return score * 2;
        }

        return score;
    }

    /**
     * Private method to calculate the score for the "three of a kind" or "four of a kind" category.
     *
     * @param diceCounts        An array representing the occurrences of each dice value.
     * @param yatzyCategoryEnum The category for which to calculate the score.
     * @return The score for the specified category.
     */
    private int calculateScoreForAKind(int[] diceCounts, YatzyCategoryEnum yatzyCategoryEnum) {
        int targetValue = yatzyCategoryEnum.getTargetValue();
        for (int i = 0; i < 6; i++) {
            if (diceCounts[i] >= targetValue) {
                return (i + 1) * targetValue;
            }
        }
        return 0;
    }

    /**
     * Private method to calculate the score for the "small straight" category.
     *
     * @param diceCounts An array representing the occurrences of each dice value.
     * @return The score for the "small straight" category.
     */
    private int calculateScoreForSmallStraight(int[] diceCounts) {
        for (int i = 0; i < 5; i++) {
            if (diceCounts[i] != 1) {
                return 0;
            }
        }
        return 15;
    }

    /**
     * Private method to calculate the score for the "large straight" category.
     *
     * @param diceCounts An array representing the occurrences of each dice value.
     * @return The score for the "large straight" category.
     */

    private int calculateScoreForLargeStraight(int[] diceCounts) {
        for (int i = 1; i < 6; i++) {
            if (diceCounts[i] != 1) {
                return 0;
            }
        }
        return 20;
    }

    /**
     * Private method to calculate the score for the "full house" category.
     *
     * @param pairScore         The score for the "pair" category.
     * @param threeOfAKindScore The score for the "three of a kind" category.
     * @return The score for the "full house" category.
     */
    private int calculateScoreForFullHouse(int pairScore, int threeOfAKindScore) {
        if (pairScore != 0 && threeOfAKindScore != 0) {
            return pairScore + threeOfAKindScore;
        }
        return 0;
    }
}



