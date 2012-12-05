package pl.edu.agh.niching;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.BitStringFactory;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import pl.edu.agh.niching.evaluators.M1Evaluator;
import pl.edu.agh.niching.evaluators.MEvaluator;

public class DeterministicCrowding {
	public static final String POPULATION_FILENAME = "population.log";

		public static void main(String[] args) {
			MEvaluator evaluator = new M1Evaluator();
			List<EvaluatedCandidate<BitString>> program = evolveProgram(evaluator);
			
			/* this should generate data for a graph
			 * to draw it, use `gnuplot res\plotresult.cfg`
			 */
			try {
				PrintStream populationStream = new PrintStream(new File(POPULATION_FILENAME));
				GraphHelper.printPopulationData(program, program.size(), 0, populationStream);
				populationStream.close();
				evaluator.plotFitnessFunction();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* end of graph data generation */
		}

	    /**
	     * Evolve a function to fit the specified data.
	     * @param data A map from input values to expected output values.
	     * @return A program that generates the correct outputs for all specified
	     * sets of input.
	     */
	    public static List<EvaluatedCandidate<BitString>> evolveProgram(MEvaluator evaluator) {
			// pseudokod deterministic crowding
	    	/*
			 * 
			 * if (d(p1 : c10) + d(p2 : c20)) <= (d(p1 : c20) + d(p2 : c10)) then
				begin
				if f (c10) > f (p1) then zamien p1 z c10
				if f (c20) > f (p2) then zamien p2 z c20
				end
				else
				begin
				if f (c20) > f (p1) then zamien p1 z c20
				if f (c10) > f (p2) then zamien p2 z c10
				end
			 */
	    	
	        List<EvolutionaryOperator<BitString>> operators = new ArrayList<EvolutionaryOperator<BitString>>(2);
	        
	        operators.add(new DeterministicCrowdingBitStringCrossover());
	        operators.add(new BitStringMutation(new Probability(0.01)));
	        //what is simplification, is it needed as in example
	        //operators.add(new Simplification());
	        

	        EvolutionEngine<BitString> engine = new GenerationalEvolutionEngine<BitString>(
        																		new BitStringFactory(evaluator.getCandidateBitLength()),
	                                                                             new EvolutionPipeline<BitString>(operators),
	                                                                             evaluator,
	                                                                             new DeterministicCrowdingSelectionStrategy(),
	                                                                             new MersenneTwisterRNG());
	        //engine.addEvolutionObserver(new EvolutionLogger<BitString>());
	        return engine.evolvePopulation(500, 0, new GenerationCount(1000)/* new TargetFitness(0d, evaluator.isNatural())*/);
	    }
	    
	
}
