package DP;
/*
 * https://www.acmicpc.net/problem/2965
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ThreeKangaroos {

	static int[][][] dpt;

	static public void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("Threekangaroos"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		dpt = new int[100][100][100];

		int left = Integer.parseInt(st.nextToken());
		int center = Integer.parseInt(st.nextToken());
		int right = Integer.parseInt(st.nextToken());
		System.out.println(dp(left, center, right));
	}

	static int dp(int l, int c, int r) {
		if (r - l < 3) {
			return 0;
		}
		if (dpt[l][c][r] != 0) {
			return dpt[l][c][r];
		}

		int temp = 0;
		for (int i = l + 1; i < c; i++) {
			temp = Math.max(dp(l, i, c) + 1, temp);
		}

		for (int i = c + 1; i < r; i++) {
			temp = Math.max(dp(c, i, r) + 1, temp);
		}

		return dpt[l][c][r] = temp;
	}

}
