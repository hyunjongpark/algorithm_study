package DP;
/*
 * https://www.acmicpc.net/problem/10835
 * 
	2	4	1	
3	0	2	-1	-1	
2	0	2	2	3	
5	0	2	6	7	

7
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CardGame {

	static boolean debug = false;
	static int cardCount;
	static int[] L;
	static int[] R;
	static int[][] LR;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("cardgame"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		cardCount = Integer.parseInt(br.readLine());
		L = new int[cardCount + 1];
		R = new int[cardCount + 1];
		LR = new int[cardCount + 1][cardCount + 1];
		result = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cardCount; i++) {
			L[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(LR[i], -1);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cardCount; i++) {
			R[i] = Integer.parseInt(st.nextToken());
		}
		LR[0][0] = 0;
		updateTable();

		System.out.println(result);

	}

	public static void updateTable() {
		for (int i = 0; i <= cardCount; i++) {
			for (int j = 0; j <= cardCount; j++) {
				if (LR[i][j] == -1) {
					break;
				}

				// case L
				if (i != cardCount && LR[i + 1][j] < LR[i][j]) {
					LR[i + 1][j] = LR[i][j];
					if (result < LR[i][j]) {
						result = LR[i][j];
					}
				}

				// case LR
				if (i != cardCount && j != cardCount && LR[i + 1][j + 1] < LR[i][j]) {
					LR[i + 1][j + 1] = LR[i][j];
					if (result < LR[i][j]) {
						result = LR[i][j];
					}
				}

				// case R
				if (j != cardCount && L[i] > R[j]) {
					if (LR[i][j + 1] < LR[i][j] + R[j]) {
						LR[i][j + 1] = LR[i][j] + R[j];
						if (result < LR[i][j]) {
							result = LR[i][j];
						}
					}
				}
				printTable();
			}
		}
	}

	public static void printTable() {
		if (!debug) {
			return;
		}
		System.out.print("	");
		for (int i = 0; i < cardCount; i++) {
			System.out.print(R[i] + "	");
		}
		System.out.println();

		for (int i = 0; i < cardCount; i++) {
			for (int j = 0; j <= cardCount; j++) {
				if (j == 0 && i != cardCount) {
					System.out.print(L[i] + "	");
				} else if (j == 0 && i == cardCount) {
					System.out.print("	");
				}
				System.out.print(LR[i][j] + "	");
			}
			System.out.println();
		}
		System.out.println();
	}

}
