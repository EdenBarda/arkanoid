package collision;

import visible.Ball;
import visible.Block;

/**
 * For objects that want to be notified of hit events.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the object that being hit.
     * @param hitter   the hitter object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}