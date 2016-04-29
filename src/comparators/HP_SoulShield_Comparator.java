package comparators;

import java.util.Comparator;

import modell.SoulShield;

public class HP_SoulShield_Comparator implements Comparator<SoulShield> {

	@Override
	public int compare(SoulShield o1, SoulShield o2) {
		Integer a_crit = o1.getHp();
		Integer b_crit = o2.getHp();
		if (a_crit.intValue() == b_crit.intValue()){
			a_crit = o1.getAccuracy();
			b_crit = o2.getAccuracy();
		}
		return b_crit.compareTo(a_crit);
	}

}
