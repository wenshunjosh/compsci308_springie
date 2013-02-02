package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

public class ViscosityForce extends Vector {
	private double myMagnitude;
	
	public ViscosityForce (double magnitude) {
		myMagnitude = magnitude;
	}
	
	public void update(double elapsedTime, Dimension bounds, List<Mass> allMasses){
		for (Mass m: allMasses){
			Vector force=new Vector(m.getAcceleration().getDirection()*-1, myMagnitude);
    		m.applyForce(force);
		}
	}
}
