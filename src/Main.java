import java.util.Comparator;

import comparators.Crit_SoulShield_Comparator;
import inport.CSVImport;
import logic.ThreadStarter;
import modell.SoulShield;
import modell.SoulShields;

public class Main {
	public static final String VERSION = "0.1 - 07.02.16 - by Nekuro/Orikuro";

	private static enum Sort {
		crit, hp, critdef
	};

	public static void main(String[] args) {
		// Import Shields
		SoulShields shields = CSVImport.importSoulShields();



		// sortierung nach crit, critdef, hp
		Comparator<? super SoulShield> comp = new Crit_SoulShield_Comparator();
		shields.sort(comp);

		// minimum stats
		new ThreadStarter(args, shields);
		
//		new ThreadStarter(args, CPUS, results, price, ATK, MATK, COMBOSORT,
//				items, NOINFO, nocsv);

	}

}
