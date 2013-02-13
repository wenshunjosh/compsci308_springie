package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import view.Canvas;


/**
 * @author Robert C. Duvall
 * @author Wenshun Liu
 * @author Josh Waldman
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

    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<ArrayList<Mass>> myAssembly = new ArrayList<ArrayList<Mass>>();
    private int myAssemblyNumber = 0;
    private List<Spring> mySprings = new ArrayList<Spring>();
    private Force[] myForces = {new GravityForce(), new ViscosityForce(),
                    new CenterMassForce(),new WallForce(1), new WallForce(2),
                    new WallForce(3), new WallForce(4)};
    private Integer[] myForcesToggleKeys = {GRAVITY_TOGGLE, VISCOSITY_TOGGLE,
                      CENTER_MASS_TOGGLE, WALL_TOP_TOGGLE, WALL_RIGHT_TOGGLE,
                      WALL_BOTTOM_TOGGLE, WALL_LEFT_TOGGLE};
    private int[] myWallSizeAdaptors = {10, -10};
    private Integer[] myWallSizeKeys = {INCREASE_WALL_SIZE, DECREASE_WALL_SIZE};

	private Mass myMouseMass; //created for mouse interaction
	private Spring myMouseSpring;


    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model(Canvas canvas) {
        myView = canvas;
    }

    /**
     * Draw all elements of the simulation.
     */
    public void paint(Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (ArrayList<Mass> masses: myAssembly) {
        	for (Mass m : masses) {
                m.paint(pen);
            }
        }
        if (myMouseSpring != null) {
        	myMouseSpring.paint(pen);
        }
        if (myMouseMass != null) {
        	myMouseMass.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     * @param elapsedTime the time frame from simulation
     */
    public void update(double elapsedTime) {
    	Dimension bounds = myView.getGameSize();
        int keyEvent = myView.getLastKeyPressed();
			processKeyEvent(keyEvent);
        Point mouseEvent = myView.getLastMousePosition();
        processMouseEvent(mouseEvent);
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
        for (ArrayList<Mass> masses: myAssembly) {
        	for (Mass m : masses) {
                m.update(elapsedTime, bounds);
            }
        	for (Force v: myForces) {
            	v.update(elapsedTime, bounds, masses);
            }
        }
        if (myMouseSpring != null) {
        	myMouseSpring.update(elapsedTime, bounds);
        }
    }
    /**
     * @param mouseEvent the last mouse position
     */
    private void processMouseEvent(Point mouseEvent) {
    	if (mouseEvent != null) {
    		if (myMouseMass == null) {
    			myMouseMass = new Mass(mouseEvent.getX(),
                                          mouseEvent.getY(), 0);
    		}
    		myMouseMass.setCenter(mouseEvent.getX(), mouseEvent.getY());
    		if (myMouseSpring == null) {
    			myMouseSpring =
                         calculateMouseDistanceAndCreateSpring(mouseEvent);
    		}
        } else {
        	myMouseMass = null;
        	myMouseSpring = null;
        }
	}
    /**
     * Calculates and finds the closest mass to the mouse position, and creates
     * the spring.
     * @param mouseEvent the last mouse position
     * @return the Spring generated through mouse click
     */
    private Spring calculateMouseDistanceAndCreateSpring (Point mouseEvent) {
    	Mass closest = null;
    	final double MAX_DISTANCE = 500;
    	double closestDistance = MAX_DISTANCE;
    	for (ArrayList<Mass> masses: myAssembly) {
    		for (Mass m: masses) {
        		double distance = myMouseMass.distance(m);
        		if (distance < closestDistance) {
        			closest = m;
       				closestDistance = distance;
        		}
        	}
    	}
    	return new Spring(myMouseMass, closest, closestDistance,
                                              Spring.DEFAULT_KVAL);
    }

    /**
     * Take user key input and process accordingly.
     * @param keyEvent user's key input
     */
	private void processKeyEvent(int keyEvent) {
    	if (keyEvent == CLEAR_ASSEMBLY) {
        	mySprings = new ArrayList<Spring>();
        	myAssembly = new ArrayList<ArrayList<Mass>>();
        	myAssemblyNumber = -1;
        }
        if (keyEvent == NEW_ASSEMBLY) {
        	myAssemblyNumber += 1;
        	myView.loadModel();
        	myView.clearInput();
        }
        List<Integer> myForcesToggleKeysArrayList =
                                 Arrays.asList(myForcesToggleKeys);
        if (myForcesToggleKeysArrayList.contains(keyEvent)) {
        	int keyEventIndex =
                           myForcesToggleKeysArrayList.indexOf(keyEvent);
        	myForces[keyEventIndex].setOppositeOn();
        }
        List<Integer> myWallSizeKeysArrayList = Arrays.asList(myWallSizeKeys);
        if (myWallSizeKeysArrayList.contains(keyEvent)) {
        	int keyEventIndex = myWallSizeKeysArrayList.indexOf(keyEvent);
        	myView.setGameSize(myWallSizeAdaptors[keyEventIndex]);
        }
	}

	/**
     * Add given mass to this simulation.
     * @param mass Mass object needs to be added
     */
    public void add (Mass mass) {
        if (myAssembly.size() <= myAssemblyNumber) {
        	ArrayList<Mass> currentMassAssembly = new ArrayList<Mass>();
        	myAssembly.add(currentMassAssembly);
        }
    	myAssembly.get(myAssemblyNumber).add(mass);
    }

    /**
     * Add given spring to this simulation.
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }

    /**
     * Add given muscle to this simulation.
     */
    public void add (Muscle muscle) {
    	mySprings.add(muscle);
    }

    /**
     * Add given gravity force to this simulation.
     */
    public void add (GravityForce gravityForce) {
    	myForces[1] = gravityForce; //how to get rid of the magic numbers here?
    }

    /**
     * Add given viscosity force to this simulation.
     */
    public void add (ViscosityForce viscosityForce) {
    	myForces[2] = viscosityForce;
    }

    /**
     * Add given center of mass force to this simulation.
     */
    public void add (CenterMassForce centerMassForce) {
    	myForces[3] = centerMassForce;
    }

    /**
     * Add given wall force to this simulation.
     */
    public void add(WallForce wallForce) {
    	int wallID = wallForce.getWallID();
    	myForces[wallID + 2] = wallForce;
    }
}
