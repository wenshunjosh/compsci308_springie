package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

public class ViscosityForce extends Vector {
	private static final double DEFAULT_MAGNITUDE = 100;
	
	private double myMagnitude;
	
	public ViscosityForce(){
		this(DEFAULT_MAGNITUDE);
		setOn(false); //how to get rid of this repeated code?????
	}
	
	public ViscosityForce (double magnitude) {
		myMagnitude = magnitude;
		setOn(true);
	}
	
	public void updateForce(double elapsedTime, Dimension bounds, List<Mass> allMasses){
		for (Mass m: allMasses){
			Vector force=new Vector(m.getAcceleration().getDirection()*-1, myMagnitude);
	   		m.applyForce(force);
		}
	}
}
