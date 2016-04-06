package inport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		System.out.print("name;waffe;klasse;");
		for (String asd : werteNamen) {
			System.out.print(asd + ";");
		}
		System.out.print("1;2;3;4;5;6;7;8;9;10;");
		System.out.println();

		for (String name : CHARS) {
			try {
				x = holeURL(name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] stats = x.split("stat-point");
			String[] a = x.split("grade_");
			System.out.print(name + ";");
			System.out.print(a[1].substring(3, a[1].indexOf("</")) + ";");
			
			String[] nam = x.split("http://static.ncsoft.com/ingame/bns/character_v2/profile/");
			System.out.print(nam[1].substring(0, nam[1].indexOf("."))+ ";");
			
			for (int i : werteIndizes) {
				System.out.print(stats[i].substring(2, stats[i].indexOf("</")) + ";");
			}
			for (int i = 2; i < a.length; i++) {
				System.out.print(a[i].substring(3, a[i].indexOf("</")) + ";");
			}

			System.out.println("");
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
		CHARS = readTxt(args[0]);
		alpha();
	}

}
