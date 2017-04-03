package liveobject;

import animation.Animator;
import geometry.Vector2D;

/**
 * Handles gravity
 */
public class GravitatingObject extends LiveObject
{
    /** The gravitational constant. */
    private final double GRAVITY_STRENGTH = 0.0;

    /** Extent of Gravity */
    private double GRAVITY_EXTENT = 0.0;

    /** Acceleration on this object due to gravity */
    private Vector2D gravity;

    /**
     * Creates a gravity object
     */
    public GravitatingObject(Animator anim){
        super(anim);
        gravity = new Vector2D(0,0);
    }

    /**
     * Return strength of gravity
     */
    public double gravityStrength(){
        return 0.0;
    }

    /**
     * Return extent of gravity
     */
    public double gravitySusceptibility(){
        return 0.0;
    }

    /**
     * @override interactWith
     */
    public void interactWith(GravitatingObject other){
        // Calculate acceleration due to gravity
        double xValue = position.getX() - other.getPosition().getX();
        double yValue = position.getY() - other.getPosition().getY();
        // Distance to object
        double d = Math.pow(Math.pow(xValue, 2) + Math.pow(yValue, 2), 0.5); 
        Vector2D u = new Vector2D(xValue, yValue);
        // Unit vector toward object
        u = u.scaleBy(1.0/d);                           
        // Acceleration due to gravity
        // (inverse-square law).
        gravity = gravity.addTo(u.scaleBy(GRAVITY_EXTENT * other.gravityStrength()/(d*d)));     
        
        if(other instanceof Star
        || other instanceof Spaceship
        || other instanceof Torpedo) {
            // Check for collisions
            if(other != this && other.overlaps(this))
                setDestroyed();
        }
    }
    
    /**
     * Destroy this LiveObject
     */
    private void setDestroyed() {
    }

    /**
     * @override update
     */
    public void update(double dt){
        super.update(dt);
        // A spaceship accelerates due to thrust and gravity.
        //Vector2D a = orientation.getDirection();//.scaleBy(thrust);
        setAcceleration(gravity);
        gravity = new Vector2D(0, 0);
        super.update(dt);
    }
}
