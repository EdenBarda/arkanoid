package level;

import animation.AnimationRunner;
import animation.GameOver;
import animation.KeyPressStoppableAnimation;
import animation.YouWin;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Counter;

import java.util.List;

/**
 * Defines the game flow.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 * @since 28.05.2022
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;

    /**
     * The constructor of GameFlow.
     *
     * @param ar  animationRunner.
     * @param ks  keyboardSensor.
     * @param gui the game's gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.gui = gui;
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Runs all the levels in the game.
     *
     * @param levels list of all the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.gui, score);
            level.initialize();
            while (level.getBallsCounter().getValue() != 0 && level.getBlocksCounter().getValue() != 0) {
                level.run();
            }
            if (level.getBallsCounter().getValue() == 0) {
                this.userLose(score);
            }
            score.increase(100);
        }
        AnimationRunner runner = new AnimationRunner(this.gui);
        runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", new YouWin(score)));
        this.gui.close();
    }

    /**
     * If the user lose.
     *
     * @param score the user's score.
     */
    private void userLose(Counter score) {
        AnimationRunner runner = new AnimationRunner(this.gui);
        runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", new GameOver(score)));
        this.gui.close();
    }
}