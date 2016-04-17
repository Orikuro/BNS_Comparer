package modell;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import comparators.CRIT_SoulShield_Comparator;

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
}