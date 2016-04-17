import java.util.Comparator;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import comparators.*;
import inport.CSVImport;
import logic.ThreadStarter;
import modell.*;

public class Main {
	public static final String VERSION = "0.5 - 17.04.16 - by Orikuro";

	private static enum Sort {
		crit, hp, critdef
	};

	
	// Main-Infos
	
	
	
	// Restrictions
	@Option(name = "-crit", usage = "minimum crit the combo must have, default: None")
	private int CRIT = Integer.MIN_VALUE;
	@Option(name = "-hp", usage = "minimum hp the combo must have, default: None")
	private int HP = Integer.MIN_VALUE;
	@Option(name = "-def", usage = "minimum def the combo must have, default: None")
	private int DEF = Integer.MIN_VALUE;
	@Option(name = "-critdef", usage = "minimum critdef the combo must have, default: None")
	private int CRITDEF = Integer.MIN_VALUE;
	@Option(name = "-block", usage = "minimum block the combo must have, default: None")
	private int BLOCK = Integer.MIN_VALUE;
	@Option(name = "-eva", usage = "minimum eva the combo must have, default: None")
	private int EVA = Integer.MIN_VALUE;
	@Option(name = "-accu", usage = "minimum accu the combo must have, default: None")
	private int ACCU = Integer.MIN_VALUE;
	@Option(name = "-pierce", usage = "minimum pierce the combo must have, default: None")
	private int PIERCE = Integer.MIN_VALUE;
	@Option(name = "-ignore", aliases = { "-i" }, usage = "ignore sets that contain those words, use  commas to separate names eg \"raider, champion\", default: disabled")
	private String ignore = "";
	
	// Output
	@Option(name = "-results", aliases = { "-r" }, usage = "the number of results, default: 30")
	private int RESULTS = 30;
	@Option(name = "-force", aliases = { "-f", "-cpus" }, usage = "force number of cpus, default: Systems Maximum")
	private int CPUS = Runtime.getRuntime().availableProcessors();
	@Option(name = "-noinfo", aliases = { "-no" }, usage = "if set, disables writing of .txt info files, default: false")
	private boolean NOINFO = false;
	@Option(name = "-nocsv", usage = "dont write .csv files (usefull for gui comparing with open consoles)")
	private boolean nocsv = false;

	
	public static void main(String[] args) throws Exception {
		System.out.println(VERSION);

		if (args == null || args.length < 1) {
			//GUI.main(args);
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
		Comparator<? super SoulShield> comp = new Crit_SoulShield_Comparator();
		shields.sort(comp);

		// minimum stats
		new ThreadStarter(args, shields);

		// new ThreadStarter(args, CPUS, results, price, ATK, MATK, COMBOSORT,
		// items, NOINFO, nocsv);

	}

}
