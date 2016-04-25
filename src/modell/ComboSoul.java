package modell;

import amain.Main;

public class ComboSoul {
	
	public static final String HEADER ="";

	private SoulShield S_1;
	private SoulShield S_2;
	private SoulShield S_3;
	private SoulShield S_4;
	private SoulShield S_5;
	private SoulShield S_6;
	private SoulShield S_7;
	private SoulShield S_8;

	private int crit = 0;	
	private int hp = 0;
	private int def = 0;
	private int accu = 0;
	private int block = 0;
	private int eva = 0;

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
		hp = S_1.getHp() + S_2.getHp() + S_3.getHp() + S_4.getHp() + S_5.getHp() + S_6.getHp()
		+ S_7.getHp() + S_8.getHp();	
		def = S_1.getDef() + S_2.getDef() + S_3.getDef() + S_4.getDef() + S_5.getDef() + S_6.getDef()
		+ S_7.getDef() + S_8.getDef();	
		accu = S_1.getAccuracy() + S_2.getAccuracy() + S_3.getAccuracy() + S_4.getAccuracy() + S_5.getAccuracy() + S_6.getAccuracy()
		+ S_7.getAccuracy() + S_8.getAccuracy();
		block = S_1.getBlock() + S_2.getBlock() + S_3.getBlock() + S_4.getBlock() + S_5.getBlock() + S_6.getBlock()
		+ S_7.getBlock() + S_8.getBlock();
		eva = S_1.getEvasion() + S_2.getEvasion() + S_3.getEvasion() + S_4.getEvasion() + S_5.getEvasion() + S_6.getEvasion()
		+ S_7.getEvasion() + S_8.getEvasion();
		

	}



	@Override
	public String toString() {
		return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;", crit,
				hp, def,accu,eva,block, S_1.getSet().getName()+" 1", S_2.getSet().getName()+" 2", S_3.getSet().getName()+" 3", S_4.getSet().getName()+" 4",
				S_5.getSet().getName()+" 5", S_6.getSet().getName()+" 6", S_7.getSet().getName()+" 7", S_8.getSet().getName()+" 8", Main.FIRST_SET.getBuff(Main.FIRST_COUNT),Main.SECOND_SET.getBuff(Main.SECOND_COUNT));
	}



	public int getCrit() {
		return crit;
	}
	
	public int getDef() {
		return def;
	}
	
	public int getHp() {
		return hp;
	}

}
