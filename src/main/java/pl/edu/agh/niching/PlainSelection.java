package pl.edu.agh.niching;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.selection.RankSelection;

import pl.edu.agh.niching.evaluators.MEvaluator;

public class PlainSelection extends RankSelection {
	protected MEvaluator evaluator;
	private PrintStream populationStream;
	private PrintStream peaksStream;
	
	public PlainSelection(MEvaluator evaluator) {
		super();
		this.evaluator = evaluator;
		this.populationStream = evaluator.getPopulationGifStream();
		this.peaksStream = evaluator.getPeaksStream();
	}
	
	@Override
	public <S> List<S> select(List<EvaluatedCandidate<S>> population, boolean naturalFitnessScores,
            int selectionSize, Random rng) {
		List<S> result = super.select(population, naturalFitnessScores, selectionSize, rng);
		List<EvaluatedCandidate<S>> selectedEvaluated = new ArrayList<EvaluatedCandidate<S>>();
		List<BitString> bs = new ArrayList<BitString>();
		for (EvaluatedCandidate<S> ec : population) {
			bs.add((BitString)ec.getCandidate());
		}
		
		for (S elem : result) {
			selectedEvaluated.add(new EvaluatedCandidate<S>(elem, evaluator.getFitness((BitString)elem, bs)));
		}
		
		GraphHelper.printPopulationData(population, population.size(), 0, populationStream);
		int peakMaintained = evaluator.peaksMaintained(bs);
		this.peaksStream.println(peakMaintained);
		
		return result;
	}
}
