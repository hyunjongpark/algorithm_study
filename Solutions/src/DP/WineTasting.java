/*
 * https://www.acmicpc.net/problem/2156

 	6	0	6	0	6	0	
	10	6	16	0	10	6	
	13	10	23	6	19	16	
	15	19	28	16	25	23	
	24	25	33	23	31	28	
	24	31	32	28	29	33	
33
 */
package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WineTasting {
	static boolean debug = true;
	static int T;
	static int[][] dpt;
	static int[] arr;
	static int reault;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("winetasting"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		T = Integer.parseInt(br.readLine());
		dpt = new int[T][6];
		arr = new int[T];
		reault = 0;

		for (int i = 0; i < T; i++) {
			int v = Integer.parseInt(br.readLine());
			arr[i] = v;
			if (i < 3) {
				initTable(v, i);
			} else {
				updateTable(v, i);
			}
			printTable();
		}
		printTable();

		System.out.println(reault);

	}

	public static void updateTable(int v, int row) {
		for (int i = 0; i < 6; i++) {
			switch (i) {
			/*
			 * 0: 001 1: 010 2: 011 3: 100 4: 101 5: 110
			 */
			case 0: {
				dpt[row][i] = getMaxValue(new int[] { 0, 1, 2, 3, 4, 5 }, row) + v;
				break;
			}
			case 1: {
				dpt[row][i] = getMaxValue(new int[] { 0, 1, 2, 3, 4, 5 }, row) + arr[row - 1];
				break;
			}
			case 2: {
				dpt[row][i] = getMaxValue(new int[] { 0, 1, 2, 3, 4, 5 }, row) + arr[row - 1] + v;
				break;
			}
			case 3: {
				dpt[row][i] = getMaxValue(new int[] { 0, 1, 3, 4, 5 }, row) + arr[row - 2];
				break;
			}
			case 4: {
				dpt[row][i] = getMaxValue(new int[] { 0, 1, 3, 4, 5 }, row) + arr[row - 2] + v;
				break;
			}
			case 5: {
				dpt[row][i] = getMaxValue(new int[] { 1, 3, 5 }, row) + arr[row - 2] + arr[row - 1];
				break;
			}
			}
			if (reault < dpt[row][i]) {
				reault = dpt[row][i];
			}
		}
	}

	public static void initTable(int v, int row) {
		if (row == 0) {
			for (int i = 0; i < 6; i++) {
				switch (i) {
				case 0: {
					dpt[row][i] = v;
					break;
				}
				case 1: {
					dpt[row][i] = 0;
					break;
				}
				case 2: {
					dpt[row][i] = v;
					break;
				}
				case 3: {
					dpt[row][i] = 0;
					break;
				}
				case 4: {
					dpt[row][i] = v;
					break;
				}
				case 5: {
					dpt[row][i] = 0;
					break;
				}
				}
			}
		} else if (row == 1) {
			for (int i = 0; i < 6; i++) {
				switch (i) {
				case 0: {
					dpt[row][i] = v;
					break;
				}
				case 1: {
					dpt[row][i] = arr[row - 1];
					break;
				}
				case 2: {
					dpt[row][i] = arr[row - 1] + v;
					break;
				}
				case 3: {
					dpt[row][i] = 0;
					break;
				}
				case 4: {
					dpt[row][i] = v;
					break;
				}
				case 5: {
					dpt[row][i] = arr[row - 1];
					break;
				}
				}
			}
		} else if (row == 2) {
			for (int i = 0; i < 6; i++) {
				switch (i) {
				case 0: {
					dpt[row][i] = v;
					break;
				}
				case 1: {
					dpt[row][i] = arr[row - 1];
					break;
				}
				case 2: {
					dpt[row][i] = arr[row - 1] + v;
					break;
				}
				case 3: {
					dpt[row][i] = arr[row - 2];
					break;
				}
				case 4: {
					dpt[row][i] = arr[row - 2] + v;
					break;
				}
				case 5: {
					dpt[row][i] = arr[row - 2] + arr[row - 1];
					break;
				}
				}
			}
		}
	}

	public static void printTable() {
		if (!debug){
			return;
		}
		for (int i = 0; i < T; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(dpt[i][j] + "	");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int getMaxValue(int[] index, int row) {
		int maxValue = 0;
		for (int i : index) {
			if (maxValue < dpt[row - 3][i]) {
				maxValue = dpt[row - 3][i];
			}
		}
		return maxValue;
	}

}
