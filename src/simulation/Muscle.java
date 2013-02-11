package simulation;

import java.awt.Dimension;

import util.Pixmap;
import util.Sprite;

public class Muscle extends Spring {
	
	private double myPie = 0;
	private double myFrequency = 0.1; //can be user input later on;
	private double myAmplitude;
	
	public Muscle(Mass start, Mass end, double length, double kVal, double amplitude) {
		super(start, end, length, kVal);
		myAmplitude = amplitude;
	}
	
	public void update(double elapsedTime, Dimension bounds){
		super.update(elapsedTime, bounds);
	}
	
    public double getLength(){
    	double muscleLength = myAmplitude*Math.sin(myPie); //update free length of Muscle;
		myPie += myFrequency;
		return muscleLength;
    }

}
