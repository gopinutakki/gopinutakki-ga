package pl.edu.agh.niching;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

public class DeterministicCrowdingSelectionStrategy implements SelectionStrategy<Object> {

	public <BitString> List<BitString> select(List<EvaluatedCandidate<BitString>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		
		
		//populacja zawiera dwa pokolenia, rodziców i dzieci, trzeba znaleźć rodziny 
		// powstale podczas krzyzowania i mutacji skladajace se z 2 rodziców i 2 dzieci
		List<BitString> selected = new ArrayList<BitString>();
		//List<EvaluatedCandidate<BitString>> processedIndividuals = new ArrayList<EvaluatedCandidate<BitString>>();
		// FIXME tu jest straszne BitStringowe zamieszanie
		
		for (Family<org.uncommons.maths.binary.BitString> family: PopulationRepository.population)
		{
			// trzeba ustalic wartosc fitness
			// wiec trzeba znaleźć w populacji osobniki
			// byc moze nie trzeba tego, bo osobniki byc moze sa posortowane w liscie wg fitness
			// wydajnościowo to moze byc slabe
			//EvaluatedCandidate<BitString> parent1 = GetEvaluatedCandidate(population, family.Parent1);
			//EvaluatedCandidate<BitString> parent2 = GetEvaluatedCandidate(population, family.Parent2);
			
			// dobieramy rodzicow i dzieci w pary gdzie rodzic i dziecko sa bardziej podobni do siebie
			if (MathHelper.hammingDistance(family.getParent1(), family.getChild1()) + MathHelper.hammingDistance(family.getParent2(), family.getChild2()) 
					<= MathHelper.hammingDistance(family.getParent1(), family.getChild2()) + MathHelper.hammingDistance(family.getParent2(), family.getChild1()))
			// w nowym pokoleniu zostaje bardziej przystosowany osobnik z pary
			{
				if (population.indexOf(family.getParent1())  < population.indexOf(family.getChild1()))
					selected.add((BitString) family.getParent1());
				else
					selected.add((BitString) family.getChild1());
				if (population.indexOf(family.getParent2())  < population.indexOf(family.getChild2()))
					selected.add((BitString) family.getParent2());
				else
					selected.add((BitString) family.getChild2());
			}
			else
			{
				if (population.indexOf(family.getParent1())  < population.indexOf(family.getChild2()))
					selected.add((BitString) family.getParent1());
				else
					selected.add((BitString) family.getChild2());
				if (population.indexOf(family.getParent2())  < population.indexOf(family.getChild1()))
					selected.add((BitString) family.getParent2());
				else
					selected.add((BitString) family.getChild1());
			}
		}
		
		PopulationRepository.population.clear();

		// first time there no crossing so nothing in repostiory, so wer returning all
		// TODO change
		if (selected.size() == 0)
			for (EvaluatedCandidate<BitString> cand :population){
				selected.add(cand.getCandidate());
			}
		//System.out.println( "Pop count: " + selected.size() );
		return selected;
	}
	

}
