package pl.edu.agh.niching.evaluators;

public class M4Evaluator extends M14Evaluator {

	@Override
	protected double function(double x) {
		return Math.pow(Math.E, -2 * Math.log(2) * (x-0.08)/0.854 * (x-0.08)/0.854) * Math.pow( Math.sin( 5 * Math.PI * Math.pow(x, 0.75) - .05 ), 6);
	}

	@Override
	public String getName() {
		return "M4";
	}

}
