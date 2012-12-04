package pl.edu.agh.niching.evaluators;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;

import pl.edu.agh.niching.GraphHelper;

public class M1Evaluator extends MEvaluator{
	
	/*
	 * M1(x) = sin^6(5*pi*x)
	 * x = [0,1]
	 * */
	private static final String OUTPUT_FILENAME = "m1.log";
	private int candidateBitLength = 30;
	
	@Override
	public int getCandidateBitLength(){
		return candidateBitLength;
	}
	
	@Override
	public double getFitness(BitString candidate, List<? extends BitString> population) {
		double x = toDouble(candidate);
		
		return Math.pow( Math.sin( 5 * Math.PI * x ), 6);
	}
	
	
	public double toDouble(BitString candidate) {
		return candidate.toNumber().doubleValue() / Math.pow(2, candidateBitLength)-1;
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
        PrintStream outfile = new PrintStream(new File(OUTPUT_FILENAME)); 
        GraphHelper.printLabelledFunctionPlot(this, fitnessRange, outfile);
        outfile.close();
	}

}
