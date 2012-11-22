package pl.edu.agh.niching;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;

// FIXME
//ugly way to store parents and childrens relations
//cant extend final bitString, i would have to reimplement strategies, mutations, crossovet etc.
// as far as I see, this is just a list of Families - if so, does it really need a class?
// in case we really need to tamper with BitString, maybe use delegation?
// for now, I just can't see the point
public class PopulationRepository {
	public static List<Family<BitString>> population = new ArrayList<Family<BitString>>();
}
