package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GreedyPanda {
	static boolean debug = true;
	static int N;
	static int[][] map;
	static int[][] dpt;
	static int macCount;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("greedypanda"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dpt = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Arrays.fill(dpt[i], -1);
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		updateTable();
		printTable();
		System.out.println(macCount);
	}

	static void updateTable() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				macCount = Math.max(macCount, recusive(i, j));
			}
		}
	}

	static int recusive(int i, int j) {
		if (dpt[i][j] != -1) {
			return dpt[i][j];
		}
		int count = 0;
		int current = map[i][j];

		// UP
		if (i != 0 && map[i - 1][j] > current) {
			dpt[i][j] = Math.max(dpt[i][j], recusive(i - 1, j) + 1);
		}
		// RIGHT
		if (j != N - 1 && map[i][j + 1] > current) {
			dpt[i][j] = Math.max(dpt[i][j], recusive(i, j + 1) + 1);
		}

		// DOWN
		if (i != N - 1 && map[i + 1][j] > current) {
			dpt[i][j] = Math.max(dpt[i][j], recusive(i + 1, j) + 1);
		}

		// LEFT
		if (j != 0 && map[i][j - 1] > current) {
			dpt[i][j] = Math.max(dpt[i][j], recusive(i, j - 1) + 1);
		}
		dpt[i][j] = Math.max(dpt[i][j], count + 1);
		return dpt[i][j];

	}

	static void printTable() {
		if (!debug) {
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(dpt[i][j] + "	");
			}
			System.out.println();
		}
	}

}
