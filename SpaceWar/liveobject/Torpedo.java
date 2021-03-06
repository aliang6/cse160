package liveobject;

import geometry.Circle2D;
import geometry.Vector2D;

import animation.Animator;

import java.awt.Graphics;

/**
 * An extension of LiveObject that implements a torpedo.
 */

public class Torpedo extends GravitatingObject {
    
    /** Susceptibility to Gravity */
    private final double GRAVITY_EXTENT = 1.0;
    
    /** Radius of a Torpedo */
    private final double RADIUS = 0.01;

    /** Whether the torpedo has been destroyed */
    private boolean destroyed = false;

    /**
     * Covering circles for this object.
     * Used for collision detection.
     */
    private final Circle2D EXTENT = new Circle2D(new Vector2D(0, 0), RADIUS);

    /**
     * Initialize a Torpedo.
     *
     * @param a The animator that manages the update and display.
     */
    public Torpedo(Animator a) {
        super(a);
        setLifetime(2.0);
        setExtent(EXTENT);
    }

    /**
     * Method for drawing the torpedo on the screen.
     *
     * @param g The graphics object to be used for drawing.
      */
    public void draw(Graphics g) {
        if(isVisible()) {
            g.setColor(getColor());
                animator.drawArc(g, position, RADIUS, 0, 360);
        }
    }
    
    public void interactWith(GravitatingObject other){
        if(other instanceof Star
        || other instanceof Spaceship
        || other instanceof Torpedo) {
            // Check for collisions
            if(other != this && other.overlaps(this))
                setDestroyed();
        }
    }

    /**
     * @override
     * Cause this torpedo to explode.
     * An exploded torpedo is temporarily replaced with an explosion graphic.
     */
    private void setDestroyed() {
        if(!destroyed) {
            destroyed = true;
            new Explosion(animator, position);
            setTerminated();
        }
    }
}
