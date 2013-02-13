package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class ProcessorMuscle extends Processor {

	private Factory myFactory;

	/**
	 * Construct the processor for muscle.
	 */
	ProcessorMuscle(Factory factory) {
		myFactory = factory;
	}

	/**
	 * Process and add the muscle to the simulation model from user input.
	 */
	@Override
	public void commandLine(Scanner line) {
		Mass m1 = myFactory.getMasses().get(line.nextInt());
        Mass m2 = myFactory.getMasses().get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        double a = line.nextDouble();
        myFactory.getModel().add(new Muscle(m1, m2, restLength, ks, a));
	}
}
