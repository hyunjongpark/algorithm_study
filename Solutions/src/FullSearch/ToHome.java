package FullSearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ToHome {

	static int N;
	static int[][] LINK;
	static int[][] check;
	static int minValue = 99999999;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("tohome"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		LINK = new int[N + 1][N + 1];
		check = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			LINK[a][b] = LINK[b][a] = w;
		}
		printMap();
		slove(1, 0);
		System.out.println(minValue);
	}

	static public void slove(int v, int score) {
		if (v == N) {
			minValue = minValue > score ? score : minValue;
			return;
		}

		for (int i = 1; i < N; i++) {
			if (check[v][i] == 0 || LINK[v][i] != 0) {
				check[v][i] = 1;
				slove(i, score + LINK[v][i]);
				check[v][i] = 0;
			}
		}
	}

	static public void printMap() {
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				System.out.print(LINK[i][j] + " ");
			}
			System.out.println();
		}
	}
}
