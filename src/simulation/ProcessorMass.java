package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ProcessorMass extends Processor {

	private Factory myFactory;

	/**
	 * Construct the processor for mass.
	 */
	ProcessorMass(Factory factory) {
		myFactory = factory;
	}

	/**
	 * Process and add the mass to simulation model based on user input.
	 */
	@Override
	public void commandLine(Scanner line) {
		int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        if (mass < 0) {
        	FixedMass result = new FixedMass(x, y, mass);
        	myFactory.getMasses().put(id,  result);
        	myFactory.getModel().add(result);
        } else {
        	Mass result = new Mass(x, y, mass);
        	myFactory.getMasses().put(id,  result);
        	myFactory.getModel().add(result);
        }
	}
}
