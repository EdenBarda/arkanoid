package animation;

import biuoop.DrawSurface;

/**
 * Define what is an animation.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public interface Animation {

    /**
     * Do one frame of the animation.
     *
     * @param d the draw-surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return if the animation should stop.
     */
    boolean shouldStop();
}