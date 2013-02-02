package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Location;
import util.Vector;

public class CenterMassForce extends Vector {
	private double myMagnitude;
	private double myExponent;
	
	public CenterMassForce (double magnitude, double exponent) {
		myExponent = exponent;
		myMagnitude = magnitude;
	}
	
	public void update (double elapsedTime, Dimension bounds, List<Mass> allMasses){
		Location center = getCenter(allMasses);
		for (Mass m: allMasses){
			double angle = angleBetween(center.x-m.getX(), center.y-m.getY());
			double distance = Math.sqrt((center.x-m.getX())*(center.x-m.getX()) + (center.y-m.getY())* (center.y-m.getY()));
			Vector force = new Vector(angle, myMagnitude/distance*myExponent);
					//I hope the formula is correct.
			m.applyForce(force);
		}
	}
	
    public Location getCenter(List<Mass> allMasses){
    	double totalX= 0;
    	double totalY = 0;
    	double totalMass = 0;
    	for (Mass m: allMasses){
    		totalX += m.getX()*m.getMass();
    		totalY += m.getY()*m.getMass();
    		totalMass += m.getMass();
    	}
    	double centerX = totalX*(1/totalMass);
    	double centerY = totalY*(1/totalMass);
    	return new Location(centerX, centerY);
    }
	
}
