package inport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import modell.SoulSet;
import modell.SoulShield;
import modell.SoulShields;

public class CSVImport {

	private static List<Integer> TEMP = new ArrayList<>();

	private static void showCombination(int[] s) {
		for (int i = 0; i < s.length; i++) {
			TEMP.add(s[i]);
		}
	}

	private static void generate(int[] s, int position, int nextInt, int k, int N) {
		if (position == k) {
			showCombination(s);
			return;
		}
		for (int i = nextInt; i < N; i++) {
			s[position] = i;
			generate(s, position + 1, i + 1, k, N);
		}

	}

	private static final File CSV_SHIELDS = new File("data" + File.separator + "bopae.csv");
	private static final File CSV_SETS = new File("data" + File.separator + "sets.csv");

	private static List<String> readCSV(File f) {
		List<String> csvfile = new ArrayList<>();

		String thisLine = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
			while ((thisLine = br.readLine()) != null) {
				csvfile.add(thisLine);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return csvfile;
	}

	private static List<SoulShield> convertCSVtoBopae(List<String> input) {

		List<SoulShield> shields = new ArrayList<>();

		for (int i = 1; i < input.size() - 0; i++) {
			if (input.get(i).length() > 9) {
				String csvline = input.get(i);
				String[] content = csvline.split(";");

				SoulShield base = new SoulShield(content[0], content[1], content[2]);
				base.add(content[3]);
				base.add(content[4]);

				int combinations = new Integer(content[5].trim());
				int substats = content.length - 6;

				// System.out.println(base + " " + combinations + " " +
				// substats);
				int[] s = new int[combinations];

				generate(s, 0, 0, combinations, substats);

				// System.out.println(TEMP.size());

				for (int j = 0; j < TEMP.size(); j += combinations) {
					// System.out.print(TEMP.get(j) + " ");
					SoulShield combinedSS = new SoulShield(base);

					int temp = j;
					for (int k = 0; k < combinations; k++) {
						combinedSS.add(content[6 + TEMP.get(temp)]);
						temp++;
					}
					shields.add(combinedSS);
				}
				TEMP.clear();
			}
		}

		return shields;
	}

	private static void convertCSVtoSets(List<String> input) {

		for (int i = 1; i < input.size() - 0; i++) {
			if (input.get(i).length() > 3) {
				String csvline = input.get(i);
				String[] content = csvline.split(";");

				switch (content.length) {
				case 4:
					SoulSet three = new SoulSet(content[0], content[1], content[2], content[3]);
					break;
				case 3:
					SoulSet two = new SoulSet(content[0], content[1], content[2]);
					break;
				case 2:
					SoulSet one = new SoulSet(content[0], content[1]);
					break;
				}
			}
		}
	}

	private static List<SoulShield> S_1 = new ArrayList<>();
	private static List<SoulShield> S_2 = new ArrayList<>();
	private static List<SoulShield> S_3 = new ArrayList<>();
	private static List<SoulShield> S_4 = new ArrayList<>();
	private static List<SoulShield> S_5 = new ArrayList<>();
	private static List<SoulShield> S_6 = new ArrayList<>();
	private static List<SoulShield> S_7 = new ArrayList<>();
	private static List<SoulShield> S_8 = new ArrayList<>();

	private static void generateLists(List<SoulShield> shields) {
		for (SoulShield s : shields) {

			switch (s.getPosition()) {
			case 1:
				S_1.add(s);
				break;
			case 2:
				S_2.add(s);
				break;
			case 3:
				S_3.add(s);
				break;
			case 4:
				S_4.add(s);
				break;
			case 5:
				S_5.add(s);
				break;
			case 6:
				S_6.add(s);
				break;
			case 7:
				S_7.add(s);
				break;
			case 8:
				S_8.add(s);
				break;
			}
		}
	}

	public static SoulShields importSoulShields() {
		List<SoulShield> shields = convertCSVtoBopae(readCSV(CSV_SHIELDS));

		System.out.println(shields.size() + " shields total");
		generateLists(shields);

		int s1 = S_1.size();
		int s2 = S_2.size();
		int s3 = S_3.size();
		int s4 = S_4.size();
		int s5 = S_5.size();
		int s6 = S_6.size();
		int s7 = S_7.size();
		int s8 = S_8.size();

		System.out.print("1:" + s1 + "  ");
		System.out.print("2:" + s2 + "  ");
		System.out.print("3:" + s3 + "  ");
		System.out.print("4:" + s4 + "  ");
		System.out.print("5:" + s5 + "  ");
		System.out.print("6:" + s6 + "  ");
		System.out.print("7:" + s7 + "  ");
		System.out.println("8:" + s8 + "  ");

		return new SoulShields(S_1, S_2, S_3, S_4, S_5, S_6, S_7, S_8);
	}

	public static void importSoulSets() {

		convertCSVtoSets(readCSV(CSV_SETS));

	}

}
