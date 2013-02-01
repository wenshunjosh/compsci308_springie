package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

public class Wall extends Vector{
	private double myExponent;
	private double myMagnitude;
	private int myWallID;
	private int myBound;
	
	public Wall(int bound, int wallID, double magnitude, double exponent) {
		super(magnitude,exponent);
		myExponent = exponent;
		myMagnitude = magnitude;
		myWallID = wallID;
		myBound = bound;
	}
	
	public void update (double elapsedTime, Dimension bounds, List<Mass> allMasses){
		for (Mass m: allMasses){
			double[] distance = {m.getY(), myBound - m.getX(), myBound - m.getY(), m.getX()};
			double[] myWallDirections = {90, 180, 270, 0};
			Vector force = new Vector(myWallDirections[myWallID], myMagnitude/distance[myWallID]*myExponent);
			m.applyForce(force);
		}
	}
}
