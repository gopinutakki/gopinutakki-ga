package pl.edu.agh.niching;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

public class DeterministicCrowdingSelectionStrategy implements
	SelectionStrategy<Object> {

	public <BitString> List<BitString> select(List<EvaluatedCandidate<BitString>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		
		
		//populacja zawiera dwa pokolenia, rodziców i dzieci, trzeba znaleźć rodziny 
		// powstale podczas krzyzowania i mutacji skladajace se z 2 rodziców i 2 dzieci
		List<BitString> selected = new ArrayList<BitString>();
		//List<EvaluatedCandidate<BitString>> processedIndividuals = new ArrayList<EvaluatedCandidate<BitString>>();
		for (Family<org.uncommons.maths.binary.BitString> family: PopulationRepository.population)
		{
			// trzeba ustalic wartosc fitness
			// wiec trzeba znaleźć w populacji osobniki
			// byc moze nie trzeba tego, bo osobniki byc moze sa posortowane w liscie wg fintess
			// performanocwo to moze byc slabe
			//EvaluatedCandidate<BitString> parent1 = GetEvaluatedCandidate(population, family.Parent1);
			//EvaluatedCandidate<BitString> parent2 = GetEvaluatedCandidate(population, family.Parent2);
			
			// dobieramy rodzicow i dzieci w pary gdzie rodzic i dziecko sa bardziej podobni do siebie
			if (MathHelper.hummingDistance(family.Parent1, family.Child1) + MathHelper.hummingDistance(family.Parent2, family.Child2) 
					<= MathHelper.hummingDistance(family.Parent1, family.Child2) + MathHelper.hummingDistance(family.Parent2, family.Child1))
			// w nowym pokoleniu zostaje bardziej przystosowany osobnik z pary
			{
				if (population.indexOf(family.Parent1)  >= population.indexOf(family.Child1))
					selected.add((BitString) family.Parent1);
				else
					selected.add((BitString) family.Child1);
				if (population.indexOf(family.Parent2)  >= population.indexOf(family.Child2))
					selected.add((BitString) family.Parent2);
				else
					selected.add((BitString) family.Child2);
			}
			else
			{
				if (population.indexOf(family.Parent1)  >= population.indexOf(family.Child2))
					selected.add((BitString) family.Parent1);
				else
					selected.add((BitString) family.Child2);
				if (population.indexOf(family.Parent2)  >= population.indexOf(family.Child1))
					selected.add((BitString) family.Parent2);
				else
					selected.add((BitString) family.Child1);
			}
		}
		
		PopulationRepository.population.clear();
		return selected;
	}
	
	private <BitString> EvaluatedCandidate<BitString> GetEvaluatedCandidate(List<EvaluatedCandidate<BitString>> population, BitString parent1){
		for (EvaluatedCandidate<BitString> one: population){
			if (one.getCandidate().equals(parent1))
				return one;
		}
		return null;
	}
	

}
