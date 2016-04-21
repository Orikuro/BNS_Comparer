package modell;

public class SoulShield {

	private SoulSet set;
	private int position;
	private int maxenchant;

	private int hp = 0;
	private int accuracy = 0;

	private int crit = 0;
	private int def = 0;
	private int block = 0;
	private int evasion = 0;
	private int critdef = 0;

	public SoulShield(String name, String pos, String max) {
		set = SoulSet.getSetByName(name.trim());
		position = new Integer(pos.trim());
		maxenchant = new Integer(max.trim());

	}

	public SoulShield(SoulShield base) {
		this.set = base.getSet();
		this.position = base.getPosition();
		this.maxenchant = base.getMaxenchant();
		this.hp = base.getHp();
		this.accuracy = base.getAccuracy();
		this.crit = base.getCrit();
		this.def = base.getDef();
		this.block = base.getBlock();
		this.evasion = base.getEvasion();
		this.critdef = base.getCritdef();
	}

	public int calcTotalAttributeBonus() {
		return accuracy + crit + def + block + evasion + critdef;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getCritdef() {
		return critdef;
	}

	public void setCritdef(int critdef) {
		this.critdef = critdef;
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

	public void add(String string) {

		if (string == null || string.length() == 0)
			return;

		String temp = string.trim();

		if (temp.endsWith("hp")) {
			hp += new Integer(temp.substring(0, temp.indexOf("hp")).trim());
		}
		if (temp.endsWith("accu")) {
			accuracy += new Integer(temp.substring(0, temp.indexOf("accu")).trim());
		}
		if (temp.endsWith("eva")) {
			evasion += new Integer(temp.substring(0, temp.indexOf("eva")).trim());
		}
		if (temp.endsWith("critdef")) {
			critdef += new Integer(temp.substring(0, temp.indexOf("critdef")).trim());
		} else if (temp.endsWith("def")) {
			def += new Integer(temp.substring(0, temp.indexOf("def")).trim());
		}
		if (temp.endsWith("crit")) {
			crit += new Integer(temp.substring(0, temp.indexOf("crit")).trim());
		}
		if (temp.endsWith("block")) {
			block += new Integer(temp.substring(0, temp.indexOf("block")).trim());
		}
	}

	public void addLimited(String string) {

		if (string == null || string.length() == 0)
			return;

		String temp = string.trim();

		int ench = 0;

		if (temp.endsWith("accu")) {
			ench = new Integer(temp.substring(0, temp.indexOf("accu")).trim());
			if (ench > maxenchant) {
				ench = maxenchant;
			}
			accuracy += ench;
		}
		if (temp.endsWith("eva")) {
			ench = new Integer(temp.substring(0, temp.indexOf("eva")).trim());
			if (ench > maxenchant) {
				ench = maxenchant;
			}
			evasion += ench;
		}
		if (temp.endsWith("critdef")) {
			ench = new Integer(temp.substring(0, temp.indexOf("critdef")).trim());
			if (ench > maxenchant) {
				ench = maxenchant;
			}
			critdef += ench;
		} else if (temp.endsWith("def")) {
			ench = new Integer(temp.substring(0, temp.indexOf("def")).trim());
			if (ench > maxenchant) {
				ench = maxenchant;
			}
			def += ench;
		}
		if (temp.endsWith("crit")) {
			ench = new Integer(temp.substring(0, temp.indexOf("crit")).trim());
			if (ench > maxenchant) {
				ench = maxenchant;
			}
			crit += ench;
		}
		if (temp.endsWith("block")) {
			ench = new Integer(temp.substring(0, temp.indexOf("block")).trim());
			if (ench > maxenchant) {
				ench = maxenchant;
			}
			block += ench;
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(set);
		builder.append(" = ");
		builder.append(position);
		builder.append(", hp=");
		builder.append(hp);
		if (accuracy > 0) {
			builder.append(", accuracy=");
			builder.append(accuracy);
		}
		if (crit > 0) {
			builder.append(", crit=");
			builder.append(crit);
		}
		if (def > 0) {
			builder.append(", def=");
			builder.append(def);
		}
		if (block > 0) {
			builder.append(", block=");
			builder.append(block);
		}
		if (evasion > 0) {
			builder.append(", evasion=");
			builder.append(evasion);
		}
		if (critdef > 0) {
			builder.append(", critdef=");
			builder.append(critdef);
		}
		return builder.toString();
	}

}
