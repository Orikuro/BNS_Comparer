package comparators;

import java.util.Comparator;

import modell.SoulShield;

public class DEFFENSIV_SoulShield implements Comparator<SoulShield> {

	@Override
	public int compare(SoulShield o1, SoulShield o2) {
		Integer a_crit = o1.getDef() + o1.getCritdef() + o1.getBlock() + o1.getEvasion() + o1.getHp() / 10;
		Integer b_crit = o2.getDef() + o2.getCritdef() + o2.getBlock() + o2.getEvasion() + o2.getHp() / 10;
		if (a_crit.intValue() == b_crit.intValue()) {
			a_crit = o1.getHp();
			b_crit = o2.getHp();
		}
		return b_crit.compareTo(a_crit);
	}

}
