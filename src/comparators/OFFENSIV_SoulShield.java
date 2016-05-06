package comparators;

import java.util.Comparator;

import modell.SoulShield;

public class OFFENSIV_SoulShield implements Comparator<SoulShield> {

	@Override
	public int compare(SoulShield o1, SoulShield o2) {
		Integer a_crit = o1.getCrit() + o1.getAccuracy();
		Integer b_crit = o2.getCrit() + o2.getAccuracy();
		if (a_crit.intValue() == b_crit.intValue()) {
			a_crit = o1.getCrit();
			b_crit = o2.getCrit();
		}
		return b_crit.compareTo(a_crit);
	}

}
