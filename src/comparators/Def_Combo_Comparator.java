package comparators;

import java.util.Comparator;
import modell.ComboSoul;

public class Def_Combo_Comparator implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a_crit = o1.getDef();
		Integer b_crit = o2.getDef();
		return b_crit.compareTo(a_crit);
	}
}