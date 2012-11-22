package pl.edu.agh.niching;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.ContinuousUniformGenerator;
import org.uncommons.maths.random.Probability;

public class ProbabilityNumberGenerator implements NumberGenerator<Probability> {

	ContinuousUniformGenerator doubleGenerator =  new ContinuousUniformGenerator(0, 1, new Random());
	public Probability nextValue() {
		return new Probability(doubleGenerator.nextValue());
	}

}
