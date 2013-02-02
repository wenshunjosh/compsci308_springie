package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

public class WallForce extends Vector{
	private double myExponent;
	private double myMagnitude;
	private int myWallID; //user input 1: top; 2: right; 3: bottom; 4: left;
	
	public WallForce(int wallID, double magnitude, double exponent) {
		super(magnitude,exponent);
		myExponent = exponent;
		myMagnitude = magnitude;
		myWallID = wallID;
	}
	
	public void update (double elapsedTime, Dimension bounds, List<Mass> allMasses){
		for (Mass m: allMasses){
			double[] distance = {m.getY(), bounds.getWidth() - m.getX(), bounds.getHeight() - m.getY(), m.getX()};
			double[] myWallDirections = {90, 180, 270, 0};
			Vector force = new Vector(myWallDirections[myWallID], myMagnitude/distance[myWallID]*myExponent);
			m.applyForce(force);
		}
	}
}
