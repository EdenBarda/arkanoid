package level;

import collision.Velocity;
import shape.Point;
import visible.Block;
import visible.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the "direct hit" level.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public class DirectHitLevel implements LevelInformation {

    /**
     * The number of balls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        ballVelocities.add(Velocity.fromAngleAndSpeed(0.001, 4));
        return ballVelocities;
    }

    /**
     * The paddle speed.
     *
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return 7;
    }

    /**
     * The paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return 60;
    }

    /**
     * The name of the level, the name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new TargetBackground(this);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Point(390, 310), 20, 20, Color.red));
        return blocks;
    }

    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
