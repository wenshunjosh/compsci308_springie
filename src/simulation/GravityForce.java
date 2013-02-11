package simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import util.Vector;

public class GravityForce extends Vector {
	private static final double DEFAULT_DIRECTION = 90;
	private static final double DEFAULT_MAGNITUDE = 100;
	
	private double myDirection;
	private double myMagnitude;
	
	public GravityForce (){
		this(DEFAULT_DIRECTION, DEFAULT_MAGNITUDE);
		setOn(false);
	}
	
	public GravityForce (double angle, double magnitude) {
		super(angle, magnitude);
		myDirection = angle;
		myMagnitude = magnitude;
		setOn(true);
	}
	
	public void updateForce(double elapsedTime, Dimension bounds, List<Mass> allMasses){
		for (Mass m: allMasses){
			Vector force = new Vector(myDirection, m.getMass()*myMagnitude);
			m.applyForce(force);
		}
	}
}
