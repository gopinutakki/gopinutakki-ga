package pl.edu.agh.niching.evaluators;


import java.io.IOException;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public abstract class MEvaluator implements FitnessEvaluator<BitString>{
	public abstract int getCandidateBitLength();
	
	/**
	 * Implementation of {@code FitnessEvaluator.isNatural()} as described in 
	 * http://watchmaker.uncommons.org/api/org/uncommons/watchmaker/framework/FitnessEvaluator.html#isNatural()
	 * 
	 * @return true, as this is a natural fitness scoring
	 */
	@Override
	public boolean isNatural() {
		return true;
	}
	
	/**
	 * Use this to print fitness function evaluation to stdout for gnuplot input.
	 * 
	 * @throws IOException if anything bad happens while writing to a file
	 */
	public abstract void plotFitnessFunction() throws IOException;
	
	
}
