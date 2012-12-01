package pl.edu.agh.niching.evaluators;


import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public abstract class MEvaluator implements FitnessEvaluator<BitString>{
	public abstract int getCadidateBitLenght();
	
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
}
