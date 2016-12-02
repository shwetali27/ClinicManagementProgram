package com.bridgelabz.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {
	BufferedReader br = null;

	// Utility constructor for creating object for BufferedReader
	public Utility() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	// input string
	public String inputString() {
		try {
			return br.readLine();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	// input Integer
	public int inputInteger() {
		try {
			return Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	// input double
	public double inputDouble() {
		try {
			return Double.parseDouble(br.readLine());
		} catch (Exception e) {
			System.out.println(e);
		}
		new BufferedReader(new InputStreamReader(System.in));
		return 0.0;
	}

	// input boolean
	public boolean inputBoolean() {
		try {
			return Boolean.parseBoolean(br.readLine());
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
