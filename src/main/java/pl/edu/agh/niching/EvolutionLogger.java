package pl.edu.agh.niching;

import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;

public class EvolutionLogger<T> implements EvolutionObserver<T>
{
    public void populationUpdate(PopulationData<? extends T> data)
    {
        System.out.println("Generation: " + data.getGenerationNumber() + ", \tbest: " + data.getBestCandidateFitness()
        		+ ", \tmean: " + data.getMeanFitness() + ", \tstdev: " + data.getFitnessStandardDeviation());
    }
}
