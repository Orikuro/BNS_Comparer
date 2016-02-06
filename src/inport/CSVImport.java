package inport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CSVImport {

	private static void showCombination(int[] s) {
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + " ");
		}
		System.out.println();
	}

	public static void generate(int[] s, int position, int nextInt, int k, int N) {
		if (position == k) {
			showCombination(s);
			return;
		}
		for (int i = nextInt; i < N; i++) {
			s[position] = i;
			generate(s, position + 1, i + 1, k, N);
		}
	}

	private static void combis(String[] args) {
		int N = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);

		int[] s = new int[k];
		generate(s, 0, 0, k, N);
	}

	private static final File CSV = new File("data" + File.separator + "bopae.csv");

	private static void readCSV() {

		String thisLine = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(CSV), "UTF-8"));
			while ((thisLine = br.readLine()) != null) {
				System.out.println(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		readCSV();
	}

}
