import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private ArrayList<BouncingBall> allBouncingBalls;
    private ArrayList<BoxBall> allBoxBalls;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        allBouncingBalls = new ArrayList<BouncingBall>();
        allBoxBalls = new ArrayList<BoxBall>();
    }

    /**
     * Simulate a given amount of bouncing balls
     */
    public void bounce(int amountOfBalls)
    {
        allBouncingBalls.clear();
        int ground = 400;   // position of the ground line
        int ceiling = 100; // y value of the ceiling
        int leftWall = 50;
        int rightWall = 550;

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(leftWall, ground, rightWall, ground);
        myCanvas.drawLine(leftWall, ground, leftWall, ceiling);
        myCanvas.drawLine(rightWall, ground, rightWall, ceiling);

        // create and show the balls
        for(int i = 0; i < amountOfBalls; i++){
            int randomXPos = (int)((Math.random() * 500) + 50);
            int randomYPos = (int)(Math.random() * ground);
            int randomRValue = (int)(Math.random() * 255);
            int randomGValue = (int)(Math.random() * 255);
            int randomBValue = (int)(Math.random() * 255);
            Color randomColor = new Color(randomRValue, randomGValue, randomBValue);
            allBouncingBalls.add(new BouncingBall(randomXPos, randomYPos, 16, randomColor, ground, myCanvas));
            allBouncingBalls.get(i).draw();
        }

        // make them bounce
        boolean finished =  false;
        Iterator<BouncingBall> ballIterator = allBouncingBalls.iterator();
        while(!finished) {
            myCanvas.wait(50);           // small delay
            while(ballIterator.hasNext()){
                BouncingBall nextBall = ballIterator.next();
                nextBall.move();
                if(nextBall.getXPosition() >= 550){
                    finished = true;
                }
            }
            if(!ballIterator.hasNext()){
                ballIterator = allBouncingBalls.iterator();
            }
        }
    }
    
    /**
     * Simulate a given amount of balls that are able to bounce off of walls
     */
    public void boxBounce(int amountOfBalls)
    {
        allBoxBalls.clear();
        int ground = 400;   // position of the ground line
        int ceiling = 100; // y value of the ceiling
        int leftWall = 50;
        int rightWall = 550;

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(leftWall, ground, rightWall, ground);
        myCanvas.drawLine(leftWall, ground, leftWall, ceiling);
        myCanvas.drawLine(rightWall, ground, rightWall, ceiling);

        // create and show the balls\
        int randomYPos = 110;
        for(int i = 0; i < amountOfBalls; i++){
            int randomXPos = (int)((Math.random() * 500) + 50);
            int randomRValue = (int)(Math.random() * 255);
            int randomGValue = (int)(Math.random() * 255);
            int randomBValue = (int)(Math.random() * 255);
            Color randomColor = new Color(randomRValue, randomGValue, randomBValue);
            allBoxBalls.add(new BoxBall(randomXPos, randomYPos, 16, randomColor, ground, leftWall, rightWall, myCanvas));
            allBoxBalls.get(i).draw();
        }

        // make them bounce
        boolean finished =  false;
        Iterator<BoxBall> ballIterator = allBoxBalls.iterator();
        while(!finished) {
            myCanvas.wait(50);           // small delay
            while(ballIterator.hasNext()){
                BoxBall nextBall = ballIterator.next();
                nextBall.move();
                distanceCheckBoxBalls();
                
                
                /*if(nextBall.getXPosition() >= 550 - 16){
                    finished = true;
                }*/
            }
            if(!ballIterator.hasNext()){
                ballIterator = allBoxBalls.iterator();
            }
        }
    }
    
    /**
     * Calculate distance between all the balls, if they are colliding, then a collision is detected.
     */
    private void distanceCheckBoxBalls(){ //Assuming same size balls i.e. same mass and radius
        if(allBoxBalls.size() <= 1){
            return;
        }
        for(int i = 0; i < allBoxBalls.size(); i++){
            for(int j = 0; j < allBoxBalls.size(); j++){
                if(i != j){
                    
                    BoxBall one = allBoxBalls.get(i);
                    BoxBall two = allBoxBalls.get(j);
                    double distanceSq = (Math.pow(one.getXPosition()-two.getXPosition(), 2) + Math.pow(one.getYPosition()-two.getYPosition(), 2));
                    int distance = (int)Math.sqrt(distanceSq);
                    if(distance <= 16){
                        collisionDetected(one, two);
                    }
                }
            }
        }
    }
    
    /**
     * Change the speed of the balls when colliding.
     */
    private void collisionDetected(BoxBall one, BoxBall two){
        double constantk = (((one.getXSpeed() - two.getXSpeed())*(two.getXPosition() - one.getXPosition()) + (one.getYSpeed() - two.getYSpeed())*(two.getYPosition() - one.getYPosition()))/(Math.pow(16,2)));
        if(constantk > 0){
        one.setXSpeed((int)(one.getXSpeed() - constantk*(two.getXPosition() - one.getXPosition())));
        one.setYSpeed((int)(one.getYSpeed() - constantk*(two.getYPosition() - one.getYPosition())));
        two.setXSpeed((int)(two.getXSpeed() - constantk*(one.getXPosition() - two.getXPosition())));
        two.setYSpeed((int)(two.getYSpeed() - constantk*(one.getYPosition() - two.getYPosition())));
        }
    }
}
