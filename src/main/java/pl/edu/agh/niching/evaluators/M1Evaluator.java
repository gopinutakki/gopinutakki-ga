package pl.edu.agh.niching.evaluators;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class M1Evaluator extends MEvaluator{
	
	/*
	 * M1(x) = sin^6(5*pi*x)
	 * x = [0,1]
	 * */
	
	private int cadidateBitLenght = 30;
	
	public int getCadidateBitLenght(){
		return cadidateBitLenght;
	}
	
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

	

	public static void plotFitnessFunction() {
		
	}

}
