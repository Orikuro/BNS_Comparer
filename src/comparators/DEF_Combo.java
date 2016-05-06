package comparators;

import java.util.Comparator;
import modell.ComboSoul;

public class DEF_Combo implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a = o1.getDef();
		Integer b = o2.getDef();
		if (a.intValue() == b.intValue()){
			a = o1.calcDefstats();
			b = o2.calcDefstats();
		}
		return b.compareTo(a);
	}
}