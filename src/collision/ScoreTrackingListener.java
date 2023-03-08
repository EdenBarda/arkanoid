package collision;

import visible.Ball;
import visible.Block;

/**
 * The class that allows as to track the score of the game.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * The constructor of ScoreTrackingListener.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * When a block destroyed the user get 5 points.
     *
     * @param beingHit the object that being hit.
     * @param hitter   the hitter object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}