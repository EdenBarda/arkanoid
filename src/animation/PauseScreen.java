package animation;

import biuoop.DrawSurface;

/**
 * The PauseScreen of the game.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class PauseScreen implements Animation {

    /**
     * Do one frame of the animation.
     *
     * @param d the draw-surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(285, d.getHeight() / 2 - 30, "Paused", 60);
        d.drawText(210, d.getHeight() / 2 + 30, "Press space to continue", 35);
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