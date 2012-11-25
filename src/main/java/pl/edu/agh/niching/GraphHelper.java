package pl.edu.agh.niching;

import java.io.PrintStream;
import java.util.List;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 * This class will generate output data in Gnuplot format. 
 * 
 * @author Aleksandra Magura-Witkowska
 *
 */
public class GraphHelper {
	
	public static void printPopulationData(List<EvaluatedCandidate<?>> population, int bestCount, int randomCount, PrintStream output) {
		// TODO
	}
	
	/**
	 * <p>This may be used to generate data required to plot a fitness function.</p>
	 * <p><strong>Warning:</strong> although it seems simple, using this method may be time- and memory-consuming, 
	 *    as the list given as {@code range} may be REALLY big.</p>
	 * 
	 * @param evaluator a {@link FitnessEvaluator} containing the desired fitness function
	 * @param range a list of arguments for the function to be plotted in
	 * @param output a {@link PrintStream} for the output (eg. {@code System.out}).
	 */
	public static <T> void printFunctionPlot(FitnessEvaluator<T> evaluator, List<T> range, PrintStream output) {
		for(T element : range) {
			output.println(element.toString() + " " + evaluator.getFitness(element, range));
		}
	}
}
