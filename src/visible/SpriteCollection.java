package visible;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * Collection of all the sprites.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class SpriteCollection {
    private final ArrayList<Sprite> spriteCollection;

    /**
     * Constructor for SpriteCollection.
     */
    public SpriteCollection() {
        spriteCollection = new ArrayList<>();
    }

    /**
     * Add the given sprite to the collection.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /**
     * Remove a sprite from the collection.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        int n = this.spriteCollection.size();
        for (int i = 0; i < n; i += 1) {
            int m = this.spriteCollection.size();
            if (n == m) {
                spriteCollection.get(i).timePassed();
            } else {
                spriteCollection.get(i - (n - m)).timePassed();
            }
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d a DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite i : spriteCollection) {
            i.drawOn(d);
        }
    }
}