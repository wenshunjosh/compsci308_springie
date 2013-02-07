package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

public class ViscosityForce extends Vector {
	private static final double DEFAULT_MAGNITUDE = 100;
	
	private double myMagnitude;
	
	public ViscosityForce(){
		myMagnitude = DEFAULT_MAGNITUDE;
		setStatus(false);
	}
	
	public ViscosityForce (double magnitude) {
		myMagnitude = magnitude;
		setStatus(true);
	}
	
	public void update(double elapsedTime, Dimension bounds, List<Mass> allMasses){
		if (getStatus()){
			for (Mass m: allMasses){
				Vector force=new Vector(m.getAcceleration().getDirection()*-1, myMagnitude);
	    		m.applyForce(force);
			}
		}
	}
}
