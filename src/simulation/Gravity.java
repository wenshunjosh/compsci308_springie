package simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import util.Vector;

public class Gravity extends Vector {
	private double myAngle;
	private double myMagnitude;
	
	public Gravity (double angle, double magnitude) {
		super(angle, magnitude);
		myAngle = angle;
		myMagnitude = magnitude;
	}
	
	@Override
	public void update(double elapsedTime, Dimension bounds, List<Mass> allMasses){
		for (Mass m: allMasses){
			Vector force = new Vector(myAngle, m.getMass()*myMagnitude);
			m.applyForce(force);
		}
	}
}
