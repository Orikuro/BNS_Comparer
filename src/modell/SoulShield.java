package modell;

public class SoulShield {
	
	private SoulSet set;
	
	private int hp;
	private int accuracy;
	private int def;
	private int block;
	private int evasion;
	private int crit;
	private int maxenchant;
	
	
	public SoulShield(SoulSet set, int hp, int accuracy, int def, int block, int evasion, int crit, int maxenchant) {
		super();
		this.set = set;
		this.hp = hp;
		this.accuracy = accuracy;
		this.def = def;
		this.block = block;
		this.evasion = evasion;
		this.crit = crit;
		this.maxenchant = maxenchant;
	}
	public SoulShield() {
		super();
	}
	
	@Override
	public String toString() {
		return "SoulShield [set=" + set + ", hp=" + hp + ", accuracy=" + accuracy + ", def=" + def + ", block=" + block + ", evasion=" + evasion + ", crit=" + crit
				+ ", maxenchant=" + maxenchant + "]";
	}
	public SoulSet getSet() {
		return set;
	}
	public void setSet(SoulSet set) {
		this.set = set;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public int getEvasion() {
		return evasion;
	}
	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}
	public int getCrit() {
		return crit;
	}
	public void setCrit(int crit) {
		this.crit = crit;
	}
	public int getMaxenchant() {
		return maxenchant;
	}
	public void setMaxenchant(int maxenchant) {
		this.maxenchant = maxenchant;
	}
	
	

}
