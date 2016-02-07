package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import modell.ComboSoul;

public class ThreadStarter {

	public ThreadStarter(String[] args) {
		start();
	}

	private void start() {
		long start = System.currentTimeMillis();
		try {
			// 1/4 Loadbalancing - Forking
			List<Callable<List<ComboSoul>>> tasks = new ArrayList<>();
			for (int i = 0; i < shoes.size(); i++) {
				Callable<List<ComboSoul>> c = new ComboSoulCall(price, atk, matk, maxis, shoes.subList(i, i + 1), pants,
						glov, armors, helms, results, defc);
				tasks.add(c);
			}

			// 2/4 Running
			ExecutorService executor = Executors.newFixedThreadPool(cpus);
			List<Future<List<ComboSoul>>> f_ComboSouls = executor.invokeAll(tasks);

			// 3/4 Joining Results
			for (Future<List<ComboSoul>> res : f_ComboSouls) {
				combs.addAll(res.get());
				if (combs.size() > results * 2) {
					Collections.sort(combs, defc);
					combs = new ArrayList<>(combs.subList(0, results));
				}
			}

			Collections.sort(combs, defc);

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
		sb.insert(0, i_comp);
		sb.insert(0, i_atk);
		sb.insert(0, i_res);
		sb.insert(0, i_anzahl);
		sb.insert(0, PARAMETER + "\n");

		if (combs.size() == 0) {
			System.out.println("Nothing found, price was too low.");
			return;
		}

		System.out.println(ComboSoul.OUTPUT_HEAD);
		for (int i = 0; i < combs.size(); i++) {
			System.out.println(i + ";" + combs.get(i));
			if (i >= 4) {
				System.out.println("[...]");
				break;
			}
		}
	}

}
