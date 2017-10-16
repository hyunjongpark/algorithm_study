package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * https://www.acmicpc.net/problem/2662
 * 
ARRS
0	0	0	0	0	
0	5	6	7	10	
0	1	5	9	15	
BPT
0	0	0	0	0	
0	5	6	7	10	
0	5	6	10	15	
PATH
0	0	0	0	0	
0	1	2	3	4	
0	0	0	2	4	
15
0 4 
 */

public class CorporateInvestment {
	static boolean debug = false;
	static int money;
	static int company;
	static int[][] arrs;
	static int[][] dp;
	static int[][] path;
	static int[] CorporateInvestment;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("corporateinvestment"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		money = Integer.parseInt(st.nextToken());
		company = Integer.parseInt(st.nextToken());
		arrs = new int[company + 1][money + 1];
		dp = new int[company + 1][money + 1];
		path = new int[company + 1][money + 1];
		CorporateInvestment = new int[company + 1];
		for (int i = 1; i <= money; i++) {
			st = new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken());
			for (int j = 1; j <= company; j++) {
				arrs[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		updateTable();
		printTable();
		calculationPath();

		StringBuffer sb = new StringBuffer();
		sb.append(dp[company][money] + "\n");
		for (int i = 1; i <= company; i++) {
			sb.append(CorporateInvestment[i] + " ");
		}
		sb.append("\n");
		System.out.println(sb.toString());
	}

	static void calculationPath() {
		int restMoney = money;
		for (int i = company; i > 0; i--) {
			int p = path[i][restMoney];
			CorporateInvestment[i] = p;
			restMoney -= p;
		}

	}

	static void updateTable() {
		int temp = 0;
		for (int i = 1; i <= company; i++) {
			for (int j = 1; j <= money; j++) {
				for (int k = 0; k <= j; k++) {
					temp = arrs[i][k] + dp[i - 1][j - k];
					if (dp[i][j] < temp) {
						dp[i][j] = temp;
						path[i][j] = k;
					}
				}
			}
		}
	}

	static void printTable() {
		if (!debug) {
			return;
		}
		System.out.println("ARRS");
		for (int i = 0; i <= company; i++) {
			for (int j = 0; j <= money; j++) {
				System.out.print(arrs[i][j] + "	");
			}
			System.out.println();
		}

		System.out.println("BPT");
		for (int i = 0; i <= company; i++) {
			for (int j = 0; j <= money; j++) {
				System.out.print(dp[i][j] + "	");
			}
			System.out.println();
		}

		System.out.println("PATH");
		for (int i = 0; i <= company; i++) {
			for (int j = 0; j <= money; j++) {
				System.out.print(path[i][j] + "	");
			}
			System.out.println();
		}
	}

}
