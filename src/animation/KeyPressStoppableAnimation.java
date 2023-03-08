package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Defines the KeyPressStoppableAnimation.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 29.05.2022
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The constructor of KeyPressStoppableAnimation.
     *
     * @param sensor    the KeyboardSensor.
     * @param key       the string.
     * @param animation an animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw-surface.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
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
