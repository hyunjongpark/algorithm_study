package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class TreePainting {

	static int N;

	static LinkedList<Integer>[] LIST;

	static int[][] dpt;

	static int colorSize = 17;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("treepainting"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		N = Integer.parseInt(br.readLine());
		LIST = new LinkedList[N + 1];
		dpt = new int[N + 1][colorSize + 1];
		for (int i = 0; i <= N; i++) {
			LIST[i] = new LinkedList<>();
		}
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			LIST[a].add(b);
			LIST[b].add(a);
		}

		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= colorSize; i++) {
			dfs(1, i, -1);
			min = Math.min(min, dpt[1][i]);
		}
		System.out.println(min);

	}

	static void dfs(int idx, int colorIndex, int parentIndex) {
		if (dpt[idx][colorIndex] != 0) {
			return;
		}

		int childCount = 0;

		for (int i = 1; i <= colorSize; i++) {
			dpt[idx][i] = i;
		}
		for (Integer next : LIST[idx]) {
			if (next == parentIndex) {
				continue;
			}
			childCount++;
			for (int i = 1; i <= colorSize; i++) {
				dfs(next, i, idx);
			}

			for (int i = 1; i <= colorSize; i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 1; j <= colorSize; j++) {
					if (i != j) {
						min = Math.min(min, dpt[next][j]);
					}
				}
				dpt[idx][i] += min;
			}
		}

		if (childCount == 0) {
			dpt[idx][colorIndex] = colorIndex;
			return;
		}
	}

}
