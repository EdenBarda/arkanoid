package collision;

import level.GameLevel;
import visible.Ball;
import visible.Block;

/**
 * A BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructor for block remover.
     *
     * @param game          the GameLevel.
     * @param removedBlocks the remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit remove from the game.
     *
     * @param beingHit the object that being hit.
     * @param hitter   the hitter object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}