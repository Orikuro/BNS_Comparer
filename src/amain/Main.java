package amain;

import java.util.Comparator;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import comparators.*;
import inport.CSVImport;
import logic.ThreadStarter;
import modell.*;

public class Main {
	public static final String VERSION = "0.5 - 17.04.16 - by Orikuro";

	public static Comparator<ComboSoul> COMPARATOR = new CRIT_Combo_Comparator();

	public static enum Sort {
		crit, hp, def
	};

	// Main-Infos
	@Option(name = "-sets", required = true, usage = "sets to use use , for more than one. eg. \"Blackram, Mushin\"")
	public static String SETS = "";

	// sort
	@Option(name = "-sort", required = true, usage = "minimum crit the combo must have, default: None")
	private void setComparator(Sort sort) {
		switch (sort) {
		case crit:
			COMPARATOR = new CRIT_Combo_Comparator();
			break;
		case hp:
			COMPARATOR = new HP_Combo_Comparator();
			break;
		case def:
			COMPARATOR = new DEF_Combo_Comparator();
			break;
		default:
			COMPARATOR = new CRIT_Combo_Comparator();
			break;
		}
	}

	// Buffs
	// TODO: buffs

	// Enchants
	@Option(name = "-cenchant", usage = "the number of results, default: 30")
	public static int CRIT_ENCHANT = 30;

	// Restrictions
	@Option(name = "-crit", usage = "minimum crit the combo must have, default: None")
	public int CRIT = Integer.MIN_VALUE;
	@Option(name = "-hp", usage = "minimum hp the combo must have, default: None")
	public static int HP = Integer.MIN_VALUE;
	@Option(name = "-def", usage = "minimum def the combo must have, default: None")
	public static int DEF = Integer.MIN_VALUE;
	@Option(name = "-accu", usage = "minimum accu the combo must have, default: None")
	public static int ACCU = Integer.MIN_VALUE;
	@Option(name = "-pierce", usage = "minimum pierce the combo must have, default: None")
	public static int PIERCE = Integer.MIN_VALUE;
	@Option(name = "-critonly", usage = "dont write .csv files (usefull for gui comparing with open consoles)")
	public static boolean CRITONLY = false;

	// Output
	@Option(name = "-results", aliases = { "-r" }, usage = "the number of results, default: 30")
	public static int RESULTS = 30;
	@Option(name = "-force", aliases = { "-f", "-cpus" }, usage = "force number of cpus, default: Systems Maximum")
	public static int CPUS = Runtime.getRuntime().availableProcessors();
	@Option(name = "-noinfo", aliases = {
			"-no" }, usage = "if set, disables writing of .txt info files, default: false")
	public static boolean NOINFO = false;
	@Option(name = "-nocsv", usage = "dont write .csv files (usefull for gui comparing with open consoles)")
	public static boolean NOCSV = false;

	public static void main(String[] args) throws Exception {
		System.out.println(VERSION);

		if (args == null || args.length < 1) {
			// GUI.main(args);
		}

		new Main().startMain(args);
	}

	private void startMain(String[] args) throws Exception {
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

		// Import Shields
		SoulShields shields = CSVImport.importSoulShields();

		// enchant shields
		String enchants = "200 crit";
		shields.enchantAll(enchants);

		// sortierung nach crit, critdef, hp
		Comparator<? super SoulShield> comp = new CRIT_SoulShield_Comparator();
		shields.sort(comp);

		// minimum stats
		new ThreadStarter(args, shields);

		// new ThreadStarter(args, CPUS, results, price, ATK, MATK, COMBOSORT,
		// items, NOINFO, nocsv);

	}

}
