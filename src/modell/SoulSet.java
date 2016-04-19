package modell;

import java.util.Vector;

public class SoulSet {

	private static Vector<SoulSet> SETS = new Vector<>();

	@Override
	public String toString() {
		return name;
	}

	private String name;

	private String buff_3;
	private String buff_5;
	private String buff_8;

	public SoulSet(String name, String buff3, String buff5, String buff8) {
		super();
		this.name = name;
		buff_3 = buff3;
		buff_5 = buff5;
		buff_8 = buff8;
		SETS.add(this);
	}

	public SoulSet(String name, String string2, String string3) {
		super();
		this.name = name;
		buff_3 = string2;
		buff_5 = string3;
		SETS.add(this);
	}
	
	public SoulSet(String name, String content) {
		super();
		this.name = name;
		buff_3 = content;
		SETS.add(this);
	}
	
	public SoulSet(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getBuff(int i) {
		switch (i) {
		case 3:
			return buff_3;
		case 5:
			return buff_3 + ", " + buff_5;
		case 8:
			return buff_3 + ", " + buff_5 + ", " + buff_8;
		}

		return "";
	}

	public static Vector<SoulSet> getSetsWithAll() {
		Vector<SoulSet> temp = new Vector<>();
		temp.addElement(new SoulSet("ALL"));
		temp.addAll(SETS);

		return temp;
	}

	public static Vector<SoulSet> getAllSets() {
		return SETS;
	}

	public static Vector<SoulSet> getSetsWithoutFirst() {
		Vector<SoulSet> temp = new Vector<>();
		temp.addAll(SETS);
		temp.remove(0);
		return temp;
	}

}
