import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class YatzyTest {

    @Test
    public void testConstructor() {
        int[] validDice = {1, 2, 3, 4, 5};
        assertArrayEquals(validDice, buildYatzy(validDice).dice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_whenShortArray_thenThrowException() {
      buildYatzy(1, 2, 3, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_whenLongArray_thenThrowException() {
        buildYatzy(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void testChance() {
        assertEquals(15, buildYatzy(2, 3, 4, 5, 1).chance());
        assertEquals(16,  buildYatzy(3, 3, 4, 5, 1).chance());
    }

    @Test
    public void testYatzy() {
        assertEquals(50,  buildYatzy(4, 4, 4, 4, 4).yatzy());
        assertEquals(50,  buildYatzy(6, 6, 6, 6, 6).yatzy());
        assertEquals(0,  buildYatzy(6, 6, 6, 6, 3).yatzy());
    }

    @Test
    public void testOnes() {
        assertEquals(1,  buildYatzy(1, 2, 3, 4, 5).ones());
        assertEquals(2,  buildYatzy(1, 2, 1, 4, 5).ones());
        assertEquals(0,  buildYatzy(6, 2, 2, 4, 5).ones());
        assertEquals(4,  buildYatzy(1, 2, 1, 1, 1).ones());
    }

    @Test
    public void testTwos() {
        assertEquals(4,  buildYatzy(1, 2, 3, 2, 6).twos());
        assertEquals(10,  buildYatzy(2, 2, 2, 2, 2).twos());
    }

    @Test
    public void testThrees() {
        assertEquals(6,  buildYatzy(1, 2, 3, 2, 3).threes());
        assertEquals(12,  buildYatzy(2, 3, 3, 3, 3).threes());
    }

    @Test
    public void testFours() {
        assertEquals(12,  buildYatzy(4, 4, 4, 5, 5).fours());
        assertEquals(8,  buildYatzy(4, 4, 5, 5, 5).fours());
        assertEquals(4,  buildYatzy(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void testFives() {
        assertEquals(10,  buildYatzy(4, 4, 4, 5, 5).fives());
        assertEquals(15,  buildYatzy(4, 4, 5, 5, 5).fives());
        assertEquals(20,  buildYatzy(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void testSixes() {
        assertEquals(0,  buildYatzy(4, 4, 4, 5, 5).sixes());
        assertEquals(6,  buildYatzy(4, 4, 6, 5, 5).sixes());
        assertEquals(18,  buildYatzy(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void testPair() {
        assertEquals(6,  buildYatzy(3, 4, 3, 5, 6).pair());
        assertEquals(10,  buildYatzy(5, 3, 3, 3, 5).pair());
        assertEquals(12,  buildYatzy(5, 3, 6, 6, 5).pair());
    }


    @Test
    public void testTowPair() {
        assertEquals(16,  buildYatzy(3, 3, 5, 4, 5).twoPair());
        assertEquals(16,  buildYatzy(3, 3, 5, 5, 5).twoPair());
    }

    @Test
    public void testThreeOfAKind() {
        assertEquals(9,  buildYatzy(3, 3, 3, 4, 5).threeOfAKind());
        assertEquals(15,  buildYatzy(5, 3, 5, 4, 5).threeOfAKind());
        assertEquals(9,  buildYatzy(3, 3, 3, 3, 5).threeOfAKind());
    }

    @Test
    public void testFourOfAKind() {
        assertEquals(12,  buildYatzy(3, 3, 3, 3, 5).fourOfAKind());
        assertEquals(20,  buildYatzy(5, 5, 5, 4, 5).fourOfAKind());
        assertEquals(12,  buildYatzy(3, 3, 3, 3, 3).fourOfAKind());
    }

    @Test
    public void testSmallStraight() {
        assertEquals(15,  buildYatzy(1, 2, 3, 4, 5).smallStraight());
        assertEquals(15,  buildYatzy(2, 3, 4, 5, 1).smallStraight());
        assertEquals(0,  buildYatzy(1, 2, 2, 4, 5).smallStraight());
    }

    @Test
    public void testLargeStraight() {
        assertEquals(20,  buildYatzy(6, 2, 3, 4, 5).largeStraight());
        assertEquals(20,  buildYatzy(2, 3, 4, 5, 6).largeStraight());
        assertEquals(0,  buildYatzy(1, 2, 2, 4, 5).largeStraight());
    }

    @Test
    public void testFullHouse() {
        assertEquals(18,  buildYatzy(6, 2, 2, 2, 6).fullHouse());
        assertEquals(0,  buildYatzy(2, 3, 4, 5, 6).fullHouse());
    }

    private Yatzy buildYatzy(int... dice) {
        return new Yatzy(dice);
    }
}
