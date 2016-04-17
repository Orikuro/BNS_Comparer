package comparators;

import java.util.Comparator;

import modell.ComboSoul;

public class CRIT_Combo_Comparator implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a_crit = o1.getCrit();
		Integer b_crit = o2.getCrit();
		return b_crit.compareTo(a_crit);
	}

}
