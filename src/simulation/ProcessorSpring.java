package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ProcessorSpring extends Processor {

	private Factory myFactory;

	/**
	 * Construct the processor of the spring.
	 */
	ProcessorSpring(Factory factory) {
		myFactory = factory;
	}

	/**
	 * Process and add the spring to the simulation model from user input.
	 */
	@Override
	public void commandLine(Scanner line) {
		Mass m1 = myFactory.getMasses().get(line.nextInt());
        Mass m2 = myFactory.getMasses().get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        myFactory.getModel().add(new Spring(m1, m2, restLength, ks));
	}
}
