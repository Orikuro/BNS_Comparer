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
		int acount, bcount = 0;
		int a, b, c, d, e, f, g, h;
		int aa, bb, cc, dd, ee, ff, gg, hh;

		boolean first = Main.FIRST_COUNT > 0;
		boolean second = Main.SECOND_COUNT > 0;

		for (SoulShield s1 : S_1) {
			a = 0;
			aa = 0;
			t1 = s1.getCrit();
			if (first && s1.getSet().equals(Main.FIRST_SET)) {
				a++;
			}
			if (second && s1.getSet().equals(Main.SECOND_SET)) {
				aa++;
			}
			for (SoulShield s2 : S_2) {
				b = 0;
				bb = 0;
				t2 = s2.getCrit();
				if (first && s2.getSet().equals(Main.FIRST_SET)) {
					b++;
				}
				if (second && s2.getSet().equals(Main.SECOND_SET)) {
					bb++;
				}
				for (SoulShield s3 : S_3) {
					c = 0;
					cc = 0;
					t3 = s3.getCrit();
					if (first && s3.getSet().equals(Main.FIRST_SET)) {
						c++;
					}
					if (second && s3.getSet().equals(Main.SECOND_SET)) {
						cc++;
					}
					for (SoulShield s4 : S_4) {
						d = 0;
						dd = 0;
						t4 = s4.getCrit();
						if (first && s4.getSet().equals(Main.FIRST_SET)) {
							d++;
						}
						if (second && s4.getSet().equals(Main.SECOND_SET)) {
							dd++;
						}

						for (SoulShield s5 : S_5) {
							e = 0;
							ee = 0;
							t5 = s5.getCrit();
							if (first && s5.getSet().equals(Main.FIRST_SET)) {
								e++;
							}
							if (second && s5.getSet().equals(Main.SECOND_SET)) {
								ee++;
							}

							for (SoulShield s6 : S_6) {
								f = 0;
								ff = 0;
								t6 = s6.getCrit();
								if (first && s6.getSet().equals(Main.FIRST_SET)) {
									f++;
								}
								if (second && s6.getSet().equals(Main.SECOND_SET)) {
									ff++;
								}

								for (SoulShield s7 : S_7) {
									g = 0;
									gg = 0;
									t7 = s7.getCrit();
									if (first && s7.getSet().equals(Main.FIRST_SET)) {
										g++;
									}
									if (second && s7.getSet().equals(Main.SECOND_SET)) {
										gg++;
									}

									for (SoulShield s8 : S_8) {
										h = 0;
										hh = 0;
										t8 = s8.getCrit();
										if (first && s8.getSet().equals(Main.FIRST_SET)) {
											h++;
										}
										if (second && s8.getSet().equals(Main.SECOND_SET)) {
											h++;
										}

										acount = a + b + c + d + e + f + g + h;
										bcount = aa + bb + cc + dd + ee + ff + gg + hh;
										total = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;

										if (((acount == Main.FIRST_COUNT || !first)
												&& (bcount == Main.SECOND_COUNT || !second)) && total >= min) {
											ComboSoul combo = new ComboSoul(s1, s2, s3, s4, s5, s6, s7, s8);
											combos.add(combo);
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
