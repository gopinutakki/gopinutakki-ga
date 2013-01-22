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
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import pl.edu.agh.niching.evaluators.M1Evaluator;
import pl.edu.agh.niching.evaluators.M2Evaluator;
import pl.edu.agh.niching.evaluators.M3Evaluator;
import pl.edu.agh.niching.evaluators.M4Evaluator;
import pl.edu.agh.niching.evaluators.MEvaluator;

/**
 * Plain genetic algorithm setting with rank selection and no niching.
 * (Warning: draws graphs as deterministic crowding!)
 * 
 * @author Aleksandra Magura-Witkowska
 */
public class PlainEvolution {
	public static final String POPULATION_FILENAME_PREFIX = "population";
	
	public static void main(String[] args) {
		System.out.print("Evolution in progress... ");
		evolve(new M1Evaluator());
		evolve(new M2Evaluator());
		evolve(new M3Evaluator());
		evolve(new M4Evaluator());
		
		try {
			System.out.print("OK\nGenerating graphs... ");
			Process graphDrawing = Runtime.getRuntime().exec("gnuplot ./res/plotresult.cfg");
			Process graphDrawing2 = Runtime.getRuntime().exec("gnuplot ./res/plotGifs.cfg");
			Process graphDrawing3 = Runtime.getRuntime().exec("gnuplot ./res/plotPeaks.cfg");
			graphDrawing.waitFor();
			graphDrawing2.waitFor();
			graphDrawing3.waitFor();
			System.out.println("OK\nDone.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param evaluator
	 */
	private static void evolve(MEvaluator evaluator) {
		PopulationRepository.population.clear();
		List<EvaluatedCandidate<BitString>> program = evolveProgram(evaluator);
		
		/* this should generate data for a graph
		 * to draw it, use `gnuplot res\plotresult.cfg`
		 */
		PrintStream populationStream = null;
		try {
			populationStream = new PrintStream(new File(POPULATION_FILENAME_PREFIX + evaluator.getName()) + ".log");
			GraphHelper.printPopulationData(program, program.size(), 0, populationStream);
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
    	List<EvolutionaryOperator<BitString>> operators = new ArrayList<EvolutionaryOperator<BitString>>(2);
        
        operators.add(new BitStringCrossover());
        operators.add(new BitStringMutation(new Probability(0.01)));

        EvolutionEngine<BitString> engine = new GenerationalEvolutionEngine<BitString>(
    																		new BitStringFactory(evaluator.getCandidateBitLength()),
                                                                             new EvolutionPipeline<BitString>(operators),
                                                                             evaluator,
                                                                             new PlainSelection(evaluator),
                                                                             new MersenneTwisterRNG());
        
        return engine.evolvePopulation(500, 00, new GenerationCount(101));
    }
}
