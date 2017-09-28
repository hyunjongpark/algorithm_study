package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 *
 * https://algospot.com/judge/problem/read/COINS
	1	5	10	50	
1	1	0	0	0	
2	1	0	0	0	
3	1	0	0	0	
4	1	0	0	0	
5	1	1	0	0	
6	1	1	0	0	
7	1	1	0	0	
8	1	1	0	0	
9	1	1	0	0	
10	1	2	1	0	
11	1	2	1	0	
4
 */
public class Coins {
	static boolean debug = true;
	static int price;
	static int priceStep;
	static long[][] DBT;
	static int[] hasPrice;
	static ArrayList<Integer> stepPrice;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("coins"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			price = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			hasPrice = new int[count];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < count; j++) {
				hasPrice[j] = Integer.parseInt(st.nextToken());
			}
			priceStep = price / 1;
			stepPrice = new ArrayList<Integer>();
			DBT = new long[priceStep][hasPrice.length];

			for (int j = 0; j < priceStep; j++) {
				stepPrice.add((j + 1));
			}
			updateTable();
			if (debug) {
				printTable();
			}

			long result = 0;
			for (int j = 0; j < hasPrice.length; j++) {
				result += DBT[priceStep - 1][j];
			}

			System.out.println(result % 1000000007);
		}
	}

	static void updateTable() {
		for (int i = 0; i < priceStep; i++) {
			int rowPrice = (i + 1);
			for (int j = 0; j < hasPrice.length; j++) {
				if (rowPrice < hasPrice[j]) {
					break;
				}
				if (rowPrice == hasPrice[j]) {
					DBT[i][j] = 1;
				} else {
					int prePriceIndex = stepPrice.indexOf(rowPrice - hasPrice[j]);
					DBT[i][j] = DBT[prePriceIndex][j];
					for (int t = j - 1; t >= 0; t--) {
						DBT[i][j] += DBT[prePriceIndex][t];
					}
				}
			}
		}
	}

	static void printTable() {
		System.out.print("	");
		for (int j = 0; j < hasPrice.length; j++) {
			System.out.print(hasPrice[j] + "	");
		}
		System.out.println();
		for (int i = 0; i < priceStep; i++) {
			stepPrice.add((i + 1));
			System.out.print(stepPrice.get(i) + "	");
			for (int j = 0; j < hasPrice.length; j++) {
				System.out.print(DBT[i][j] + "	");
			}
			System.out.println();
		}
	}

}
