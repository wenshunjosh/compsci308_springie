package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * @author Robert C. Duvall
 * @author Wenshun Liu
 * @author Josh Waldman
 */
public class Factory {
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String MUSCLE_KEYWORD = "muscle";
    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTERMASS_KEYWORD = "centermass";
    private static final String WALL_KEYWORD = "wall";

    // mass IDs
    Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();
    private Processor[] myProcessors = {new ProcessorMass(this),
    		          new ProcessorSpring(this), new ProcessorMuscle(this),
    		          new ProcessorGravity(this), new ProcessorViscosity(this),
    		          new ProcessorCenterMass(this), new ProcessorWall(this)};
    private String[] myKeywords = {MASS_KEYWORD, SPRING_KEYWORD,
    	               MUSCLE_KEYWORD, GRAVITY_KEYWORD, VISCOSITY_KEYWORD,
    	               CENTERMASS_KEYWORD, WALL_KEYWORD};
    private Model myModel;


    /**
     * Load the user input from a file.
     * @param model the simulation model
     * @param modelFile the file type of the user input
     */
    public void loadModel (Model model, File modelFile) {
    	myModel = model;
    	try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    List<String> myKeywordsArrayList
                                        = Arrays.asList(myKeywords);
                    if (myKeywordsArrayList.contains(type)) {
                    	int keywordsIndex = myKeywordsArrayList.indexOf(type);
                    	myProcessors[keywordsIndex].commandLine(line);
                    } else {
                    	System.out.println("Error");
                    }
                }
            }
            input.close();
    	} catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    /**
     * Get the HashMap that stores information of the masses and their indexes.
     * @return myMasses
     */
    public Map<Integer, Mass> getMasses() {
    	return myMasses;
    }

    /**
     * Get the model of simulation.
     * @return myModel
     */
    public Model getModel() {
    	return myModel;
    }
}
