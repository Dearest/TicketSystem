package com.compitation.ticketsystem.scan;

import android.graphics.Color;

public final class RGB {

	public float R;
	public float G;
	public float B;
	
	public int iR;
	public int iG;
	public int iB;
	
	RGB(float fR, float fG, float fB) {
		R = fR; G = fG; B = fB;
	}
	
	RGB(int color) {
		iR = Color.red(color);
		iG = Color.green(color);
		iB = Color.blue(color);
	}
	
}