package DP;
/*
 * https://www.acmicpc.net/problem/1149
R	G	B
26	40	83	
89	86	83	
96	172	185	
96
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB {

	static boolean debug = false;
	static int n;
	static int[][] dpt;
	static int[][] arrs;
	static int result = Integer.MAX_VALUE;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("rgb"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		n = Integer.parseInt(br.readLine());
		dpt = new int[n][3];
		arrs = new int[n][3];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arrs[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0) {
					dpt[i][j] = arrs[i][j];
				}
			}
		}
		updateTable();
		printTable();
		System.out.print(result);
	}

	static void updateTable() {
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					dpt[i][j] = Math.min(dpt[i - 1][1], dpt[i - 1][2]) + arrs[i][j];
				} else if (j == 1) {
					dpt[i][j] = Math.min(dpt[i - 1][0], dpt[i - 1][2]) + arrs[i][j];
				} else if (j == 2) {
					dpt[i][j] = Math.min(dpt[i - 1][0], dpt[i - 1][1]) + arrs[i][j];
				}
				if (i == n - 1) {
					result = Math.min(result, dpt[i][j]);
				}
			}

		}
	}

	static void printTable() {
		if (!debug) {
			return;
		}
		System.out.println("R	G	B");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(dpt[i][j] + "	");
			}
			System.out.println();
		}
	}

}
