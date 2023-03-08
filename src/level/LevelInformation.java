package level;

import collision.Velocity;
import visible.Block;
import visible.Sprite;

import java.util.List;

/**
 * defines the information that level needs.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public interface LevelInformation {

    /**
     * The number of balls.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return - the velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * The paddle width.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * The name of the level, the name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
