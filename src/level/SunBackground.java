package level;

import biuoop.DrawSurface;
import visible.Sprite;

import java.awt.Color;
import java.awt.Point;

/**
 * Defines the SunBackground.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 26.05.2022
 */
public class SunBackground implements Sprite {
    private final int height;
    private final LevelInformation level;
    private int signX;
    private int signY;
    private Point center;

    /**
     * Constructor for SunBackground.
     *
     * @param height suns height.
     * @param level  the level.
     */
    public SunBackground(int height, LevelInformation level) {
        this.level = level;
        this.height = height;
        this.center = new Point(150, 180);
        this.signX = 1;
        this.signY = 1;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d a surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 25, 800, 575);
        d.setColor(Color.black);
        d.drawText(600, 20, "Level Name: " + this.level.levelName(), 15);
        d.setColor(new Color(234, 226, 80));
        for (int i = 0; i < 40; ++i) {
            d.drawLine((int) this.center.getX(), (int) this.center.getY(), i * 20, this.height);
        }
        d.setColor(Color.orange);
        d.setColor(new Color(224, 202, 158));
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), 60);
        d.setColor(new Color(238, 208, 66));
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), 50);
        d.setColor(new Color(234, 226, 80));
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), 40);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        if (this.center.getX() == 650) {
            this.signX = -1;
        }
        if (this.center.getX() == 149) {
            this.signX = 1;
        }
        if (this.center.getX() > 400 && this.signX == 1) {
            this.signY = 1;
        } else if (this.center.getX() < 400 && this.signX == -1) {
            this.signY = 1;
        } else {
            this.signY = -1;
        }
        if ((int) this.center.getX() % 5 == 0) {
            this.center = new Point((int) this.center.getX(), (int) this.center.getY() + this.signY);
        }
        this.center = new Point((int) this.center.getX() + this.signX, (int) this.center.getY());
    }
}
