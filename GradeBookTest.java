import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GradeBookTest {

    private GradeBook g1;
    private GradeBook g2;

    @Before
    public void setUp() {
        // Two gradebooks, small capacity to force boundary testing
        g1 = new GradeBook(5);
        g2 = new GradeBook(2);

        // Seed g1 with two typical valid scores
        assertTrue(g1.addScore(50.0));
        assertTrue(g1.addScore(75.0));

        // Seed g2 to capacity
        assertTrue(g2.addScore(90.0));
        assertTrue(g2.addScore(80.0));
    }

    // ---------------------------
    // Capacity & addScore tests
    // ---------------------------
    @Test
    public void testAddScoreWithinCapacity() {
        GradeBook gb = new GradeBook(3);
        assertTrue(gb.addScore(10.0));
        assertTrue(gb.addScore(20.5));
        assertTrue(gb.addScore(30.0));
        assertEquals(3, gb.getScoreSize());
        assertEquals("10.0 20.5 30.0", gb.toString());
    }

    @Test
    public void testAddScoreOverCapacity() {
        // g2 is already full (capacity 2)
        assertFalse(g2.addScore(70.0)); // should fail
        assertEquals(2, g2.getScoreSize());
        assertEquals("90.0 80.0", g2.toString());
    }

    @Test
    public void testAddInvalidScoreNaN() {
        GradeBook gb = new GradeBook(2);
        assertFalse(gb.addScore(Double.NaN));
        assertEquals(0, gb.getScoreSize());
    }

    @Test
    public void testAddInvalidScoreInfinite() {
        GradeBook gb = new GradeBook(2);
        assertFalse(gb.addScore(Double.POSITIVE_INFINITY));
        assertEquals(0, gb.getScoreSize());
    }

    // ---------------------------
    // Sum / Minimum / FinalScore
    // ---------------------------
    @Test
    public void testSum() {
        assertEquals(125.0, g1.sum(), 0.0001);    // 50 + 75
        assertEquals(170.0, g2.sum(), 0.0001);    // 90 + 80
    }

    @Test
    public void testMinimum() {
        assertEquals(50.0, g1.minimum(), 0.0001);
        assertEquals(80.0, g2.minimum(), 0.0001);
    }

    @Test
    public void testFinalScoreTwoOrMore() {
        // g1: 50 + 75 = 125; drop min (50) => 75
        assertEquals(75.0, g1.finalScore(), 0.0001);

        // g2: 90 + 80 = 170; drop min (80) => 90
        assertEquals(90.0, g2.finalScore(), 0.0001);
    }

    @Test
    public void testFinalScoreOneScore() {
        GradeBook gb = new GradeBook(3);
        assertTrue(gb.addScore(88.0));
        assertEquals(88.0, gb.finalScore(), 0.0001);
    }

    @Test
    public void testFinalScoreNoScores() {
        GradeBook gb = new GradeBook(3);
        assertEquals(0.0, gb.finalScore(), 0.0001);
        assertTrue(gb.toString().isEmpty());
    }

    // ---------------------------
    // toString format
    // ---------------------------
    @Test
    public void testToStringFormat() {
        assertEquals("50.0 75.0", g1.toString());
        assertEquals("90.0 80.0", g2.toString());
    }

    // ---------------------------
    // getScoreSize
    // ---------------------------
    @Test
    public void testGetScoreSize() {
        assertEquals(2, g1.getScoreSize());
        assertEquals(2, g2.getScoreSize());
    }

    // ---------------------------
    // Extra: edge/boundary behaviors
    // ---------------------------
    @Test
    public void testLargeValuesAndPrecision() {
        GradeBook gb = new GradeBook(3);
        assertTrue(gb.addScore(99.9999));
        assertTrue(gb.addScore(0.00005));
        assertTrue(gb.addScore(100.0));
        assertEquals(3, gb.getScoreSize());
        assertEquals(200.0, gb.finalScore(), 0.0002); // drop ~0.00005
    }
}

