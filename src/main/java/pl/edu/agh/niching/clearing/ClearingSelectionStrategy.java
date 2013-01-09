package pl.edu.agh.niching.clearing;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

public class ClearingSelectionStrategy implements SelectionStrategy<Object> {
	PrintStream populationStream;
	public ClearingSelectionStrategy(PrintStream populationStream){
		super();
		this.populationStream = populationStream;

	}
	
	@Override
	public <S> List<S> select(List<EvaluatedCandidate<S>> population, boolean naturalFitnessScores, int selectionSize, Random rng) {
		// TODO Auto-generated method stub
		return null;
	}

}
