package simulation;

import java.awt.Dimension;

import util.Pixmap;
import util.Sprite;

public class Muscle extends Spring {
	
	private double pie = 0;
	private double myAmplitude;
	
	public Muscle(Mass start, Mass end, double length, double kVal, double amplitude) {
		super(start, end, length, kVal);
		myAmplitude = amplitude;
	}
	
	public void update(double elapsedTime, Dimension bounds){
		
		updateMyLength(myAmplitude*Math.sin(pie));
		pie += 0.1;
		//update myLength
		
		super.update(elapsedTime, bounds);
	}

}
