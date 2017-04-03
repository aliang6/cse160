package liveobject;

import geometry.Circle2D;
import geometry.Vector2D;
import geometry.Segment2D;

import animation.Animator;

import java.awt.Graphics;

/**
 * An extension of LiveObject that implements a star.
 */

public class Star extends GravitatingObject {
    
    /** The gravitational constant. */
    private final double GRAVITY_STRENGTH = 0.1;
    
    /** Radius of a Star */
    private final double RADIUS = 0.1;
    
    /** Amount of Rays on Star */
    private final int RAY_AMOUNT = 10;

    /** Extent for a Star */
    private final Circle2D EXTENT = new Circle2D(new Vector2D(0, 0), RADIUS);
    
    /** Length of Star's Rays */
    private double rayLength;

    /**
     * Initialize a Star.
     *
     * @param a The animator that manages the update and display.
     */
    public Star(Animator a) {
        super(a);
        setExtent(EXTENT);
        rayLength = 0.02;
    }

    /**
     * Method for drawing the star on the screen.
     *
     * @param g The graphics object to be used for drawing.
     */
    public void draw(Graphics g) {
        if(isVisible()) {
            g.setColor(getColor());
            animator.drawArc(g, position, RADIUS, 0, 360);
            Vector2D startPoint = new Vector2D(0, RADIUS);
            Vector2D endPoint = new Vector2D(0, rayLength);
            Segment2D segment = new Segment2D(position.addTo(startPoint), position.addTo(startPoint).addTo(endPoint));
            animator.drawSegment(g, segment);
            for(int i = 0; i < RAY_AMOUNT; i++){
                segment = segment.rotateBy(360/RAY_AMOUNT);
                animator.drawSegment(g,segment);
            }
        }
    }
    
    public double gravityStrength(){
        return 0.1;
    }
    
    /**
     * Make the star twinkle
     */
    public void update(double dt){
        super.update(dt);
        double newLength = Math.random()*.03 + .02;
        rayLength = newLength;
    }
}
