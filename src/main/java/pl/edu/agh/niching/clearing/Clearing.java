package pl.edu.agh.niching.clearing;

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

public class Clearing {
	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		evolve(new M1Evaluator());
		evolve(new M2Evaluator());
		evolve(new M3Evaluator());
		evolve(new M4Evaluator());
	}
	
	/**
	 * 
	 * @param evaluator
	 */
	private static void evolve(MEvaluator evaluator) {
		// TODO
	}
	
	/**
     * Evolve a function to fit the specified data.
     * @param data A map from input values to expected output values.
     * @return A program that generates the correct outputs for all specified
     * sets of input.
     */
    public static List<EvaluatedCandidate<BitString>> evolveProgram(MEvaluator evaluator) {
    	
    	
    	List<EvolutionaryOperator<BitString>> operators = new ArrayList<EvolutionaryOperator<BitString>>(1);
        
        operators.add(new BitStringCrossover()); //XXX
        operators.add(new BitStringMutation(new Probability(0.01))); //XXX
        

        EvolutionEngine<BitString> engine = new GenerationalEvolutionEngine<BitString>(
    																		new BitStringFactory(evaluator.getCandidateBitLength()),
                                                                             new EvolutionPipeline<BitString>(operators),
                                                                             evaluator,
                                                                             new ClearingSelectionStrategy(evaluator.getPopulationGifStream()),
                                                                             new MersenneTwisterRNG());
        
        return engine.evolvePopulation(500, 0, new GenerationCount(16));
    	// TODO
    }
}
