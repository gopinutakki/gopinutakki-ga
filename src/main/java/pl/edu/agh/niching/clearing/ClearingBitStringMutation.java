package pl.edu.agh.niching.clearing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;

public class ClearingBitStringMutation extends BitStringMutation {
	
	private Probability probability;

	public ClearingBitStringMutation(Probability probability) {
		super(probability);
		this.probability = probability;
	}
	
	/**
	 * Mutating BitStrings in a way that makes higher bits change more probable than lower bits.
	 * It should make the candidates more scattered.
	 */
	public List<BitString> apply(List<BitString> selectedCandidates, Random rng) {
		List<BitString> mutated = new ArrayList<BitString>();
		BitString tmp;
		int i;
		for (BitString can : selectedCandidates) {
			tmp = can.clone();
			i=2;
			while (rng.nextDouble() < this.probability.doubleValue() && i>=0) {
				tmp.flipBit(rng.nextInt(tmp.getLength()/3)+tmp.getLength()*i--/3);
			}
			
			mutated.add(tmp);
		}
		return mutated;
    }

}
