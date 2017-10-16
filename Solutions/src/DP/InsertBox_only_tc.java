package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InsertBox_only_tc {
	static boolean debug = true;

	static int T;
	static int[] arr;
	static int[][] DBT;
	static int[][] minValue;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("insertbox"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		T = Integer.parseInt(br.readLine());
		arr = new int[T];
		DBT = new int[T + 1][T];
		minValue = new int[T + 1][T];
		result = 1;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		updateTable();
		printTable();
		printMaxValueTable();
		System.out.println(result);
		br.close();
	}

	static void updateTable() {
		for (int i = 2; i <= T; i++) {
			for (int j = i - 1; j < T; j++) {
				if (i == 2) {
					if (arr[j - 1] <= arr[j]) {
						DBT[i][j] = 2;
						minValue[i][j] = arr[j - 1];
					} else {
						DBT[i][j] = 1;
						minValue[i][j] = arr[j];
					}
					result = Math.max(result, DBT[i][j]);
				} else {
					if (arr[j - (i - 1)] < minValue[i - 1][j]) {
						DBT[i][j] = DBT[i - 1][j] + 1;
						minValue[i][j] = arr[j - (i - 1)];
					} else {
						DBT[i][j] = DBT[i - 1][j];
						minValue[i][j] = minValue[i - 1][j];
					}
					result = Math.max(result, DBT[i][j]);
				}
			}
		}
	}

	static void printTable() {
		if (!debug) {
			return;
		}
		System.out.print("	");
		for (int j = 0; j < T; j++) {
			System.out.print(arr[j] + "	");
		}
		System.out.println();
		for (int i = 2; i <= T; i++) {
			System.out.print(i + "	");
			for (int j = 0; j < T; j++) {
				System.out.print(DBT[i][j] + "	");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void printMaxValueTable() {
		if (!debug) {
			return;
		}
		System.out.print("	");
		for (int j = 0; j < T; j++) {
			System.out.print(arr[j] + "	");
		}
		System.out.println();
		for (int i = 2; i <= T; i++) {
			System.out.print(i + "	");
			for (int j = 0; j < T; j++) {
				System.out.print(minValue[i][j] + "	");
			}
			System.out.println();
		}
		System.out.println();
	}

}
