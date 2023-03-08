package level;

import biuoop.DrawSurface;
import visible.Sprite;

import java.awt.Color;

/**
 * Defines the TargetBackground.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public class TargetBackground implements Sprite {
    private final LevelInformation level;

    /**
     * The constructor of TargetBackground.
     *
     * @param level the level.
     */
    public TargetBackground(LevelInformation level) {
        this.level = level;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d a surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 25, 800, 575);
        d.setColor(Color.black);
        d.drawText(600, 20, "Level Name: " + this.level.levelName(), 15);
        d.setColor(Color.blue);
        d.drawLine(400, 350, 400, 450);
        d.drawLine(400, 290, 400, 190);
        d.drawLine(430, 320, 530, 320);
        d.drawLine(370, 320, 270, 320);
        d.drawCircle(400, 320, 40);
        d.drawCircle(400, 320, 70);
        d.drawCircle(400, 320, 110);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
