package pl.edu.agh.niching;

import java.util.List;
import java.util.Random;

import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;

public class DeterministicCrowdingBitStringMutation extends BitStringMutation {
	
	public DeterministicCrowdingBitStringMutation(Probability probability) {
		super(probability);
	}

	public List<BitString> apply(List<BitString> selectedCandidates, Random rng)
    {
		List<BitString> mutated =  super.apply(selectedCandidates, rng);
		PopulationRepository.swap(selectedCandidates, mutated);
		return mutated;
    }

}
