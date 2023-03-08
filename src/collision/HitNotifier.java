package collision;

/**
 * Indicate that objects that implement it send notifications when they are being hit.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl a hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a hit listener.
     */
    void removeHitListener(HitListener hl);
}
