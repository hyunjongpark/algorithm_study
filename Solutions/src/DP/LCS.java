package DP;
/*
 * 
 * https://www.acmicpc.net/problem/9251
/	0	A	C	A	Y	K	P	
0	0	0	0	0	0	0	0	
C	0	0	1	1	1	1	1	
A	0	1	1	1	2	2	2	
P	0	1	2	2	2	3	3	
C	0	1	2	2	2	3	3	
A	0	1	2	2	2	3	4	
K	0	1	2	3	3	3	4	
4

 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {
	static boolean debug = true;
	static String firstString;
	static String secondString;
	static int[][] dbt;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("lcs"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		firstString = br.readLine();
		secondString = br.readLine();
		dbt = new int[firstString.length() + 1][secondString.length() + 1];

		updateTable();
		printTable();
		System.out.println(dbt[firstString.length()][secondString.length()]);

	}

	static public void updateTable() {
		for (int i = 1; i <= firstString.length(); i++) {
			for (int j = 1; j <= secondString.length(); j++) {
				if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
					dbt[i][j] = dbt[i - 1][j - 1] + 1;
				} else {
					dbt[i][j] = Math.max(dbt[i - 1][j], dbt[i][j - 1]);
				}

			}
		}
	}

	static public void printTable() {
		if (!debug) {
			return;
		}
		for (int i = 0; i <= firstString.length() + 1; i++) {
			if (i == 0) {
				System.out.print("/	");
			} else if (i == 1) {
				System.out.print(0 + "	");
			} else {
				System.out.print(firstString.charAt(i - 2) + "	");
			}
		}
		System.out.println();

		for (int i = 0; i <= firstString.length(); i++) {
			if (i == 0) {
				System.out.print(0 + "	");
			} else {
				System.out.print(secondString.charAt(i - 1) + "	");
			}
			for (int j = 0; j <= secondString.length(); j++) {
				System.out.print(dbt[i][j] + "	");
			}
			System.out.println();
		}
	}

}
