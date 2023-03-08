package level;

import biuoop.DrawSurface;
import visible.Sprite;

import java.awt.Color;

/**
 * Defines the RectanglesBackground.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public class RectanglesBackground implements Sprite {
    private final LevelInformation level;
    private int firstLayer;
    private int secondLayer;

    /**
     * Constructor for RectanglesBackground.
     *
     * @param level the level.
     */
    public RectanglesBackground(LevelInformation level) {
        this.level = level;
        this.firstLayer = 25;
        this.secondLayer = -132;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d a surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 146, 0));
        d.fillRectangle(0, 25, 800, 575);
        d.setColor(Color.black);
        d.drawText(600, 20, "Level Name: " + this.level.levelName(), 15);
        d.setColor(new Color(36, 157, 36));
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 6; ++j) {
                d.fillRectangle(this.firstLayer + i * 105, 25 + j * 105, 70, 70);
            }
        }
        d.setColor(new Color(34, 140, 34));
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 6; ++j) {
                d.fillRectangle(this.secondLayer + i * 105, 78 + j * 105, 70, 70);
            }
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        if (this.firstLayer > -185) {
            this.firstLayer -= 1;
        } else {
            this.firstLayer = 25;
        }
        if (this.secondLayer < -27) {
            this.secondLayer += 1;
        } else {
            this.secondLayer = -132;
        }
    }
}
