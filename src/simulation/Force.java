package simulation;
import java.awt.Dimension;
import java.util.List;

import util.Vector;


public class Force extends Vector {
	private boolean myOn = false;

	/**
	 * Construct a default force.
	 */
	public Force() {
		super();
	}

	/**
	 * Construct a force based on user input.
	 */
	public Force (double angle, double magnitude) {
		super(angle, magnitude);
		myOn = true;
	}

	/**
	 * Check if the force is turned on. If so apply it to mass assembly.
	 * @param bounds the bounds of the simulation
	 * @param allMasses the mass assembly the force is applied to
	 */
	public void update(double elapsedTime, Dimension bounds,
			                             List<Mass> allMasses) {
		if (myOn) {
			updateForce(elapsedTime, bounds, allMasses);
		}
		return;
	}

	/**
	 * Apply the force to the mass assembly.
	 * @param bounds the bounds of the simulation
	 * @param allMasses the mass assembly the force is applied to
	 */
	public void updateForce(double elapsedTime, Dimension bounds,
			                                 List<Mass> allMasses) {
		for (Mass m: allMasses) {
			Vector force = getForce(m);
			m.applyForce(force);
		}
		return;
	}

	/**
	 * Calculate the force applied to a specific mass. In this case the
	 * value is set to default.
	 * @param m the mass that the force is applied to
	 * @return the applied force
	 */
	public Vector getForce (Mass m) {
		return new Vector();
	}

	/**
	 * Set the boolean that determines if the force is on.
	 */
	public void setOn (boolean status) {
		myOn = status;
	}

	/**
	 * Get the boolean that determines if the force is on.
	 */
	public boolean getOn () {
		return myOn;
	}

	/**
	 * Set the boolean that determines if the force is on to its opposite
	 * value.
	 */
	public void setOppositeOn() {
		if (myOn) {
			myOn = false;
		} else {
			myOn = true;
		}
	}
}
