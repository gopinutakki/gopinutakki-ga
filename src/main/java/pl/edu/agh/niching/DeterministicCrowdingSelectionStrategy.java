package pl.edu.agh.niching;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

import pl.edu.agh.niching.evaluators.MEvaluator;

public class DeterministicCrowdingSelectionStrategy implements SelectionStrategy<Object> {
	PrintStream populationStream;
	private MEvaluator evaluator;
	private PrintStream peaksStream;
	public DeterministicCrowdingSelectionStrategy(MEvaluator evaluator){
		super();
		this.evaluator = evaluator;
		this.populationStream = evaluator.getPopulationGifStream();
		this.peaksStream = evaluator.getPeaksStream();

	}
	static int i = 0;
	@SuppressWarnings("unchecked")
	public <T> List<T> select(List<EvaluatedCandidate<T>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		
		i++;
		//populacja zawiera dwa pokolenia, rodziców i dzieci, trzeba znaleźć rodziny 
		// powstale podczas krzyzowania i mutacji skladajace se z 2 rodziców i 2 dzieci
		List<T> selected = new ArrayList<T>();
		List<EvaluatedCandidate<T>> selectedEvaluated = new ArrayList<EvaluatedCandidate<T>>();
		
		for (Family<org.uncommons.maths.binary.BitString> family: PopulationRepository.population){
			// trzeba ustalic wartosc fitness
			// wiec trzeba znaleźć w populacji osobniki
			// byc moze nie trzeba tego, bo osobniki byc moze sa posortowane w liscie wg fitness
			// wydajnościowo to moze byc slabe
			//EvaluatedCandidate<BitString> parent1 = GetEvaluatedCandidate(population, family.Parent1);
			//EvaluatedCandidate<BitString> parent2 = GetEvaluatedCandidate(population, family.Parent2);
			
			// dobieramy rodzicow i dzieci w pary gdzie rodzic i dziecko sa bardziej podobni do siebie
			EvaluatedCandidate<T> parent1 = GetEvaluatedCandidate(population, family.getParent1());
			EvaluatedCandidate<T> parent2 = GetEvaluatedCandidate(population, family.getParent2());
			EvaluatedCandidate<T> child1 = GetEvaluatedCandidate(population, family.getChild1());
			EvaluatedCandidate<T> child2 = GetEvaluatedCandidate(population, family.getChild2());

			// dobieramy rodzicow i dzieci w pary gdzie rodzic i dziecko sa bardziej podobni do siebie
			if (MathHelper.hammingDistance(family.getParent1(), family.getChild1()) + MathHelper.hammingDistance(family.getParent2(), family.getChild2()) 
					<= MathHelper.hammingDistance(family.getParent1(), family.getChild2()) + MathHelper.hammingDistance(family.getParent2(), family.getChild1()))
			// w nowym pokoleniu zostaje bardziej przystosowany osobnik z pary
			{
				if (parent1.getFitness()  > child1.getFitness()){
					selected.add((T) family.getParent1());
					selectedEvaluated.add(parent1);
				}
				else{
					selected.add((T) family.getChild1());
					selectedEvaluated.add(child1);
				}
				if (parent2.getFitness()  > child2.getFitness()){
					selected.add((T) family.getParent2());
					selectedEvaluated.add(parent2);
				}
				else{
					selected.add((T) family.getChild2());
					selectedEvaluated.add(child2);
				}
			}
			else
			{
				if (parent1.getFitness()  > child2.getFitness()){
					selected.add((T) family.getParent1());
					selectedEvaluated.add(parent1);
				}
				else{
					selected.add((T) family.getChild2());
					selectedEvaluated.add(child2);
				}
				if (parent2.getFitness()  > child1.getFitness()){
					selected.add((T) family.getParent2());
					selectedEvaluated.add(parent2);
				}
				else{
					selected.add((T) family.getChild1());
					selectedEvaluated.add(child1);
				}
			}
			
		}
		
		
		int peakMaintened = evaluator.peaksMaintained((List<org.uncommons.maths.binary.BitString>) selected);
		this.peaksStream.println(peakMaintened);
		GraphHelper.printPopulationData(selectedEvaluated, selectedEvaluated.size(), 0, populationStream);
		//populationStream.println(" i:" + i +"selectedEvaluated: " + selectedEvaluated.size() + "selected:" + selected.size() + "population"+ population.size());
		PopulationRepository.population.clear();

		// first time there no crossing so nothing in repostiory, so wer returning all
		// TODO change
		if (selected.size() == 0){
			GraphHelper.printPopulationData(population, population.size(), 0, populationStream);
			for (EvaluatedCandidate<T> cand :population){
				selected.add(cand.getCandidate());
			}
		}
		//System.out.println( "Pop count: " + selected.size() );
		return selected;
	}
	
	//FIXME: czasem nie ma w poplacji tego co w family, dlaczego?
	private <T> EvaluatedCandidate<T> GetEvaluatedCandidate(List<EvaluatedCandidate<T>> population, org.uncommons.maths.binary.BitString one){

			for (EvaluatedCandidate<T> evaluatedCandidate: population){
				if (evaluatedCandidate.getCandidate().equals(one))//to equlas
				{
					return evaluatedCandidate;

				}
			}

		return null;
	}
}
