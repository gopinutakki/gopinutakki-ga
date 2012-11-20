package pl.edu.agh.niching;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.examples.geneticprogramming.Node;
import org.uncommons.watchmaker.examples.geneticprogramming.TreeCrossover;


public class DeterministicCrowdingTreeCrossover extends TreeCrossover {

	// adding parents to next generation after crossing and setting the family
	// so it can be finded during selection
	@Override
	protected List<Node> mate(Node parent1, Node parent2,
			int numberOfCrossoverPoints, Random rng) {
		// TODO Auto-generated method stub
		// TreeCrossover.mate produce only crossed childrens, leaving parents (i think, must check this)
		List<Node> childerns =  super.mate(parent1, parent2, numberOfCrossoverPoints, rng);
		
		parent1.setOtherParent(parent2);
		parent2.setOtherParent(parent1);
		parent1.setChildrens(childrens);
		parent2.setChildrens(childrens);
		childerns.add(parent1);
		childerns.add(parent2);
		return childerns;
	}
}
