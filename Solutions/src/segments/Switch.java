package segments;
/*
 * https://www.acmicpc.net/problem/1395
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Switch {
	static int[] tree;
	static int[] lazy;

	static public void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("switch"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int height = (int) Math.ceil(Math.log(n) / Math.log(2));
		int treeSize = (int) Math.pow(2, height + 1);
		tree = new int[treeSize + 1];
		lazy = new int[treeSize + 1];

		init(1, 1, n);

		int tc = Integer.parseInt(st.nextToken());
		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (t == 0) {
				update_ragne(1, 1, n, s, e);
			} else {
				System.out.println(getCount(1, 1, n, s, e));
			}
		}

	}

	static int init(int node, int start, int end) {
		if (start == end) {
			tree[node] = 0;
			return 0;
		}
		int mid = (start + end) / 2;
		tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
		return tree[node];
	}

	static void lzay_update(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] = (end - start + 1) - tree[node];
			if (start != end) {
				lazy[node * 2] = lazy[node * 2] == 1 ? 0 : 1;
				lazy[node * 2 + 1] = lazy[node * 2 + 1] == 1 ? 0 : 1;
			}

			lazy[node] = 0;
		}

	}

	static void update_ragne(int node, int start, int end, int left, int right) {
		lzay_update(node, start, end);
		if (right < start || end < left) {
			return;
		}

		if (left <= start && end <= right) {
			tree[node] = (end - start + 1) - tree[node];
			if (start != end) {
				lazy[node * 2] = lazy[node * 2] == 1 ? 0 : 1;
				lazy[node * 2 + 1] = lazy[node * 2 + 1] == 1 ? 0 : 1;
			}
			return;
		}

		int mid = (start + end) / 2;
		update_ragne(node * 2, start, mid, left, right);
		update_ragne(node * 2 + 1, mid + 1, end, left, right);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static int getCount(int node, int start, int end, int left, int right) {
		lzay_update(node, start, end);
		if (right < start || end < left) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return getCount(node * 2, start, mid, left, right) + getCount(node * 2 + 1, mid + 1, end, left, right);
	}

}
