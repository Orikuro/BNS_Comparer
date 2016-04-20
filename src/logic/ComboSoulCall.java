package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

import amain.Main;
import comparators.*;
import modell.ComboSoul;
import modell.SoulShield;

public class ComboSoulCall implements Callable<List<ComboSoul>> {

	private List<SoulShield> S_1 = new ArrayList<>();
	private List<SoulShield> S_2 = new ArrayList<>();
	private List<SoulShield> S_3 = new ArrayList<>();
	private List<SoulShield> S_4 = new ArrayList<>();
	private List<SoulShield> S_5 = new ArrayList<>();
	private List<SoulShield> S_6 = new ArrayList<>();
	private List<SoulShield> S_7 = new ArrayList<>();
	private List<SoulShield> S_8 = new ArrayList<>();

	private int results = 30;
	private int thresh;

	private List<ComboSoul> combos = new ArrayList<>();

	public ComboSoulCall(SoulShield s_1, List<SoulShield> s_2, List<SoulShield> s_3, List<SoulShield> s_4,
			List<SoulShield> s_5, List<SoulShield> s_6, List<SoulShield> s_7, List<SoulShield> s_8) {
		super();
		S_1.add(s_1);
		S_2 = s_2;
		S_3 = s_3;
		S_4 = s_4;
		S_5 = s_5;
		S_6 = s_6;
		S_7 = s_7;
		S_8 = s_8;
		thresh = results * 2;
	}

	private static int min = -1;

	@Override
	public List<ComboSoul> call() throws Exception {

		String compname = Main.COMBO_COMPARATOR.getClass().getSimpleName();

		switch (compname) {
		case "Crit_Combo_Comparator":
			return callCrit();
		case "Def_Combo_Comparator":
			return callDef();
		// case "MatkComparator":
		// return callMatk();
		// case "StaDComparator":
		// case "StaAComparator":
		// case "StaMComparator":
		// return callSta();
		default:
			return callCrit();
		}
	}

	private List<ComboSoul> callCrit() throws Exception {

		int i = 0;
		int total, t1, t2, t3, t4, t5, t6, t7, t8;

		int first = 0;
		int second = 0;
		
		for (SoulShield s1 : S_1) {
			t1 = s1.getCrit();
			for (SoulShield s2 : S_2) {
				t2 = s2.getCrit();
				for (SoulShield s3 : S_3) {
					t3 = s3.getCrit();
					for (SoulShield s4 : S_4) {
						t4 = s4.getCrit();
						for (SoulShield s5 : S_5) {
							t5 = s5.getCrit();
							for (SoulShield s6 : S_6) {
								t6 = s6.getCrit();
								for (SoulShield s7 : S_7) {
									t7 = s7.getCrit();
									for (SoulShield s8 : S_8) {
										t8 = s8.getCrit();

										total = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;

										if (total >= min) {
											ComboSoul c = new ComboSoul(s1, s2, s3, s4, s5, s6, s7, s8);
											combos.add(c);
										}
										i++;
										if (i % 100000000 == 0) {
											System.out.print(".");
										}
										if (combos.size() > thresh) {
											Collections.sort(combos, Main.COMBO_COMPARATOR);
											combos = new ArrayList<ComboSoul>(combos.subList(0, results));
											synchronized (this) {
												min = combos.get(results - 1).getCrit();
											}
										}

									}
								}
							}
						}
					}
				}
			}

		}

		System.out.print("x");
		return combos;

	}

	private List<ComboSoul> callDef() throws Exception {

		int i = 0;
		int total, t1, t2, t3, t4, t5, t6, t7, t8;

		for (SoulShield s1 : S_1) {
			t1 = s1.getDef();
			for (SoulShield s2 : S_2) {
				t2 = s2.getDef();
				for (SoulShield s3 : S_3) {
					t3 = s3.getDef();
					for (SoulShield s4 : S_4) {
						t4 = s4.getDef();
						for (SoulShield s5 : S_5) {
							t5 = s5.getDef();
							for (SoulShield s6 : S_6) {
								t6 = s6.getDef();
								for (SoulShield s7 : S_7) {
									t7 = s7.getDef();
									for (SoulShield s8 : S_8) {
										t8 = s8.getDef();

										total = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;

										if (total >= min) {
											ComboSoul c = new ComboSoul(s1, s2, s3, s4, s5, s6, s7, s8);
											combos.add(c);
										}
										i++;
										if (i % 100000000 == 0) {
											System.out.print(".");
										}
										if (combos.size() > thresh) {
											Collections.sort(combos, Main.COMBO_COMPARATOR);
											combos = new ArrayList<ComboSoul>(combos.subList(0, results));
											synchronized (this) {
												min = combos.get(results - 1).getDef();
											}
										}

									}
								}
							}
						}
					}
				}
			}

		}

		System.out.print("x");
		return combos;

	}
}
