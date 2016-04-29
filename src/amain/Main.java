package amain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import comparators.*;
import inport.CSVImport;
import logic.ThreadStarter;
import modell.*;

public class Main {
	public static final String VERSION = "1.2 - 30.04.16 - by Orikuro";

	public static Comparator<ComboSoul> COMBO_COMPARATOR = new CRIT_Combo_Comparator();
	public static Comparator<SoulShield> SOUL_COMPARATOR = new CRIT_SoulShield_Comparator();

	public static SoulSet FIRST_SET = new SoulSet("Leer");
	public static SoulSet SECOND_SET = new SoulSet("Leer");

	public static enum Sort {
		crit, hp, def, cdef
	};

	// Main-Infos
	@Option(name = "-sets", required = true, usage = "sets to use, use ; for more than one. eg. \"Blackram; Mushin\"")
	public static String SETS = "";

	// sort
	@Option(name = "-sort", required = true, usage = "sort to use, allowed: crit, hp, def")
	private void setComparator(Sort sort) {
		switch (sort) {
		case crit:
			COMBO_COMPARATOR = new CRIT_Combo_Comparator();
			SOUL_COMPARATOR = new CRIT_SoulShield_Comparator();
			break;
		case hp:
			COMBO_COMPARATOR = new HP_Combo_Comparator();
			SOUL_COMPARATOR = new HP_SoulShield_Comparator();
			break;
		case def:
			COMBO_COMPARATOR = new DEF_Combo_Comparator();
			SOUL_COMPARATOR = new DEF_SoulShield_Comparator();
			break;
		case cdef:
			COMBO_COMPARATOR = new CRITDEF_Combo_Comparator();
			SOUL_COMPARATOR = new CRITDEF_SoulShield_Comparator();
			break;
		default:
			COMBO_COMPARATOR = new CRIT_Combo_Comparator();
			SOUL_COMPARATOR = new CRIT_SoulShield_Comparator();
			break;
		}
	}

	// enchants
	@Option(name = "-ecrit", usage = "crit to enchant, default: none")
	public static String CRIT_ENCHANT = "";

	// Buffs
	@Option(name = "-acount", usage = "buffset1 count, default: 0")
	public static int FIRST_COUNT = 0;
	@Option(name = "-bcount", usage = "buffset2 count, default: 0")
	public static int SECOND_COUNT = 0;

	@Option(name = "-aset", usage = "buffset1, default: None")
	private void setFirstBuff(String set) {
		FIRST_SET = SoulSet.getSetByName(set.trim());
	}

	@Option(name = "-bset", usage = "buffset2, default: None")
	private void setSecondBuff(String set) {
		SECOND_SET = SoulSet.getSetByName(set.trim());
	}

	// Restrictions
	@Option(name = "-crit", usage = "minimum crit the combo must have, default: None")
	public static int CRIT = Integer.MIN_VALUE;
	@Option(name = "-hp", usage = "minimum hp the combo must have, default: None")
	public static int HP = Integer.MIN_VALUE;
	@Option(name = "-def", usage = "minimum def the combo must have, default: None")
	public static int DEF = Integer.MIN_VALUE;
	@Option(name = "-accu", usage = "minimum accu the combo must have, default: None")
	public static int ACCU = Integer.MIN_VALUE;

	@Option(name = "-critonly", usage = "only use shields with crit, default: false")
	public static boolean CRITONLY = false;

	// Output
	@Option(name = "-results", aliases = { "-r" }, usage = "the number of results, default: 10")
	public static int RESULTS = 10;
	@Option(name = "-force", aliases = { "-f", "-cpus" }, usage = "force number of cpus, default: Systems Maximum")
	public static int CPUS = Runtime.getRuntime().availableProcessors();
	@Option(name = "-noinfo", aliases = {
			"-no" }, usage = "if set, disables writing of .txt info files, default: false")
	public static boolean NOINFO = false;
	@Option(name = "-nocsv", usage = "dont write .csv files (usefull for gui comparing with open consoles)")
	public static boolean NOCSV = false;

	public static void main(String[] args) throws Exception {
		System.out.println(VERSION);
		System.out.println(Arrays.toString(args).replace(",", "").replace("[", "")
				.replace("]", ""));

		if (args == null || args.length < 1) {
			GUI.main(args);
		} else {
			new Main().startMain(args);
		}
	}

	private void startMain(String[] args) throws Exception {
		// Import Shields + Sets
		CSVImport.importSoulSets();
		SoulShields shields = CSVImport.importSoulShields();

		CmdLineParser cmdLineParser = new CmdLineParser(this);

		cmdLineParser.setUsageWidth(120);
		try {
			cmdLineParser.parseArgument(args);
		} catch (Exception e) {
			System.err.println(e.getMessage());

			System.err.print("Usage: java -jar BNS_Comparer.jar");
			cmdLineParser.printSingleLineUsage(System.err);
			System.err.println();

			cmdLineParser.printUsage(System.err);
			return;
		}

		// Filter by Set-Names
		shields.filterName(SETS);

		if (CRITONLY) {
			shields.filterCrit();
		}

		// pre-sort ss by crit, def, hp
		shields.sort(SOUL_COMPARATOR);

		// enchant shields
		if (CRIT_ENCHANT.length() > 0) {
			shields.enchantAll(CRIT_ENCHANT);
		}

		// minimum stats
		new ThreadStarter(args, shields);

		// new ThreadStarter(args, CPUS, results, price, ATK, MATK, COMBOSORT,
		// items, NOINFO, nocsv);
		System.console().readLine();
	}

}
