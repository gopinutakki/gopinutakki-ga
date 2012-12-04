package pl.edu.agh.niching;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 * This class will generate output data in Gnuplot format. 
 * 
 * @author Aleksandra Magura-Witkowska
 *
 */
public class GraphHelper {
	/**
	 * Prints to output data about final population in gnuplot format.
	 * 
	 * @param population	final population from {@code evolvePrgram()}
	 * @param bestCount 	number of best-fitted individuals to print
	 * @param randomCount	number of random individuals to be printed
	 * @param output		output stream (eg. a file or stdout)
	 */
	public static <T> void printPopulationData(List<EvaluatedCandidate<T>> population, int bestCount, int randomCount, PrintStream output) {
		bestCount = Math.min(bestCount, population.size());
		randomCount = Math.min(randomCount, population.size()-bestCount);
		Random rand = new Random(System.currentTimeMillis());
		int i;
		for (i=0; i<bestCount; i++) {
			// FIXME ten cast uroda nie grzeszy
			output.println(((BitString)population.get(i).getCandidate()).toNumber().toString() 
					+ " " + population.get(i).getFitness());
		}
		for (int j=0; j<randomCount; j++) {
			// FIXME ten tez nie
			output.println(((BitString)population.get(i=rand.nextInt(population.size())).getCandidate()).toNumber().toString()
					+ " " + population.get(i).getFitness());
		}
	}
	
	/**
	 * <p>This may be used to generate data required to plot a fitness function.</p>
	 * <p><strong>Warning:</strong> although it seems simple, using this method may be time- and memory-consuming, 
	 *    as the list given as {@code range} may be REALLY big.</p>
	 * <p>This method prints no X-axis values :(</p>
	 * 
	 * @param evaluator a {@link FitnessEvaluator} containing the desired fitness function
	 * @param range a list of arguments for the function to be plotted in
	 * @param output a {@link PrintStream} for the output (eg. {@code System.out}). 
	 */
	public static <T> void printSimpleFunctionPlot(FitnessEvaluator<T> evaluator, List<T> range, PrintStream output) {
		for (T element : range) {
			output.println(evaluator.getFitness(element, range));
		}
	}
	
	/**
	 * <p>This may be used to generate data required to plot a fitness function.</p>
	 * <p><strong>Warning:</strong> although it seems simple, using this method may be time- and memory-consuming, 
	 *    as the list given as {@code range} may be REALLY big.</p>
	 * <p>As this method prints out genotypes using {@code Object.toString()}, 
	 * it is recommended for small or numeric ranges only.</p>
	 * 
	 * @param evaluator a {@link FitnessEvaluator} containing the desired fitness function
	 * @param range a list of arguments for the function to be plotted in
	 * @param output a {@link PrintStream} for the output (eg. {@code System.out}).
	 */
	public static <T> void printLabelledFunctionPlot(FitnessEvaluator<T> evaluator, List<T> range, PrintStream output) {
		if (!range.isEmpty() && range.get(0) instanceof BitString) for (T element : range) {
			output.println(((BitString)element).toNumber().toString() 
					+ " " + evaluator.getFitness(element, range));
		} else for (T element : range) {
			output.println(element.toString() + " " + evaluator.getFitness(element, range));
		}
	}
}
