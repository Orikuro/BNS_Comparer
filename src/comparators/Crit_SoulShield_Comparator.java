package comparators;

import java.util.Comparator;

import modell.SoulShield;

public class Crit_SoulShield_Comparator implements Comparator<SoulShield>{

	@Override
	public int compare(SoulShield o1, SoulShield o2) {
		Integer a_crit = o1.getCrit();
		Integer b_crit = o2.getCrit();
		return b_crit.compareTo(a_crit);
	}

}
