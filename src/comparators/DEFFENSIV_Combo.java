package comparators;

import java.util.Comparator;

import modell.ComboSoul;

public class DEFFENSIV_Combo implements Comparator<ComboSoul> {

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a = o1.getDeffensive();
		Integer b = o2.getDeffensive();
		if (a.intValue() == b.intValue()) {
			a = o1.getDef();
			b = o2.getDef();
		}
		return b.compareTo(a);
	}

}
