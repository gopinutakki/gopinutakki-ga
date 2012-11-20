package pl.edu.agh.niching;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

public class DeterministicCrowdingSelectionStrategy implements
	SelectionStrategy<Object> {

	public <S> List<S> select(List<EvaluatedCandidate<S>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		
		
		//populacja zawiera dwa pokolenia, rodziców i dzieci, trzeba znaleźć rodziny 
		// powstale podczas krzyzowania i mutacji skladajace se z 2 rodziców i 2 dzieci
		List<S> selected = new List<S>();
		List<EvaluatedCandidate<S>> processedIndividuals = new List<EvaluatedCandidate<S>>();
		for (EvaluatedCandidate<S> individual: population)
		{
			if (processedIndividuals.contains(individual))
				continue;
			EvaluatedCandidate<S> parent1;
			EvaluatedCandidate<S> parent2;
			EvaluatedCandidate<S> child1;
			EvaluatedCandidate<S> child2;
			if (individual.hasChildrens())
			{
				parent1 = individual;
				parent2 = individual.getSecondParent();
				child1 = individual.getChild1();
				child2 = individual.getChild2();
				processedIndividuals.add(parent1);
				processedIndividuals.add(parent2);
				processedIndividuals.add(child1);
				processedIndividuals.add(child2);
			}
			else
				continue;
			
			// dobieramy rodzicow i dzieci w pary gdzie rodzic i dziecko sa bardziej podobni do siebie
			if (parent1.getSimilarity(child1) + parent2.getSimilarity(child2) 
					<= parent1.getSimilarity(child2) + parent2.getSimilarity(child1))
			// w nowym pokoleniu zostaje bardziej przystosowany osobnik z pary
			{
				if (parent1.compareTo(child1) >= 0)
					selected.add(parent1);
				else
					selected.add(child1);
				if (parent2.compareTo(child2) >= 0)
					selected.add(parent2);
				else
					selected.add(child2);
			}
			else
			{
				if (parent1.compareTo(child2) >= 0)
					selected.add(parent1);
				else
					selected.add(child2);
				if (parent2.compareTo(child1) >= 0)
					selected.add(parent2);
				else
					selected.add(child1);
			}
		}
		return selected;
	}

}
