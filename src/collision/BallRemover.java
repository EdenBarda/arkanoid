package collision;

import level.GameLevel;
import visible.Ball;
import visible.Block;

/**
 * A BallRemover is in charge of removing balls from the game,
 * as well as keeping count of the number of balls that remain.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Constructor for ball remover.
     *
     * @param game           the game.
     * @param remainingBalls the remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit remove the ball from the game.
     *
     * @param beingHit the object that being hit.
     * @param hitter   the hitter object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}