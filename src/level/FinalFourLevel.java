package level;

import collision.Velocity;
import shape.Point;
import visible.Block;
import visible.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the "green 3" level.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public class FinalFourLevel implements LevelInformation {
    private static final int HEIGHT = 100;

    /**
     * The number of balls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        ballVelocities.add(Velocity.fromAngleAndSpeed(-55, 4));
        ballVelocities.add(Velocity.fromAngleAndSpeed(1, 4));
        ballVelocities.add(Velocity.fromAngleAndSpeed(55, 4));
        return ballVelocities;
    }

    /**
     * The paddle speed.
     *
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return 6;
    }

    /**
     * The paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return 70;
    }

    /**
     * The name of the level, the name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new level.SkyBackground(this);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 15; ++j) {
                if (i == 0) {
                    blocks.add(new Block(new Point(800 - 75 - j * 50, HEIGHT), 50, 25, Color.gray));
                } else if (i == 1) {
                    blocks.add(new Block(new Point(800 - 75 - j * 50, HEIGHT + i * 25), 50, 25, Color.red));
                } else if (i == 2) {
                    blocks.add(new Block(new Point(800 - 75 - j * 50, HEIGHT + i * 25), 50, 25, Color.yellow));
                } else if (i == 3) {
                    blocks.add(new Block(new Point(800 - 75 - j * 50, HEIGHT + i * 25), 50, 25, Color.green));
                } else if (i == 4) {
                    blocks.add(new Block(new Point(800 - 75 - j * 50, HEIGHT + i * 25), 50, 25, Color.white));
                } else if (i == 5) {
                    blocks.add(new Block(new Point(800 - 75 - j * 50, HEIGHT + i * 25), 50, 25, Color.pink));
                } else {
                    blocks.add(new Block(new Point(800 - 75 - j * 50, HEIGHT + i * 25), 50, 25, Color.cyan));
                }
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

