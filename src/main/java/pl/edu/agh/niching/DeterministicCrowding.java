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
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import pl.edu.agh.niching.evaluators.*;

public class DeterministicCrowding {
	public static final String POPULATION_FILENAME_PREFIX = "population";
	
		public static void main(String[] args) {
			evolve(new M1Evaluator());
			evolve(new M2Evaluator());
			/* end of graph data generation */
		}

		private static void evolve(MEvaluator evaluator) {
	    	PopulationRepository.population.clear();
			List<EvaluatedCandidate<BitString>> program = evolveProgram(evaluator);
			
			/* this should generate data for a graph
			 * to draw it, use `gnuplot res\plotresult.cfg`
			 */
			PrintStream populationStream = null;
			 //population at this point is twice big (), because contains 2 generations before selection
			try {
				populationStream = new PrintStream(new File(POPULATION_FILENAME_PREFIX + evaluator.getName()) + ".log");
				GraphHelper.printPopulationData(program, program.size(), 0, populationStream);
				//GraphHelper.printPopulationData(program, program.size(), 0, evaluator.getPopulationGifStream());
				evaluator.plotFitnessFunction();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(populationStream!=null) populationStream.close();
			}
			evaluator.getPopulationGifStream().close();
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

	    	
	        List<EvolutionaryOperator<BitString>> operators = new ArrayList<EvolutionaryOperator<BitString>>(1);
	        
	        operators.add(new DeterministicCrowdingBitStringCrossover());
	        operators.add(new DeterministicCrowdingBitStringMutation(new Probability(0.01)));
	        //what is simplification, is it needed as in example
	        //operators.add(new Simplification());
	        

	        EvolutionEngine<BitString> engine = new GenerationalEvolutionEngine<BitString>(
        																		new BitStringFactory(evaluator.getCandidateBitLength()),
	                                                                             new EvolutionPipeline<BitString>(operators),
	                                                                             evaluator,
	                                                                             new DeterministicCrowdingSelectionStrategy(evaluator.getPopulationGifStream()),
	                                                                             new MersenneTwisterRNG());
	        //engine.addEvolutionObserver(new EvolutionLogger<BitString>());
	        // add +1
	        return engine.evolvePopulation(500, 0, new GenerationCount(15+1));
	    }
	    
	
}
