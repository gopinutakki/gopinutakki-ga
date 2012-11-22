package pl.edu.agh.niching;

import org.uncommons.maths.binary.BitString;

public class MathHelper {
	/**
	 * An implementation of Hamming distance between bit strings a and b,  
	 * assuming the strings are of equal length.
	 * 
	 * @param a	first bit string to be compared
	 * @param b	second bit string to be compared
	 * 
	 * @return number of positions at which the bit strings are different
	 */
	public static double hammingDistance(BitString a, BitString b){
		double distance = 0;
		for (int i=0; i<a.getLength(); i++){
			if (a.getBit(i) != b.getBit(i)) // increase when they're different, not the same, I guess
				distance += 1.0d;
		}
		return distance;
	}
}
