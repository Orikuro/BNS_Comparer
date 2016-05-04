package modell;

import java.util.Vector;

public class SoulSet {

	private static Vector<SoulSet> SETS = new Vector<>();
	public int id;
	private static int COUNTER = 0;

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
		id = COUNTER++;
	}

	public SoulSet(String name, String string2, String string3) {
		super();
		this.name = name;
		buff_3 = string2;
		buff_5 = string3;
		SETS.add(this);
		id = COUNTER++;
	}
	
	public SoulSet(String name, String content) {
		super();
		this.name = name;
		buff_3 = content;
		SETS.add(this);
		id = COUNTER++;
	}
	
	public SoulSet(String name) {
		super();
		this.name = name;
		id = -1;
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

	public static Vector<SoulSet> getSetsWithNone() {
		Vector<SoulSet> temp = new Vector<>();
		temp.addElement(new SoulSet("None"));
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoulSet other = (SoulSet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static SoulSet getSetByName(String trim) {

		for (SoulSet s : SETS){
			if (trim.equals(s.getName())){
				return s;
			}
		}
		System.err.println("Set not found!");
		
		return null;
	}

}
