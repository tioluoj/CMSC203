
import java.util.Arrays;


public class GradeBook {

    private double[] scores;
    private int scoresSize;

    
    public GradeBook(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0");
        }
        this.scores = new double[capacity];
        this.scoresSize = 0;
    }

    /**
     * Adds a score if there is capacity.
     * @return true if added, false if full or score invalid
     */
    public boolean addScore(double score) {
        if (scoresSize < scores.length && !Double.isNaN(score) && !Double.isInfinite(score)) {
            scores[scoresSize++] = score;
            return true;
        }
        return false;
    }

    /**
     * @return number of scores currently stored
     */
    public int getScoreSize() {
        return scoresSize;
    }

    /**
     * @return sum of all stored scores (0 if none)
     */
    public double sum() {
        double total = 0.0;
        for (int i = 0; i < scoresSize; i++) {
            total += scores[i];
        }
        return total;
    }

    /**
     * @return smallest score (Double.POSITIVE_INFINITY if none)
     */
    public double minimum() {
        if (scoresSize == 0) return Double.POSITIVE_INFINITY;
        double min = scores[0];
        for (int i = 1; i < scoresSize; i++) {
            if (scores[i] < min) min = scores[i];
        }
        return min;
    }

    /**
     * "Final score" = sum of scores after dropping the lowest score.
     * If 0 scores → 0; if 1 score → that score.
     */
    public double finalScore() {
        if (scoresSize == 0) return 0.0;
        if (scoresSize == 1) return scores[0];
        return sum() - minimum();
    }

    /**
     * @return space-separated list of scores in insertion order (no trailing space)
     *         e.g., "50.0 75.0"
     */
    @Override
    public String toString() {
        if (scoresSize == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scoresSize; i++) {
            if (i > 0) sb.append(' ');
            
            sb.append(scores[i]);
        }
        return sb.toString();
    }

    // --- Optional helpers (not required but handy for testing/debugging) ---

    /** Current capacity (max number of scores). */
    public int capacity() {
        return scores.length;
    }

    /** Defensive copy of current scores (trimmed to size). */
    public double[] toArray() {
        return Arrays.copyOf(scores, scoresSize);
    }
}

