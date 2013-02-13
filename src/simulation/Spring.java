package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * @author Robert C. Duvall
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class Spring extends Sprite {
    // reasonable default values
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("spring.gif");
    public static final int IMAGE_HEIGHT = 20;
    public static final double DEFAULT_KVAL = 0.5;

    private Mass myStart;
    private Mass myEnd;
    private double myLength;
    private double myOriginalLength;
    private double myK;

    /**
     * Construct the spring based on user input.
     */
    public Spring (Mass start, Mass end, double length, double kVal) {
        super(DEFUALT_IMAGE, getCenter(start, end), getSize(start, end));
        myStart = start;
        myEnd = end;
        myLength = length;
        myOriginalLength = length;
        myK = kVal;
    }

    /**
     * Draw the spring on the screen.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(getColor(myStart.distance(myEnd) - myLength));
        pen.drawLine((int) myStart.getX(), (int) myStart.getY(),
                               (int) myEnd.getX(), (int) myEnd.getY());
    }

    /**
     * Update the spring's force and position, and apply the force to its
     * corresponding masses.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double dx = myStart.getX() - myEnd.getX();
        double dy = myStart.getY() - myEnd.getY();
        // apply hooke's law to each attached mass
        Vector force = new Vector(Vector.angleBetween(dx, dy),
                        myK * (getLength() - Vector.distanceBetween(dx, dy)));
        myStart.applyForce(force);
        force.negate();
        myEnd.applyForce(force);
        // update sprite values based on attached masses
        setCenter(getCenter(myStart, myEnd));
        setSize(getSize(myStart, myEnd));
        setVelocity(Vector.angleBetween(dx, dy), 0);

        resetLength();
    }

    /**
     * Reset the free length of the spring.
     */
    public void resetLength() {
    	myLength = myOriginalLength;
    }

    /**
     * Get the free length of the spring.
     * @return free length of the spring
     */
    public double getLength() {
    	return myLength;
    }

    /**
     * Convenience method.
     */
    protected Color getColor (double diff) {
        if (Vector.fuzzyEquals(diff, 0)) {
        	return Color.BLACK;
        } else if (diff < 0.0) {
        	return Color.BLUE;
        } else {
        	return Color.RED;
        }
    }

    /**
     * Compute center of the spring.
     * @return center of the spring
     */
    private static Location getCenter (Mass start, Mass end) {
    	return new Location((start.getX() + end.getX()) / 2,
        		                (start.getY() + end.getY()) / 2);
    }

    /**
     * Compute size of the spring.
     * @return size of the spring
     */
    private static Dimension getSize (Mass start, Mass end) {
        return new Dimension((int) start.distance(end), IMAGE_HEIGHT);
    }
}
