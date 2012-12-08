package pl.edu.agh.niching.evaluators;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public abstract class MEvaluator implements FitnessEvaluator<BitString>{
	PrintStream populationStream;
	public MEvaluator() {
		super();
		//todo: close on disposing/destructor
		try {
			populationStream = new PrintStream(new File(getName()+"gif.log"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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

	public abstract String getName();

	public PrintStream getPopulationGifStream() {
		return populationStream;
	}
	
	
}
