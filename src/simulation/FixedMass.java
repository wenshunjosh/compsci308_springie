package simulation;

import util.Vector;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class FixedMass extends Mass {

	/**
	 * Construct a fixed mass.
	 * @param x position on the x axis
	 * @param y position on the y axis
	 * @param mass value of mass
	 */
	public FixedMass(double x, double y, double mass) {
		super(x, y, -mass);
	}

	/**
	 * Apply a force to the fixed mass. In this does nothing.
	 */
	@Override
	public void applyForce(Vector force) {
		//do nothing
	}
}
