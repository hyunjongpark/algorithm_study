package DP;
/*
 * https://www.acmicpc.net/problem/3584
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LCA {

	static int N;

	static LinkedList<Integer>[] TREE;
	static int[] PARENT_TREE;

	static int FIND_A;
	static int FIND_B;

	static int FIND_A_DEPTH;
	static int FIND_B_DEPTH;

	static int[] IND;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("lca"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			TREE = new LinkedList[N + 1];
			PARENT_TREE = new int[N + 1];
			IND = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				TREE[i] = new LinkedList<>();
			}

			StringTokenizer st;
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				TREE[a].add(b);
				PARENT_TREE[b] = a;
				IND[b]++;
			}
			st = new StringTokenizer(br.readLine());
			FIND_A = Integer.parseInt(st.nextToken());
			FIND_B = Integer.parseInt(st.nextToken());
			FIND_A_DEPTH = 0;
			FIND_B_DEPTH = 0;
			find_depth_dfs(find_root_index(), 0);
			findLca();
		}
	}

	static int find_root_index() {
		int ret = 1;
		for (int i = 1; i < N - 1; i++) {
			if (IND[i] == 0) {
				return i;
			}
		}
		return ret;
	}

	static void find_depth_dfs(int v, int depth) {
		depth++;
		if (v == FIND_A) {
			FIND_A_DEPTH = depth;
		}
		if (v == FIND_B) {
			FIND_B_DEPTH = depth;
		}
		for (Integer child : TREE[v]) {
			find_depth_dfs(child, depth);
		}
	}

	static void findLca() {

		int a = PARENT_TREE[FIND_A];
		int b = PARENT_TREE[FIND_B];

		if (FIND_A_DEPTH > FIND_B_DEPTH) {
			int p = PARENT_TREE[FIND_A];
			int diff = FIND_A_DEPTH - FIND_B_DEPTH - 1;
			while (diff-- > 0) {
				p = PARENT_TREE[p];
			}
			a = p;
			b = FIND_B;
		} else if (FIND_A_DEPTH < FIND_B_DEPTH) {
			int p = PARENT_TREE[FIND_B];
			int diff = FIND_B_DEPTH - FIND_A_DEPTH - 1;
			while (diff-- > 0) {
				p = PARENT_TREE[p];
			}
			a = FIND_A;
			b = p;
		}

		int a_parent = a;
		int b_parent = b;
		while (a_parent != b_parent) {
			a_parent = PARENT_TREE[a_parent];
			b_parent = PARENT_TREE[b_parent];
		}
		if (a_parent == 0) {
			System.out.println(FIND_A);
		} else {
			System.out.println(a_parent);
		}

	}

}
