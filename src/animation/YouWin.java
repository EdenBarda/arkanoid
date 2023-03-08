package animation;

import biuoop.DrawSurface;
import collision.Counter;

/**
 * The YouWin of the game.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 28.05.2022
 */
public class YouWin implements Animation {
    private final Counter counter;

    /**
     * The constructor of GameOver.
     *
     * @param counter the score counter.
     */
    public YouWin(Counter counter) {
        this.counter = counter;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw-surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(270, d.getHeight() / 2 - 30, "You Win!", 60);
        d.drawText(260, d.getHeight() / 2 + 30, "Your score is " + this.counter.getValue(), 35);
    }

    /**
     * Checks if the animation should stop.
     *
     * @return if the animation should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}
