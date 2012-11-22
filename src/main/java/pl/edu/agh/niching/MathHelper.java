package pl.edu.agh.niching;

import org.uncommons.maths.binary.BitString;

public class MathHelper {
	public static double hummingDistance(BitString a, BitString b){
		double distance = 0;
		for (int i=0; i<a.getLength(); i++){
			if (a.getBit(i) == b.getBit(i))
			distance += 1.0d;
		}
		return distance;
	}
}
