package comparators;

import java.util.Comparator;

import modell.ComboSoul;

public class OFFENSIV_Combo implements Comparator<ComboSoul> {

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a_crit = o1.getOffensive();
		Integer b_crit = o2.getOffensive();
		if (a_crit.intValue() == b_crit.intValue()) {
			a_crit = o1.getCrit();
			b_crit = o2.getCrit();
		}
		return b_crit.compareTo(a_crit);
	}

}
