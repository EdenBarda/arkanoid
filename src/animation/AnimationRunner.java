package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Defines AnimationRunner.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * The constructor for AnimationRunner.
     *
     * @param gui a GUI.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Run the animation.
     *
     * @param animation an Animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
