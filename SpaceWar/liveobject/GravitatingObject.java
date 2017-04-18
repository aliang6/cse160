package liveobject;

import geometry.Circle2D;
import geometry.Orientation;
import geometry.Segment2D;
import geometry.Vector2D;

import animation.Animator;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Handles gravity
 */
public class GravitatingObject extends LiveObject
{
    /** The gravitational constant. */
    private final double GRAVITY_STRENGTH = 0.0;

    /** Susceptibility to Gravity */
    private final double GRAVITY_EXTENT = 0.0;

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
        
        
    }

    /**
     * @override update
     */
    public void update(double dt){
        // A spaceship accelerates due to thrust and gravity.
        //Vector2D a = orientation.getDirection();//.scaleBy(thrust);
        gravity = gravity.addTo(acceleration);
        setAcceleration(gravity);
        gravity = new Vector2D(0, 0);
        super.update(dt);
    }
}
