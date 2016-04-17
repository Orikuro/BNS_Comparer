package modell;

import java.util.Comparator;

import comparators.*;

public class AConsts {

	public static Comparator<ComboSoul> COMPARATOR = new Crit_Combo_Comparator();
    
	public static boolean NOCSV = true;
    public static boolean NOINFO = true;
    
    public static int CPUS = 8;
    public static int RESULTS = 30;
    
	
}
