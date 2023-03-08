package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import visible.SpriteCollection;

import java.awt.Color;

/**
 * Defines how the count-down animation works.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class CountdownAnimation implements Animation {
    private int countFrom;
    private final int tempCountFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final long sleepingTime;

    /**
     * CountdownAnimation's constructor.
     *
     * @param numOfSeconds the number of seconds before the game start.
     * @param countFrom    the number the count starts from.
     * @param gameScreen   the game environment.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.tempCountFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleepingTime = (long) (numOfSeconds / this.countFrom * 1000);
    }

    /**
     * Setter for countFrom.
     *
     * @param countFrom the number that the animation should show.
     */
    private void setCountFrom(int countFrom) {
        this.countFrom = countFrom;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw-surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(d.getWidth() / 2 - 10, d.getHeight() / 2, String.valueOf(this.countFrom), 70);
        Sleeper sleeper = new Sleeper();
        if (this.countFrom != this.tempCountFrom) {
            sleeper.sleepFor(this.sleepingTime);
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.setCountFrom(this.countFrom - 1);
    }

    /**
     * Checks if the animation should stop.
     *
     * @return if the animation should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
