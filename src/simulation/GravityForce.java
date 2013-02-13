package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

/**
 * @author Robert C. Duvall
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class GravityForce extends Force {

	/**
	 * Construct a default gravity force.
	 */
	public GravityForce() {
		super();
	}

	/**
	 * Construct a gravity force based on user input.
	 */
	public GravityForce(double angle, double magnitude) {
		super(angle, magnitude);
	}

	/**
	 * Apply gravity force to the mass assembly.
	 */
	@Override
	public void updateForce(double elapsedTime, Dimension bounds,
			                                List<Mass> allMasses) {
		super.updateForce(elapsedTime, bounds, allMasses);
	}

	/**
	 * Calculate the gravity force applied to a specific mass.
	 */
	@Override
	public Vector getForce(Mass m) {
		return new Vector(getAngle(), m.getMass() * getMagnitude());
	}
}
