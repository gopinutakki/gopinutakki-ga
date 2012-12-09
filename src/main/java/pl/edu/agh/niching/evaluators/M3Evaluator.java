package pl.edu.agh.niching.evaluators;

import java.io.IOException;
import java.util.List;

import org.uncommons.maths.binary.BitString;

public class M3Evaluator extends M14Evaluator {



	@Override
	public String getName() {
		return "M3";
	}

	@Override
	protected double function(double x) {
		return Math.pow( Math.sin( 5 * Math.PI * (Math.pow(x, 0.75) - .05) ), 6);
	}

}
