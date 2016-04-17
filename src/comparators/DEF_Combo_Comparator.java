package comparators;

import java.util.Comparator;
import modell.ComboSoul;

public class DEF_Combo_Comparator implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a = o1.getDef();
		Integer b = o2.getDef();
		return b.compareTo(a);
	}
}