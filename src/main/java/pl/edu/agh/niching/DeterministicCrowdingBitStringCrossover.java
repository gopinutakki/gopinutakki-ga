package pl.edu.agh.niching;

import java.util.List;
import java.util.Random;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.examples.geneticprogramming.Node;
import org.uncommons.watchmaker.examples.geneticprogramming.TreeCrossover;
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;


public class DeterministicCrowdingBitStringCrossover extends BitStringCrossover {

	@Override
	protected List<BitString> mate(BitString parent1, BitString parent2, int numberOfCrossoverPoints, Random rng) {
		// adding parents to next generation after crossing and setting the family
		List<BitString> childerns =  super.mate(parent1, parent2, numberOfCrossoverPoints, rng);
		
		//todo: setters getters etc.
		Family family= new Family();
		family.setParent1(parent1);
		family.Parent2 = parent2;
		family.Child1 = childerns.get(0);
		family.Child1 = childerns.get(0);
		PopulationRepository.population.add(family);
		
		childerns.add(parent1);
		childerns.add(parent2);
		return childerns;
	};


}
