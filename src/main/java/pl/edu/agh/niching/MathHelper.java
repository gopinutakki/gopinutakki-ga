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
	
	/**
	 * <p>Transforms integer values into Gray code (reflected binary code).</p> 
	 * <p>This method will be used to order elements according to their Hamming distance
	 * (ie every 2 consecutive values differ by only 1 bit).</p>
	 * <p>It operates on integers, because it is easier that way (and we may need it somewhere else).</p> 
	 * 
	 * @param number the number whose Gray code we want
	 * @return graycoded value of {@code number}
	 */
	public static int grayCode(int number) {
		return ((number >> 1) ^ number);
	}
}
