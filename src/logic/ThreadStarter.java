package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import amain.Main;
import modell.ComboSoul;
import modell.SoulShields;

public class ThreadStarter {
	private StringBuffer sb = new StringBuffer();
	private DecimalFormat df = new DecimalFormat(",###");

	public ThreadStarter(String[] args, SoulShields shields) {
		start(shields);
	}

	private void start(SoulShields shields) {
		List<ComboSoul> combs = new ArrayList<ComboSoul>();

		int results = 10;

		if (Main.RESULTS > 0) {
			results = Main.RESULTS;
		}

		long total = 1l * shields.getS_1().size() * shields.getS_2().size() * shields.getS_3().size()
				* shields.getS_4().size() * shields.getS_5().size() * shields.getS_6().size() * shields.getS_7().size()
				* shields.getS_8().size();

		System.out.println(df.format(total) + " Combinations");

		long start = System.currentTimeMillis();
		try {
			// 1/4 Loadbalancing - Forking
			List<Callable<List<ComboSoul>>> tasks = new ArrayList<>();
			for (int i = 0; i < shields.getS_1().size(); i++) {
				Callable<List<ComboSoul>> c = new ComboSoulCall(results, shields.getS_1().get(i), shields.getS_2(),
						shields.getS_3(), shields.getS_4(), shields.getS_5(), shields.getS_6(), shields.getS_7(),
						shields.getS_8());
				tasks.add(c);
			}

			// 2/4 Running
			ExecutorService executor = Executors.newFixedThreadPool(Main.CPUS);
			List<Future<List<ComboSoul>>> f_ComboSouls = executor.invokeAll(tasks);

			// 3/4 Joining Results
			for (Future<List<ComboSoul>> res : f_ComboSouls) {
				combs.addAll(res.get());
				if (combs.size() > results * 2) {
					Collections.sort(combs, Main.COMBO_COMPARATOR);
					combs = new ArrayList<>(combs.subList(0, results));
				}
			}

			Collections.sort(combs, Main.COMBO_COMPARATOR);

			if (results > total) {
				results = (int) total;
			}

			if (results > combs.size()) {
				results = combs.size();
			}

			combs = new ArrayList<ComboSoul>(combs.subList(0, results));

			// 4/4 Closing
			executor.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();

		System.out.println();
		String i_end1 = "------ DONE ------ Time = " + (end - start) + " ms";
		String i_end2 = "\t" + df.format(1.0d * total / (end - start) * 1000) + " checks / s\n";
		System.out.println(i_end1 + i_end2 + "Results:");
		sb.insert(0, i_end2);
		sb.insert(0, i_end1);
		sb.insert(0, "\n");

		if (combs.size() == 0) {
			System.out.println("Nothing found");
			return;
		}

		System.out.println(ComboSoul.HEADER_CONSOLE);
		for (int i = 0; i < combs.size(); i++) {
			System.out.println(i + ";" + combs.get(i).toStringConsole());
		}

		if (!Main.NOCSV) {
			writeResults(combs, "a");
		}
	}

	private void writeResults(List<ComboSoul> combs, String infos) {
		File out = new File("_out" + File.separator);
		out.mkdirs();
		if (combs == null || combs.size() < 1)
			return;
		try {
			long time = System.currentTimeMillis();
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("_out" + File.separator + time + ".csv"), "UTF-8"));
			bw.write(ComboSoul.HEADER);
			bw.newLine();
			for (int i = 0; i < combs.size(); i++) {

				bw.write(i + ";" + combs.get(i));
				bw.newLine();
			}
			bw.close();

			if (!Main.NOINFO) {
				BufferedWriter bufferedWriter = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream("_out" + File.separator + time + ".txt"), "UTF-8"));
				bufferedWriter.write(infos);
				bufferedWriter.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
