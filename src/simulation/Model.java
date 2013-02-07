package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import util.Location;
import util.Vector;
import view.Canvas;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Model {
    private static final int NEW_ASSEMBLY = KeyEvent.VK_N;
    private static final int CLEAR_ASSEMBLY = KeyEvent.VK_C;
    private static final int GRAVITY_TOGGLE = KeyEvent.VK_G;
    private static final int VISCOSITY_TOGGLE = KeyEvent.VK_V;
    private static final int CENTER_MASS_TOGGLE = KeyEvent.VK_M;
    private static final int WALL_TOP_TOGGLE = KeyEvent.VK_1;
    private static final int WALL_RIGHT_TOGGLE = KeyEvent.VK_2;
    private static final int WALL_BOTTOM_TOGGLE = KeyEvent.VK_3;
    private static final int WALL_LEFT_TOGGLE = KeyEvent.VK_4;
    private static final int INCREASE_WALL_SIZE = KeyEvent.VK_UP;
    private static final int DECREASE_WALL_SIZE = KeyEvent.VK_DOWN;
    private static final long KEY_COOL_DOWN_TIME = 200;
	
	// bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private Vector[] myForces = {new GravityForce(), new ViscosityForce(), new CenterMassForce(),
    							new WallForce(1), new WallForce(2), new WallForce(3), new WallForce(4)};
    private List<Integer> myForcesToggleKeys = new ArrayList<Integer>();
    private int[] myWallSizeAdaptors = {10, -10};
    private List<Integer> myWallSizeKeys = new ArrayList<Integer>();
    private Long myTimer1;
	private Long myTimer2;

    
    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForcesToggleKeys.add(GRAVITY_TOGGLE);
        myForcesToggleKeys.add(VISCOSITY_TOGGLE);
        myForcesToggleKeys.add(CENTER_MASS_TOGGLE);
        myForcesToggleKeys.add(WALL_TOP_TOGGLE);
        myForcesToggleKeys.add(WALL_RIGHT_TOGGLE);
        myForcesToggleKeys.add(WALL_BOTTOM_TOGGLE);
        myForcesToggleKeys.add(WALL_LEFT_TOGGLE);
        myWallSizeKeys.add(INCREASE_WALL_SIZE);
        myWallSizeKeys.add(DECREASE_WALL_SIZE);
        myTimer1 = System.currentTimeMillis();
    }

    /**
     * Draw all elements of the simulation.
     */
    public void paint (Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (Mass m : myMasses) {
            m.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getGameSize(); //update in order to change size
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
        for (Vector v: myForces){
        	v.update(elapsedTime, bounds, myMasses);
        }
        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds);
        }
        myTimer2 = System.currentTimeMillis(); //"Cools down" the keyboard so one press will only result in one update.
		if (myTimer2-myTimer1 >= KEY_COOL_DOWN_TIME){
			myTimer1 = myTimer2;
			int keyEvent = myView.getLastKeyPressed();
	        if (keyEvent == CLEAR_ASSEMBLY){
	        	mySprings = new ArrayList<Spring>();
	        	myMasses = new ArrayList<Mass>();
	        }
	        if (keyEvent == NEW_ASSEMBLY){
	        	//TODO
	        }
	        if(myForcesToggleKeys.contains(keyEvent)){
	        	int keyEventIndex = myForcesToggleKeys.indexOf(keyEvent);
	        	myForces[keyEventIndex].setOppositeStatus();
	        }
	        if(myWallSizeKeys.contains(keyEvent)){ //duplicate with the above if statement??
	        	int keyEventIndex = myWallSizeKeys.indexOf(keyEvent);
	        	myView.setGameSize(myWallSizeAdaptors[keyEventIndex]);
	        }
		}
    }

    /**
     * Add given mass to this simulation.
     */
    public void add (Mass mass) {
        myMasses.add(mass);
    }

    /**
     * Add given spring to this simulation.
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }
    
    public void add (Muscle muscle){
    	mySprings.add(muscle);
    }
    
    public void add (GravityForce gravityForce){
    	myForces[1] = gravityForce;
    }
    
    public void add (ViscosityForce viscosityForce){
    	myForces[2] = viscosityForce;
    }
    
    public void add (CenterMassForce centerMassForce){
    	myForces[3] = centerMassForce;
    }
    public void add(WallForce wallForce){
    	int wallID = wallForce.getWallID();
    	myForces[wallID+2] = wallForce;
    }
    
}
