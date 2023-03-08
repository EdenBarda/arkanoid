package visible;

import biuoop.DrawSurface;

/**
 * Sprites can be drawn on the screen, and can be notified that time has passed.
 * (so that they know to change their position / shape / appearance).
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     *
     * @param d a surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}