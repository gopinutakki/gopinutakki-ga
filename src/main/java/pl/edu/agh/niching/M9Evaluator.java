package pl.edu.agh.niching;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class M9Evaluator implements FitnessEvaluator<BitString>{

	private List<BitString> G = new ArrayList<BitString>();
	
	public M9Evaluator() {
		super();
		G.add(new BitString("00000000"));
		G.add(new BitString("10001100"));
		G.add(new BitString("01001010"));
		
	}
	
	@Override
	public double getFitness(BitString candidate, List<? extends BitString> population) {
		double result = 0.0d;
		for (int i=0; i<candidate.getLength();){
			BitString subString = new BitString(8);
			for (int j=0; j< subString.getLength(); j++, i++){
				subString.setBit(j, candidate.getBit(i));
			}
			result += fmdG(subString);
		}
		return result;
	}

	double fmdG(BitString a){
		if (G.contains(a))
			return 10.0d;
		double minDistance = Double.MAX_VALUE;
		for (BitString g: G)
		{
			double distance = MathHelper.hammingDistance(a, g);
			if (distance < minDistance)
				minDistance = distance;
		}
		return minDistance;
	}
	
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
	 * Prints fitness function (M9) evaluation to stdout in order to plot it later.
	 * Generating all that BitStrings takes loads of memory and time.
	 */
	public static void plotFitnessFunction() {
		ArrayList<BitString> fitnessRange = new ArrayList<BitString>();
        String bits;
        String zeros = "000000000000000000000000";
        for (int i=0; i<256*256*256; i++) {
        	bits = Integer.toBinaryString(MathHelper.grayCode(i));
        	bits = zeros.substring(bits.length()) + bits;
        	fitnessRange.add(new BitString(bits));
        }
        GraphHelper.printFunctionPlot(new M9Evaluator(), fitnessRange, System.out);
	}

}
