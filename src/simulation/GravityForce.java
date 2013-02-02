package simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import util.Vector;

public class GravityForce extends Vector {
	private double myDirection;
	private double myMagnitude;
	
	public GravityForce (double angle, double magnitude) {
		super(angle, magnitude);
		myDirection = angle;
		myMagnitude = magnitude;
	}
	
	@Override
	public void update(double elapsedTime, Dimension bounds, List<Mass> allMasses){
		for (Mass m: allMasses){
			Vector force = new Vector(myDirection, m.getMass()*myMagnitude);
			m.applyForce(force);
		}
	}
}
