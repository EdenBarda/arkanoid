package collision;

import level.GameLevel;
import visible.Ball;
import visible.Block;

import java.awt.Color;

/**
 * A AddBall is in charge of adding balls to the game,
 * as well as keeping count of the number of balls that remain.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class AddBall implements HitListener {
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Constructor for ball remover.
     *
     * @param game           the GameLevel.
     * @param remainingBalls the remaining balls.
     */
    public AddBall(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Block that are hit create another ball in the game.
     *
     * @param beingHit the object that being hit.
     * @param hitter   the hitter object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        Ball newBall = new Ball(hitter.getCenter(), hitter.getSize(), Color.white);
        newBall.setVelocity(-hitter.getVelocity().getDx(), -hitter.getVelocity().getDy());
        newBall.addToGame(this.game);
        newBall.setGameEnvironment(this.game.getEnvironment());
        this.remainingBalls.increase(1);
    }
}