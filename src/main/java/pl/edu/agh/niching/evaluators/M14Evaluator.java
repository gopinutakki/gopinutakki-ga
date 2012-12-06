package pl.edu.agh.niching.evaluators;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;

import pl.edu.agh.niching.GraphHelper;

public abstract class M14Evaluator extends MEvaluator{
	
	/*
	 * x = [0,1]
	 * */

	
	private int candidateBitLength = 30;
	
	@Override
	public int getCandidateBitLength(){
		return candidateBitLength;
	}
	
	protected abstract double function(double x);
	
	@Override
	public double getFitness(BitString candidate, List<? extends BitString> population) {
		double x = toDouble(candidate);
		return function(x);
	}
	
	protected abstract String getFileName();
	
	public double toDouble(BitString candidate) {
		return candidate.toNumber().doubleValue() / (Math.pow(2, candidateBitLength)-1);
	}
	
	@Override
	public void plotFitnessFunction() throws IOException {
		ArrayList<BitString> fitnessRange = new ArrayList<BitString>();
        String bits;
        String zeros = "00000000000000000000000000000000";
        for (long i=0; i<Math.pow(2, candidateBitLength); i+=Math.pow(2, 12)) {
        	bits = Long.toBinaryString(i);
        	bits = zeros.substring(bits.length()) + bits;
        	fitnessRange.add(new BitString(bits));
        }
        PrintStream outfile = new PrintStream(new File(getFileName())); 
        GraphHelper.printLabelledFunctionPlot(this, fitnessRange, outfile);
        outfile.close();
	}

}
