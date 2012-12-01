package pl.edu.agh.niching;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class M1Evaluator implements FitnessEvaluator<BitString>{
	
	/*
	 * M1(x) = sin^6(5*pi*x)
	 * x = [0,1]
	 * */
	@Override
	public double getFitness(BitString candidate, List<? extends BitString> population) {
		double x = toDouble(candidate);
		
		return Math.pow( Math.sin( 5 * Math.PI * x ), 6);
	}
	
	
	private double toDouble(BitString candidate){
		int a=0;
		int i=0;
		for (; i< candidate.getLength();){
			if (candidate.getBit(i))
				a += Math.pow(2, i);
			i++;
		}
		return a / Math.pow(2, i+1)-1;
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
	

	public static void plotFitnessFunction() {
		
	}

}
