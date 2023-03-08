import animation.AnimationRunner;
import biuoop.GUI;
import level.DirectHitLevel;
import level.FinalFourLevel;
import level.GameFlow;
import level.GreenLevel;
import level.LevelInformation;
import level.WideEasyLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * the class starts the game.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Arkanoid {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * The game so far.
     *
     * @param args the levels that the user gives to the program.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        int levelNumber = 0;
        for (String i : args) {
            switch (i) {
                case "1":
                    levels.add(new DirectHitLevel());
                    ++levelNumber;
                    break;

                case "2":
                    levels.add(new WideEasyLevel());
                    ++levelNumber;
                    break;

                case "3":
                    levels.add(new GreenLevel());
                    ++levelNumber;
                    break;

                case "4":
                    levels.add(new FinalFourLevel());
                    ++levelNumber;
                    break;

                default:

            }
        }
        if (levelNumber == 0) {
            levels.add(new DirectHitLevel());
            levels.add(new WideEasyLevel());
            levels.add(new GreenLevel());
            levels.add(new FinalFourLevel());
        }
        GUI gui = new GUI("Arkanoid - by Eden Barda", WIDTH, HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(levels);
    }
}
