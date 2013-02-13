package simulation;

import java.awt.Dimension;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class Muscle extends Spring {

	private double myPie = 0;
	private double myFrequency = 0.1; //can be user input later on;
	private double myAmplitude;

	/**
	 * Construct the muscle based on user input.
	 */
	public Muscle(Mass start, Mass end, double length,
                            double kVal, double amplitude) {
		super(start, end, length, kVal);
		myAmplitude = amplitude;
	}

	/**
	 * Update force of the muscle and apply to corresponding masses.
	 */
	@Override
	public void update(double elapsedTime, Dimension bounds) {
		super.update(elapsedTime, bounds);
	}

	/**
	 * Calculate the free length of the muscle.
	 */
    @Override
	public double getLength() {
    	double muscleLength = myAmplitude * Math.sin(myPie);
		myPie += myFrequency;
		return muscleLength;
    }

}
