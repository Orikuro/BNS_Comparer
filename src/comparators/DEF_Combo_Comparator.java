package comparators;

import java.util.Comparator;
import modell.ComboSoul;

public class DEF_Combo_Comparator implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a = o1.getDef();
		Integer b = o2.getDef();
		if (a.intValue() == b.intValue()){
			a = o1.getAccu();
			b = o2.getAccu();
		}
		return b.compareTo(a);
	}
}