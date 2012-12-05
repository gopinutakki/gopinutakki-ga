package pl.edu.agh.niching.evaluators;

public class M1Evaluator extends M14Evaluator {

	@Override
	protected double function(double x) {
		return Math.pow( Math.sin( 5 * Math.PI * x ), 6);
	}
	@Override
	protected String getFileName() {
		return "m1.log";
	}

}
