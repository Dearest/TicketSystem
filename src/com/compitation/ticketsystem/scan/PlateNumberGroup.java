package com.compitation.ticketsystem.scan;

public final class PlateNumberGroup {
	
	public static final String[] E1 = new String[] {
		"��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
	    "��", "��", "��", "ԥ", "��", "��", "��", "��", "³", "��",
	    "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
	    "��", "��", "��"
	};
	
	public static final String[] E2 = new String[] {
	    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
	    "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", 
	    "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", 
	    "W", "X", "Y", "Z"
	};
	
	public static final String[] E3 = new String[] {
	    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
	    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
	    "U", "V", "W", "X", "Y", "Z"
	}; 
	
	public static final int LenMax = 220;
	public static final int LenMin = 85;
	public static final int WidMax = 70;
	public static final int WidMin = 30;

	public static final float PlateDiv = 3.025f;
	public static final float DDiv = 1.4f;
	
	public static int[] PlateXState = new int[10]; 
	
	public static boolean AlreadyChecked = false;
	
	public static String CPYS = "";
	
}