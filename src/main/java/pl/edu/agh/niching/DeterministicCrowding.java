package pl.edu.agh.niching;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.examples.EvolutionLogger;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.BitStringFactory;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

public class DeterministicCrowding {


	    public static void main(String[] args)
	    {
	        List<EvaluatedCandidate<BitString>> program = evolveProgram();
	        //System.out.println(program.print());
	    }


	    /**
	     * Evolve a function to fit the specified data.
	     * @param data A map from input values to expected output values.
	     * @return A program that generates the correct outputs for all specified
	     * sets of input.
	     */
	    public static List<EvaluatedCandidate<BitString>> evolveProgram()
	    {
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
	        
	        // changed order
	        operators.add(new DeterministicCrowdingBitStringCrossover());
	        operators.add(new BitStringMutation(new Probability(0.01)));
	        //what is simplification, is it needed as in example
	        //operators.add(new Simplification());
	        
	        M9Evaluator evaluator = new M9Evaluator();
	        EvolutionEngine<BitString> engine = new GenerationalEvolutionEngine<BitString>(
        																		new BitStringFactory(32),
	                                                                             new EvolutionPipeline<BitString>(operators),
	                                                                             evaluator,
	                                                                             new DeterministicCrowdingSelectionStrategy(),
	                                                                             new MersenneTwisterRNG());
	        engine.addEvolutionObserver(new EvolutionLogger<BitString>());
	        return engine.evolvePopulation(1000, 0, new GenerationCount(10000)/* new TargetFitness(0d, evaluator.isNatural())*/);
	    }
}
