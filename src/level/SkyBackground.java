package level;

import biuoop.DrawSurface;
import visible.Sprite;

import java.awt.Color;

/**
 * Defines the SkyBackground.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public class SkyBackground implements Sprite {
    private final LevelInformation level;

    /**
     * The constructor of SkyBackground.
     *
     * @param level the level.
     */
    public SkyBackground(LevelInformation level) {
        this.level = level;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d a surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(65, 163, 217));
        d.fillRectangle(0, 25, 800, 575);
        d.setColor(Color.black);
        d.drawText(600, 20, "Level Name: " + this.level.levelName(), 15);

        //first cloud.
        d.setColor(Color.white);
        for (int i = 0; i < 10; ++i) {
            d.drawLine(70 + i * 10, 410, 60 + i * 10, 600);
        }
        d.setColor(new Color(116, 137, 154));
        d.fillCircle(120, 410, 35);
        d.setColor(new Color(116, 140, 154));
        d.fillCircle(150, 400, 30);
        d.setColor(new Color(116, 147, 154));
        d.fillCircle(75, 405, 30);
        d.setColor(new Color(116, 152, 154));
        d.fillCircle(100, 400, 35);
        d.setColor(new Color(116, 142, 154));
        d.fillCircle(135, 380, 25);

        //second cloud.
        d.setColor(Color.white);
        for (int i = 0; i < 10; ++i) {
            d.drawLine(485 + i * 10, 450, 475 + i * 10, 600);
        }
        d.setColor(new Color(116, 137, 154));
        d.fillCircle(520, 440, 35);
        d.setColor(new Color(116, 140, 154));
        d.fillCircle(550, 430, 30);
        d.setColor(new Color(116, 147, 154));
        d.fillCircle(575, 435, 30);
        d.setColor(new Color(116, 152, 154));
        d.fillCircle(500, 430, 35);
        d.setColor(new Color(116, 142, 154));
        d.fillCircle(535, 410, 25);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
