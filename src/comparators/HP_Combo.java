package comparators;

import java.util.Comparator;

import modell.ComboSoul;

public class HP_Combo implements Comparator<ComboSoul>{

	@Override
	public int compare(ComboSoul o1, ComboSoul o2) {
		Integer a = o1.getHp();
		Integer b = o2.getHp();
		if (a.intValue() == b.intValue()){
			a = o1.calcDefstats();
			b = o2.calcDefstats();
		}
		return b.compareTo(a);
	}

}
