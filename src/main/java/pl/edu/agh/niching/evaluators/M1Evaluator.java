package pl.edu.agh.niching.evaluators;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;

public class M1Evaluator extends M14Evaluator {

	@Override
	protected double function(double x) {
		return Math.pow( Math.sin( 5 * Math.PI * x ), 6);
	}
	@Override
	protected double[] peaks(){
		return new double[]
		{
			0.1, 0.3, 0.5, 0.7, 0.9
		};
	}
	@Override
	public String getName(){
		return "M1";
	}

}
