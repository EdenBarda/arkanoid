package visible;

import biuoop.DrawSurface;
import collision.Counter;

import java.awt.Color;

/**
 * The score indicator of the game.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private static final int WIDTH = 383;
    private static final int HEIGHT = 20;
    private static final int TEXT = 15;

    /**
     * The constructor of ScoreIndicator.
     * Gets the score counter and prints the score on the screen.
     *
     * @param score the score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d a surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(WIDTH, HEIGHT,
                "Score: " + this.score.getIntegerValue().toString(), TEXT);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
