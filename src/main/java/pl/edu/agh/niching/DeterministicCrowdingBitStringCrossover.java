package pl.edu.agh.niching;

import java.util.List;
import java.util.Random;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;


public class DeterministicCrowdingBitStringCrossover extends BitStringCrossover {

	@Override
	protected List<BitString> mate(BitString parent1, BitString parent2, int numberOfCrossoverPoints, Random rng) {
		// adding parents to next generation after crossing and setting the family
		List<BitString> childerns =  super.mate(parent1, parent2, numberOfCrossoverPoints, rng);
		
		Family<BitString> family= new Family<BitString>();
		family.setParent1(parent1);
		family.setParent2(parent2);
		family.setChild1(childerns.get(0));
		family.setChild2(childerns.get(1));
		PopulationRepository.population.add(family);
		
		childerns.add(parent1);
		childerns.add(parent2);
		return childerns;
	};


}
