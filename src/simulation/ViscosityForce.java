package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ViscosityForce extends Force {

	/**
	 * Construct the default viscosity force.
	 */
	public ViscosityForce() {
		super();
	}

	/**
	 * Construct the viscosity force based on user input.
	 */
	public ViscosityForce (double magnitude) {
		super(0 , magnitude);
	}

	/**
	 * Apply viscosity force to the mass assembly.
	 */
	@Override
	public void updateForce(double elapsedTime, Dimension bounds,
			                                List<Mass> allMasses) {
		super.updateForce(elapsedTime, bounds, allMasses);
	}

	/**
	 * Calculate the return the viscosity force applied to a specific mass.
	 */
	@Override
	public Vector getForce(Mass m) {
		return new Vector(m.getAcceleration().getDirection() * (-1),
				                                getMagnitude());
	}
}
