package level;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.AddBall;
import collision.BallRemover;
import collision.BlockRemover;
import collision.ScoreTrackingListener;
import collision.Counter;
import shape.Rectangle;
import shape.Point;
import visible.Ball;
import visible.Block;
import visible.Collidable;
import visible.GameEnvironment;
import visible.Paddle;
import visible.ScoreIndicator;
import visible.Sprite;
import visible.SpriteCollection;

import java.awt.Color;

/**
 * The game class hold the sprites and the collidables, and charge of the animation.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class GameLevel implements Animation {
    private final LevelInformation levelInformation;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final Counter score;
    private final Block[] deathBlocks;
    private final Block[] liveBlock;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final GUI gui;
    private boolean running;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_LENGTH = 7;
    private static final int PADDLE_HEIGHT = 20;
    private static final int SUPER_BLOCKS_LENGTH = 6;
    private static final int SUPER_BLOCKS_WIDTH = 80;
    private static final int BALL_RADIUS = 4;

    /**
     * Constructor for Game.
     *
     * @param levelInformation - the level.
     * @param keyboard         - the keyboard of the game.
     * @param runner           - the animation runner of the game.
     * @param gui              - the gui of the game.
     * @param score            - the user's score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner,
                     GUI gui, Counter score) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter(0);
        this.ballsCounter = new Counter(0);
        this.score = score;
        this.deathBlocks = deathBlocksGenerator();
        this.liveBlock = liveBlocksGenerator();
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.sprites.addSprite(this.levelInformation.getBackground());
    }

    /**
     * Getter for balls counter.
     *
     * @return the balls counter.
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * Getter for blocks counter.
     *
     * @return the blocks counter.
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }

    /**
     * Get the game environment.
     *
     * @return the game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Generates the death blocks.
     *
     * @return array of the death blocks.
     */
    private Block[] deathBlocksGenerator() {
        Block[] deathBlocks = new Block[1];
        deathBlocks[0] = new Block(new Point(0, HEIGHT - 2), WIDTH, 2, Color.darkGray);
        return deathBlocks;
    }

    /**
     * Generates the life blocks.
     *
     * @return array of the life blocks.
     */
    private Block[] liveBlocksGenerator() {
        Block[] lifeBlocks = new Block[2];
        lifeBlocks[0] = new Block(new Point(0, 50), SUPER_BLOCKS_WIDTH, SUPER_BLOCKS_LENGTH, Color.white);
        lifeBlocks[1] = new Block(new Point(WIDTH - SUPER_BLOCKS_WIDTH, 50),
                SUPER_BLOCKS_WIDTH, SUPER_BLOCKS_LENGTH, Color.white);
        return lifeBlocks;
    }

    /**
     * Add collidable to the GameEnvironment.
     *
     * @param c a collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove a collidable from the game environment.
     *
     * @param c a collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getGameEnvironment().remove(c);
    }

    /**
     * Add spite to the SpriteCollection.
     *
     * @param s a spite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove a sprite from the sprite collection.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Generate the balls in the game and add them to this environment.
     */
    private void generateBalls() {
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        AddBall addBall = new AddBall(this, this.ballsCounter);
        for (Block i : this.deathBlocks) {
            i.addHitListener(ballRemover);
        }
        for (Block i : this.liveBlock) {
            i.addHitListener(addBall);
        }
        Ball[] balls = new Ball[this.levelInformation.numberOfBalls()];
        for (int i = 0; i < balls.length; ++i) {
            balls[i] = new Ball(400, 500, BALL_RADIUS, Color.WHITE);
            balls[i].setVelocity(this.levelInformation.initialBallVelocities().get(i));
            balls[i].addToGame(this);
            balls[i].setGameEnvironment(this.environment);
            this.ballsCounter.increase(1);
        }
    }

    /**
     * Generate the blocks in the game and add them to this environment.
     */
    private void generateBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (Block i : this.levelInformation.blocks()) {
            i.addToGame(this);
            i.addHitListener(blockRemover);
            i.addHitListener(scoreTrackingListener);
        }
        this.blocksCounter.increase(this.levelInformation.numberOfBlocksToRemove());
        Block[] blocks = new Block[3];
        blocks[0] = new Block(new Point(0, 25), 25, HEIGHT - 25, Color.GRAY);
        blocks[1] = new Block(new Point(WIDTH - 25, 25), 25, HEIGHT - 25, Color.GRAY);
        blocks[2] = new Block(new Point(0, 25), WIDTH, 25, Color.GRAY);
        for (int i = 0; i < 3; i++) {
            blocks[i].addToGame(this);
        }
        for (Block i : this.deathBlocks) {
            i.addToGame(this);
        }
        for (Block i : this.liveBlock) {
            i.addToGame(this);
        }
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        generateBalls();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.addSprite(scoreIndicator);
        Paddle paddle = new Paddle(new Rectangle(
                new Point((float) (WIDTH / 2 - (this.levelInformation.paddleWidth() / 2)),
                        HEIGHT - PADDLE_HEIGHT),
                this.levelInformation.paddleWidth(), PADDLE_LENGTH),
                this.gui.getKeyboardSensor(), this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
        generateBlocks();
    }

    /**
     * Checks if the animation should stop.
     *
     * @return if the animation should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d - the draw-surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if (this.blocksCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Run the game. start the animation loop.
     */
    public void run() {
        this.running = true;
        // Use our runner to run the current animation - which is one turn of the game.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }
}