package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ProcessorGravity extends Processor {

	private Factory myFactory;

	/**
	 * Construct the processor for gravity force.
	 */
	ProcessorGravity(Factory factory){
		myFactory = factory;
	}

	/**
	 * Process and add the gravity force to simulation model.
	 */
	@Override
	public void commandLine(Scanner line) {
		double angle = line.nextDouble();
    	double magnitude = line.nextDouble();
    	myFactory.getModel().add(new GravityForce(angle, magnitude));
	}
}
