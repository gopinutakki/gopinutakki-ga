package pl.edu.agh.niching;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.binary.BitString;


public class PopulationRepository {
	public static List<Family<BitString>> population = new ArrayList<Family<BitString>>();
	

	public static void swap(List<BitString> selectedCandidates, List<BitString> mutatedList) {
		int i =0;
		for (BitString selectedCdanidate: selectedCandidates){
			for (Family<BitString> family: population){
				if (family.getChild1().equals(selectedCdanidate))
					family.setChild1(mutatedList.get(i));
				else if (family.getChild2().equals(selectedCdanidate))
					family.setChild2(mutatedList.get(i));
				else if (family.getParent1().equals(selectedCdanidate))
					family.setParent1(mutatedList.get(i));
				else if (family.getParent2().equals(selectedCdanidate))
					family.setParent2(mutatedList.get(i));
			}
			i++;
		 }
		
	}
}
