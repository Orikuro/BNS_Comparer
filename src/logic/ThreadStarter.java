package logic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import comparators.Crit_Combo_Comparator;
import modell.ComboSoul;
import modell.SoulShield;
import modell.SoulShields;

public class ThreadStarter {
	private StringBuffer sb = new StringBuffer();
	private DecimalFormat df = new DecimalFormat(",###");

	public ThreadStarter(String[] args, SoulShields shields) {
		start(shields);
	}

	private void start(SoulShields shields) {
		List<ComboSoul> combs = new ArrayList<ComboSoul>();
		int results = 30;

		Crit_Combo_Comparator ccc = new Crit_Combo_Comparator();
		long total = shields.getS_1().size() * shields.getS_2().size() * shields.getS_3().size()
				* shields.getS_4().size() * shields.getS_5().size() * shields.getS_6().size() * shields.getS_7().size()
				* shields.getS_8().size();
		
		System.out.println(total + " Combinations");

		long start = System.currentTimeMillis();
		try {
			// 1/4 Loadbalancing - Forking
			List<Callable<List<ComboSoul>>> tasks = new ArrayList<>();
			for (int i = 0; i < shields.getS_1().size(); i++) {
				Callable<List<ComboSoul>> c = new ComboSoulCall(shields.getS_1().get(i), shields.getS_2(),
						shields.getS_3(), shields.getS_4(), shields.getS_5(), shields.getS_6(), shields.getS_7(),
						shields.getS_8());
				tasks.add(c);
			}

			// 2/4 Running
			ExecutorService executor = Executors.newFixedThreadPool(4);
			List<Future<List<ComboSoul>>> f_ComboSouls = executor.invokeAll(tasks);

			// 3/4 Joining Results
			for (Future<List<ComboSoul>> res : f_ComboSouls) {
				combs.addAll(res.get());
				if (combs.size() > results * 2) {
					Collections.sort(combs, ccc);
					combs = new ArrayList<>(combs.subList(0, results));
				}
			}

			Collections.sort(combs, ccc);

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
			System.out.println("Nothing found, price was too low.");
			return;
		}

		for (int i = 0; i < 3; i++) {
			System.out.println(combs.get(i));
		}
	}

}
