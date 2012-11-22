package pl.edu.agh.niching;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;

//ugly way to store parents and childrens relations
//cant extend final bitString, i would have to reimplement strategies, mutations, crossovet etc.
public class PopulationRepository {
	public static List<Family<BitString>> population = new ArrayList<Family<BitString>>();
}
