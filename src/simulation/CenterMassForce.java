package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Location;
import util.Vector;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class CenterMassForce extends Force {

	private Location myCenter;

	/**
	 * Create a default center of mass force.
	 */
	public CenterMassForce() {
		super();
	}

	/**
	 * Create a center of mass force based on user input.
	 */
	public CenterMassForce (double exponent, double magnitude) {
		super(exponent, magnitude);
	}

	/**
	 * Apply center of mass force to the mass assembly.
	 */
	@Override
	public void updateForce (double elapsedTime, Dimension bounds,
                                                List<Mass> allMasses) {
		myCenter = getCenter(allMasses);
		super.updateForce(elapsedTime, bounds, allMasses);
	}

	/**
	 * Calculate the center of mass force applied to a specific mass.
	 * @return the force applied to the mass
	 */
    @Override
	public Vector getForce(Mass m) {
    	double angle = angleBetween(myCenter.x - m.getX(),
                                         myCenter.y - m.getY());
		double distance = Math.sqrt((myCenter.x - m.getX())
		          * (myCenter.x - m.getX()) + (myCenter.y - m.getY())
				  * (myCenter.y - m.getY()));
		Vector force = new Vector(angle, getMagnitude()
				                      / distance * getAngle());
    	return force;
	}

    /**
     * Calculate the center of the mass assembly.
     * @param allMasses the corresponding mass assembly
     * @return the location of the center
     */
    public Location getCenter(List<Mass> allMasses) {
    	double totalX = 0;
    	double totalY = 0;
    	double totalMass = 0;
    	for (Mass m: allMasses) {
    		totalX += m.getX() * m.getMass();
    		totalY += m.getY() * m.getMass();
    		totalMass += m.getMass();
    	}
    	double centerX = totalX * (1 / totalMass);
    	double centerY = totalY * (1 / totalMass);
    	return new Location(centerX, centerY);
    }
}
