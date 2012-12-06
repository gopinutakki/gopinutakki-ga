package pl.edu.agh.niching.evaluators;

public class M2Evaluator extends M14Evaluator {

	@Override
	protected double function(double x) {
		return Math.pow(Math.E, -2*Math.log(2) * (x-0.1)/0.8 * (x-0.1)/0.8) * Math.pow( Math.sin( 5 * Math.PI * x ), 6);
	}
	@Override
	protected String getFileName() {
		return "m2.log";
	}

}
