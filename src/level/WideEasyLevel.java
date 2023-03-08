package level;

import collision.Velocity;
import shape.Point;
import visible.Block;
import visible.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the "wide easy" level.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public class WideEasyLevel implements LevelInformation {
    private static final int HEIGHT = 250;

    /**
     * The number of balls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return - the velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        for (int i = 0; i <= this.numberOfBalls(); ++i) {
            if (i == 5) {
                ++i;
            }
            ballVelocities.add(Velocity.fromAngleAndSpeed(-50 + i * 10, 4));
        }
        return ballVelocities;
    }

    /**
     * The paddle speed.
     *
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return 3;
    }

    /**
     * The paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return 550;
    }

    /**
     * The name of the level, the name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new SunBackground(HEIGHT, this);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 15; ++i) {
            if (i < 2) {
                blocks.add(new Block(new Point(25 + i * 50, HEIGHT), 50, 25, Color.red));
            } else if (i < 4) {
                blocks.add(new Block(new Point(25 + i * 50, HEIGHT), 50, 25, Color.orange));
            } else if (i < 6) {
                blocks.add(new Block(new Point(25 + i * 50, HEIGHT), 50, 25, Color.yellow));
            } else if (i < 9) {
                blocks.add(new Block(new Point(25 + i * 50, HEIGHT), 50, 25, Color.green));
            } else if (i < 11) {
                blocks.add(new Block(new Point(25 + i * 50, HEIGHT), 50, 25, Color.blue));
            } else if (i < 13) {
                blocks.add(new Block(new Point(25 + i * 50, HEIGHT), 50, 25, Color.pink));
            } else {
                blocks.add(new Block(new Point(25 + i * 50, HEIGHT), 50, 25, Color.cyan));
            }
        }
        return blocks;
    }

    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
