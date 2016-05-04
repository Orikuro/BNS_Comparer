package modell;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SoulShields {
	private List<SoulShield> S_1 = new ArrayList<>();
	private List<SoulShield> S_2 = new ArrayList<>();
	private List<SoulShield> S_3 = new ArrayList<>();
	private List<SoulShield> S_4 = new ArrayList<>();
	private List<SoulShield> S_5 = new ArrayList<>();
	private List<SoulShield> S_6 = new ArrayList<>();
	private List<SoulShield> S_7 = new ArrayList<>();
	private List<SoulShield> S_8 = new ArrayList<>();

	public SoulShields(List<SoulShield> s_1, List<SoulShield> s_2, List<SoulShield> s_3, List<SoulShield> s_4,
			List<SoulShield> s_5, List<SoulShield> s_6, List<SoulShield> s_7, List<SoulShield> s_8) {
		super();
		S_1 = s_1;
		S_2 = s_2;
		S_3 = s_3;
		S_4 = s_4;
		S_5 = s_5;
		S_6 = s_6;
		S_7 = s_7;
		S_8 = s_8;
	}

	public List<SoulShield> getS_1() {
		return S_1;
	}

	public void setS_1(List<SoulShield> s_1) {
		S_1 = s_1;
	}

	public List<SoulShield> getS_2() {
		return S_2;
	}

	public void setS_2(List<SoulShield> s_2) {
		S_2 = s_2;
	}

	public List<SoulShield> getS_3() {
		return S_3;
	}

	public void setS_3(List<SoulShield> s_3) {
		S_3 = s_3;
	}

	public List<SoulShield> getS_4() {
		return S_4;
	}

	public void setS_4(List<SoulShield> s_4) {
		S_4 = s_4;
	}

	public List<SoulShield> getS_5() {
		return S_5;
	}

	public void setS_5(List<SoulShield> s_5) {
		S_5 = s_5;
	}

	public List<SoulShield> getS_6() {
		return S_6;
	}

	public void setS_6(List<SoulShield> s_6) {
		S_6 = s_6;
	}

	public List<SoulShield> getS_7() {
		return S_7;
	}

	public void setS_7(List<SoulShield> s_7) {
		S_7 = s_7;
	}

	public List<SoulShield> getS_8() {
		return S_8;
	}

	public void setS_8(List<SoulShield> s_8) {
		S_8 = s_8;
	}

	public void sort(Comparator<? super SoulShield> comp) {
		S_1.sort(comp);
		S_2.sort(comp);
		S_3.sort(comp);
		S_4.sort(comp);
		S_5.sort(comp);
		S_6.sort(comp);
		S_7.sort(comp);
		S_8.sort(comp);
	}

	public void enchantAll(String enchants) {
		enchant_1(enchants);
		enchant_2(enchants);
		enchant_3(enchants);
		enchant_4(enchants);
		enchant_5(enchants);
		enchant_6(enchants);
		enchant_7(enchants);
		enchant_8(enchants);
	}

	public void enchant_1(String enchants) {
		for (SoulShield s : S_1) {
			s.addLimited(enchants);
		}
	}

	public void enchant_2(String enchants) {
		for (SoulShield s : S_2) {
			s.addLimited(enchants);
		}
	}

	public void enchant_3(String enchants) {
		for (SoulShield s : S_3) {
			s.addLimited(enchants);
		}
	}

	public void enchant_4(String enchants) {
		for (SoulShield s : S_4) {
			s.addLimited(enchants);
		}
	}

	public void enchant_5(String enchants) {
		for (SoulShield s : S_5) {
			s.addLimited(enchants);
		}
	}

	public void enchant_6(String enchants) {
		for (SoulShield s : S_6) {
			s.addLimited(enchants);
		}
	}

	public void enchant_7(String enchants) {
		for (SoulShield s : S_7) {
			s.addLimited(enchants);
		}
	}

	public void enchant_8(String enchants) {
		for (SoulShield s : S_8) {
			s.addLimited(enchants);
		}
	}

	public void filterName(String sETS) {
		String setname;
		int count = 0;
		List<SoulShield> S1 = new ArrayList<>();
		List<SoulShield> S2 = new ArrayList<>();
		List<SoulShield> S3 = new ArrayList<>();
		List<SoulShield> S4 = new ArrayList<>();
		List<SoulShield> S5 = new ArrayList<>();
		List<SoulShield> S6 = new ArrayList<>();
		List<SoulShield> S7 = new ArrayList<>();
		List<SoulShield> S8 = new ArrayList<>();

		for (SoulShield s : S_1) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S1.add(s);
				count++;
			}
		}
		for (SoulShield s : S_2) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S2.add(s);
				count++;
			}
		}
		for (SoulShield s : S_3) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S3.add(s);
				count++;
			}
		}
		for (SoulShield s : S_4) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S4.add(s);
				count++;
			}
		}
		for (SoulShield s : S_5) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S5.add(s);
				count++;
			}
		}
		for (SoulShield s : S_6) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S6.add(s);
				count++;
			}
		}
		for (SoulShield s : S_7) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S7.add(s);
				count++;
			}
		}
		for (SoulShield s : S_8) {
			setname = s.getSet().getName();
			if (!sETS.contains(setname)) {
				S8.add(s);
				count++;
			}
		}
		
		S_1.removeAll(S1);
		S_2.removeAll(S2);
		S_3.removeAll(S3);
		S_4.removeAll(S4);
		S_5.removeAll(S5);
		S_6.removeAll(S6);
		S_7.removeAll(S7);
		S_8.removeAll(S8);
		
		System.out.println(count + " Soulshields removed due to filter (" + sETS + ")");
	}
	
	public void filterCrit() {
		String setname;
		int count = 0;
		List<SoulShield> S1 = new ArrayList<>();
		List<SoulShield> S2 = new ArrayList<>();
		List<SoulShield> S3 = new ArrayList<>();
		List<SoulShield> S4 = new ArrayList<>();
		List<SoulShield> S5 = new ArrayList<>();
		List<SoulShield> S6 = new ArrayList<>();
		List<SoulShield> S7 = new ArrayList<>();
		List<SoulShield> S8 = new ArrayList<>();

		for (SoulShield s : S_1) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S1.add(s);
				count++;
			}
		}
		for (SoulShield s : S_2) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S2.add(s);
				count++;
			}
		}
		for (SoulShield s : S_3) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S3.add(s);
				count++;
			}
		}
		for (SoulShield s : S_4) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S4.add(s);
				count++;
			}
		}
		for (SoulShield s : S_5) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S5.add(s);
				count++;
			}
		}
		for (SoulShield s : S_6) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S6.add(s);
				count++;
			}
		}
		for (SoulShield s : S_7) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S7.add(s);
				count++;
			}
		}
		for (SoulShield s : S_8) {
			setname = s.getSet().getName();
			if (s.getCrit()<=0) {
				S8.add(s);
				count++;
			}
		}
		
		S_1.removeAll(S1);
		S_2.removeAll(S2);
		S_3.removeAll(S3);
		S_4.removeAll(S4);
		S_5.removeAll(S5);
		S_6.removeAll(S6);
		S_7.removeAll(S7);
		S_8.removeAll(S8);
		
		System.out.println(count + " Soulshields removed due to no crit ;_;" );
	}

	public void onlyUse(String only) {
		
		SoulShield empty = new SoulShield("");
		
		if (only.contains("1")){
			S_1.clear();
			S_1.add(empty);
		}
		if (only.contains("2")){
			S_2.clear();
			S_2.add(empty);
		}
		if (only.contains("3")){
			S_3.clear();
			S_3.add(empty);
		}
		if (only.contains("4")){
			S_4.clear();
			S_4.add(empty);
		}
		if (only.contains("5")){
			S_5.clear();
			S_5.add(empty);
		}
		if (only.contains("6")){
			S_6.clear();
			S_6.add(empty);
		}
		if (only.contains("7")){
			S_7.clear();
			S_7.add(empty);
		}
		if (only.contains("8")){
			S_8.clear();
			S_8.add(empty);
		}
		
		
	}
}