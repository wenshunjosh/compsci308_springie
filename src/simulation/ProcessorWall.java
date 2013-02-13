package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ProcessorWall extends Processor {

	private Factory myFactory;

	/**
	 * Construct the processor for wall force.
	 */
	ProcessorWall(Factory factory) {
		myFactory = factory;
	}

	/**
	 * Process the wall force from user input.
	 */
	@Override
	public void commandLine(Scanner line) {
		int wallID = line.nextInt();
    	double magnitude = line.nextDouble();
    	double exponent = line.nextDouble();
    	myFactory.getModel().add(new WallForce(wallID, exponent, magnitude));
	}
}
