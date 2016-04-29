package comparators;

import java.util.Comparator;

import modell.ComboSoul;

public class HP_Combo_Comparator implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a = o1.getHp();
		Integer b = o2.getHp();
		if (a.intValue() == b.intValue()){
			a = o1.getAccu();
			b = o2.getAccu();
		}
		return b.compareTo(a);
	}

}
