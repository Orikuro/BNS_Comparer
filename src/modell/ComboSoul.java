package modell;

public class ComboSoul {

	private SoulShield S_1;
	private SoulShield S_2;
	private SoulShield S_3;
	private SoulShield S_4;
	private SoulShield S_5;
	private SoulShield S_6;
	private SoulShield S_7;
	private SoulShield S_8;

	private int crit = 0;
	
	private int def = 0;

	public ComboSoul(SoulShield s_1, SoulShield s_2, SoulShield s_3, SoulShield s_4, SoulShield s_5, SoulShield s_6,
			SoulShield s_7, SoulShield s_8) {
		super();
		S_1 = s_1;
		S_2 = s_2;
		S_3 = s_3;
		S_4 = s_4;
		S_5 = s_5;
		S_6 = s_6;
		S_7 = s_7;
		S_8 = s_8;

		crit = S_1.getCrit() + S_2.getCrit() + S_3.getCrit() + S_4.getCrit() + S_5.getCrit() + S_6.getCrit()
				+ S_7.getCrit() + S_8.getCrit();
		
		def = S_1.getDef() + S_2.getDef() + S_3.getDef() + S_4.getDef() + S_5.getDef() + S_6.getDef()
		+ S_7.getDef() + S_8.getDef();
	}

	@Override
	public String toString() {
		return crit+";"+def+";"+"[S_1=" + S_1 + ", S_2=" + S_2 + ", S_3=" + S_3 + ", S_4=" + S_4 + ", S_5=" + S_5 + ", S_6="
				+ S_6 + ", S_7=" + S_7 + ", S_8=" + S_8 + "]";
	}

	public int getCrit() {
		return crit;
	}
	
	public int getDef() {
		return def;
	}

}
