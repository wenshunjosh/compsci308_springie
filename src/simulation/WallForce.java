package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class WallForce extends Force {
	public static final double DEFAULT_TOP_WALL_FORCE_DIRECTION = 90;
	public static final double DEFAULT_RIGHT_WALL_FORCE_DIRECTION = 180;
	public static final double DEFAULT_BOTTOM_WALL_FORCE_DIRECTION = 90;
	public static final double DEFAULT_LEFT_WALL_FORCE_DIRECTION = 180;

	private int myWallID; //user input 1: top; 2: right; 3: bottom; 4: left;
	private Dimension myBounds;

	/**
	 * Construct the default wall force for the wall specified by
	 * the wallID.
	 * @param wallID the index that represents a specific side of wall
	 */
	public WallForce(int wallID) {
		super();
		myWallID = wallID - 1;
	}

	/**
	 * Construct a wall force based on user input.
	 */
	public WallForce(int wallID, double exponent, double magnitude) {
		super(exponent, magnitude);
		myWallID = wallID - 1;
	}

	/**
	 * Apply wall force to a mass assembly.
	 */
	@Override
	public void updateForce (double elapsedTime, Dimension bounds,
			                                List<Mass> allMasses) {
		myBounds = bounds;
		super.updateForce(elapsedTime, bounds, allMasses);
	}

	/**
	 * Calculate the return the wall force applied to a specific mass.
	 */
	@Override
	public Vector getForce(Mass m) {
		double[] distance = {m.getY(), myBounds.getWidth() - m.getX(),
                              myBounds.getHeight() - m.getY(), m.getX()};
		double[] myWallDirections = {DEFAULT_TOP_WALL_FORCE_DIRECTION,
			            DEFAULT_RIGHT_WALL_FORCE_DIRECTION,
                        DEFAULT_BOTTOM_WALL_FORCE_DIRECTION,
				        DEFAULT_LEFT_WALL_FORCE_DIRECTION};
		Vector force = new Vector(myWallDirections[myWallID],
                     getMagnitude() / distance[myWallID] * getAngle());
		return force;
	}

	/**
	 * Get the wallID associated with a wall force.
	 */
	public int getWallID() {
		return myWallID;
	}
}
