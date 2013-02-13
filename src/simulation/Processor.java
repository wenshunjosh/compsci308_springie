package simulation;

import java.util.Scanner;

/**
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public abstract class Processor {
	/**
	 * Process and add the object to the simulation model.
	 * @param line user input that carries information of the object
	 */
	public abstract void commandLine(Scanner line);
}
