package modell;

public class SoulSet {
	
	@Override
	public String toString() {
		return name;
	}

	private String name;
	private BNSChar restrictedClass;
	
	private String buff_3;
	private String buff_5;
	private String buff_8;
	

	public SoulSet(String name, String buff3, String buff5, String buff8) {
		super();
		this.name = name;
		buff_3 = buff3;
		buff_5 = buff5;
		buff_8 = buff8;
	}
	
	public SoulSet(String name) {
		super();
		this.name = name;
	}
	
	public void restrict(BNSChar res){
		restrictedClass = res;
	}
	
	public String getName() {
		return name;
	}

}
