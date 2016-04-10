package inport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CharImport {

	private static final String NCSOFT_URL = "http://eu-bns.ncsoft.com/ingame/bs/character/profile?c=";

	private static List<String> readTxt(String path) {
		List<String> txtfile = new ArrayList<>();

		String thisLine = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
			while ((thisLine = br.readLine()) != null) {
				txtfile.add(thisLine);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return txtfile;
	}

	private static List<String> CHARS = new ArrayList<>();

	private static String holeURL(String name) throws Exception {
		URL url = new URL(NCSOFT_URL + name);
		InputStream is = url.openStream();
		return new Scanner(is).useDelimiter("\\Z").next();
	}

	private static void alpha() {
		String x = "";
		List<String> ergs = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append("name;klasse;waffe;");
		System.out.print("name;klasse;waffe;");
		for (String asd : werteNamen) {
			System.out.print(asd + ";");
			sb.append(asd + ";");
		}
		System.out.print("necklace;earring;bracelet;ring;belt;soul;");
		sb.append("necklace;earring;bracelet;ring;belt;soul;");
		System.out.println();
		ergs.add(sb.toString());

		for (String name : CHARS) {
			sb = new StringBuffer();
			try {
				x = holeURL(name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] nam = x.split("http://static.ncsoft.com/ingame/bns/character_v2/profile/");

			String[] stats = x.split("stat-point");
			String[] a = x.split("div class=\"name\"");
			System.out.print(name + ";");
			sb.append(name + ";");
			System.out.print(nam[1].substring(0, nam[1].indexOf(".")) + ";");
			sb.append(nam[1].substring(0, nam[1].indexOf(".")) + ";");

			System.out
					.print(a[1].substring(3, a[1].indexOf("</")).replace("<span class=\"grade_5\">", "").trim() + ";");
			sb.append(a[1].substring(3, a[1].indexOf("</")).replace("<span class=\"grade_5\">", "").trim() + ";");
			for (int i : werteIndizes) {
				System.out.print(stats[i].substring(2, stats[i].indexOf("</")) + ";");
				sb.append(stats[i].substring(2, stats[i].indexOf("</")) + ";");
			}
			for (int i = 2; i < a.length; i++) {
				if (i == 2 || i == 3 || i == 5 || i == 7 || i == 9) {
					System.out.print(
							a[i].substring(3, a[i].indexOf("</")).replace("<span class=\"grade_5\">", "").trim() + ";");
					sb.append(
							a[i].substring(3, a[i].indexOf("</")).replace("<span class=\"grade_5\">", "").trim() + ";");
				}

				if (i == 10) {
					String soulname = a[i].substring(3, a[i].indexOf("</")).replace("<span class=\"grade_5\">", "")
							.trim() + ";";
					if (!soulname.contains("class=\"empty\"")) {
						System.out.print(
								a[i].substring(3, a[i].indexOf("</")).replace("<span class=\"grade_5\">", "").trim()
										+ ";");
						sb.append(a[i].substring(3, a[i].indexOf("</")).replace("<span class=\"grade_5\">", "").trim()
								+ ";");
					}
				}
			}

			System.out.println("");
			ergs.add(sb.toString());
		}
		writeResults(ergs);
	}

	private static void writeResults(List<String> liste) {
		File out = new File("_out" + File.separator);
		out.mkdirs();
		if (liste == null || liste.size() < 2)
			return;
		try {
			long time = System.currentTimeMillis();
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("_out" + File.separator + time + ".csv"), "UTF-8"));

			for (int i = 0; i < liste.size(); i++) {

				bw.write(liste.get(i));
				bw.newLine();
			}
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static int[] werteIndizes = { 1, 45, 8, 11, 13, 16, 22, 25, 26, 29, 49, 61, 66 };

	static String[] werteNamen = { "Attack Power", "HP", "Piercing", "Defense Piercing", "Accuracy", "Hit Rate",
			"Critical Hit", "Crit %", "Critical Damage", "Critdam %", "Defense", "Evasion", "Block" };

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		System.out.println("Charimporter Beta 0.1  11.04.2016");
		if (args == null || args.length < 1) {
			CHARS = readTxt("clan.txt");
		} else {
			CHARS = readTxt(args[0]);
		}
		alpha();
	}

}
