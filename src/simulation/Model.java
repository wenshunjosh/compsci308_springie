package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

import util.Location;
import util.Vector;
import view.Canvas;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private List<Muscle> myMuscles;
    private List<Vector> myForces;

    
    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myMuscles = new ArrayList<Muscle>();
        myForces = new ArrayList<Vector>();
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
        for (Muscle m: myMuscles){
        	m.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
        for (Vector v: myForces){
        	v.update(elapsedTime, bounds, myMasses);
        }
        for (Muscle m: myMuscles){
        	m.update(elapsedTime, bounds);
        }
        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds);
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
    	myMuscles.add(muscle);
    }
    
    public void add (GravityForce gravityForce){
    	myForces.add(gravityForce);
    }
    
    public void add (ViscosityForce viscosityForce){
    	myForces.add(viscosityForce);
    }
    
    public void add (CenterMassForce centerMassForce){
    	myForces.add(centerMassForce);
    }
    public void add(WallForce wallForce){
    	myForces.add(wallForce);
    }
    
}
