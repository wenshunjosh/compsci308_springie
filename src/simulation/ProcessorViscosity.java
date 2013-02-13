package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ProcessorViscosity extends Processor {

	private Factory myFactory;

	/**
	 * Construct the processor for viscosity force.
	 */
	ProcessorViscosity(Factory factory) {
		myFactory = factory;
	}

	/**
	 * Processor viscosity force from user input.
	 * @param line the input line from the file to be loaded
	 */
	@Override
	public void commandLine(Scanner line) {
		double magnitude = line.nextDouble();
    	myFactory.getModel().add(new ViscosityForce(magnitude));
	}
}
