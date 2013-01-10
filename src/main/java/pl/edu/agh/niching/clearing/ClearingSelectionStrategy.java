package pl.edu.agh.niching.clearing;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import org.uncommons.maths.binary.BitString;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

import pl.edu.agh.niching.GraphHelper;

public class ClearingSelectionStrategy implements SelectionStrategy<Object> {
	long clearingRadius = 500000;
	private PrintStream populationStream;
	/* Pseudokod:
	 * 
	 * matingPool := population;
	 * Sort matingPool in descending order of fitness;
	 * for i := 1 to |matingPool| do
	 *   mi := matingPool[i];
	 *   for j := i + 1 to |matingPool| do
	 *     mj := matingPool[j];
	 *     if distance(mi;mj) < sigma_clear then remove mj from matingPool;
	 *   end
	 * end
	 * return matingPool;
	 */
	
	public ClearingSelectionStrategy(PrintStream populationStream){
		super();
		this.populationStream = populationStream;
	}
	
	@Override
	public <T> List<T> select(List<EvaluatedCandidate<T>> population, boolean naturalFitnessScores, int selectionSize, Random rng) {
		// clear the mating pool according to the algorithm above
		List<T> selected = new ArrayList<T>();
		List<EvaluatedCandidate<T>> selectedEvaluated = new ArrayList<EvaluatedCandidate<T>>();
		List<EvaluatedCandidate<T>> populationLeft = new CopyOnWriteArrayList<EvaluatedCandidate<T>>(population);
		
		while(selected.size()<selectionSize && !populationLeft.isEmpty()) {
			EvaluatedCandidate<T> best = populationLeft.get(0); 
			selected.add(best.getCandidate());
			selectedEvaluated.add(best);
			populationLeft.remove(0);
			for (EvaluatedCandidate<T> elem : populationLeft) {
				if (Math.abs(((BitString)best.getCandidate()).toNumber().longValue() - ((BitString)elem.getCandidate()).toNumber().longValue()) < clearingRadius)
					populationLeft.remove(elem);
			}
		}
		
		if (selected.isEmpty()) for (EvaluatedCandidate<T> ec : population)
			selected.add((T)ec.getCandidate());
		
		// fill the rest of selected pool
		int i=0;
		while (selected.size()<selectionSize) {
			selected.add(selected.get(i));
		}
		
		GraphHelper.printPopulationData(selectedEvaluated, selectedEvaluated.size(), 0, populationStream);
		
		return selected;
	}
	
	public void setClearingRadius(long newRadius) {
		this.clearingRadius = newRadius;
	}

}
