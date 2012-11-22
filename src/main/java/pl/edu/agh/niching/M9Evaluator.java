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
	public boolean isNatural() {
		return true;
	}
	


}
