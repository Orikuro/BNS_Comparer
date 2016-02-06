package inport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import modell.SoulShield;

public class CSVImport {

	private static List<Integer> TEMP = new ArrayList<>(); 
	
	private static void showCombination(int[] s) {
		for (int i = 0; i < s.length; i++) {
		//	System.out.print(s[i] + " ");
			TEMP.add(s[i]);
		}
		//System.out.println();
		
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

	private static List<String> readCSV() {
		List<String> csvfile = new ArrayList<>();

		String thisLine = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(CSV), "UTF-8"));
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
				for (String x : content) {
			//		System.out.println(x);
				}

				SoulShield base = new SoulShield(content[0], content[1], content[2]);
				base.add(content[3]);
				base.add(content[4]);
				
				int combinations = new Integer(content[5].trim());
				int substats = content.length-6;
				
				System.out.println(base + " "+combinations + " "+substats);
				int[] s = new int[combinations];
				
				generate(s, 0, 0, combinations, substats);
				for (int j=0;j<TEMP.size();j++){
					System.out.print(TEMP.get(j)+"  ");
				}
				
				SoulShield combinedSS = new SoulShield(base);
				
				
				System.out.println("--");
				TEMP.clear();
				
			}
		}

		return shields;
	}

	private List<SoulShield> SHIELDS;

	public static void main(String[] args) {
		convertCSVtoBopae(readCSV());
	}

}
