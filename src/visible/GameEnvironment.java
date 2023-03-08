package visible;

import collision.CollisionInfo;
import shape.Line;

import java.util.ArrayList;

/**
 * The game environment.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class GameEnvironment {
    private final ArrayList<Collidable> gameEnvironment;

    /**
     * Constructor for GameEnvironment.
     */
    public GameEnvironment() {
        gameEnvironment = new ArrayList<>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c a collodable.
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironment.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of an object.
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<CollisionInfo> a = new ArrayList<>();
        for (Collidable i : this.gameEnvironment) {
            if (trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle()) != null) {
                a.add(new CollisionInfo(trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle()), i));
            }
        }
        if (a.size() != 0) {
            CollisionInfo b = a.get(0);
            for (CollisionInfo i : a) {
                if (trajectory.start().distance(i.collisionPoint())
                        <= trajectory.start().distance(b.collisionPoint())) {
                    b = i;
                }
            }
            return b;
        } else {
            return null;
        }
    }

    /**
     * @return the game environment.
     */
    public ArrayList<Collidable> getGameEnvironment() {
        return gameEnvironment;
    }
}