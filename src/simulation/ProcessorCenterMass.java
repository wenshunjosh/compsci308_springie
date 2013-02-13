package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ProcessorCenterMass extends Processor {

	private Factory myFactory;

	/**
	 * Construct the processor of center mass force.
	 */
	ProcessorCenterMass(Factory factory) {
		myFactory = factory;
	}

	/**
	 * Process and add the center mass force to the simulation model.
	 */
	@Override
	public void commandLine(Scanner line) {
		double magnitude = line.nextDouble();
    	double exponent = line.nextDouble();
    	myFactory.getModel().add(new CenterMassForce(exponent, magnitude));
	}
}
