package comparators;

import java.util.Comparator;

import modell.ComboSoul;

public class CRIT_Combo_Comparator implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a_crit = o1.getCrit();
		Integer b_crit = o2.getCrit();	
		if (a_crit.intValue() == b_crit.intValue()){
			a_crit = o1.getAccu();
			b_crit = o2.getAccu();
		}		
		return b_crit.compareTo(a_crit);
	}

}
