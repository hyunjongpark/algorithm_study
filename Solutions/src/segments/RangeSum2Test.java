package segments;
/*
 * https://www.acmicpc.net/problem/10999
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RangeSum2Test {

	static int N;

	static long[] tree;
	static long[] lazy;

	static long[] arr;

	static public void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("10999"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int tc = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		arr = new long[N + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		tree = new long[N * 4 + 1];
		lazy = new long[N * 4 + 1];
		init(1, 0, N);
		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			if (a == 1) {
				int d = Integer.parseInt(st.nextToken());
				update(1, 0, N, b, c, d);
			} else {
				System.out.println(get(1, 0, N, b, c));
			}
		}

		System.out.println();

	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}
		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void lazy_update(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += ((end - start + 1) * lazy[node]);
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	static void update(int node, int start, int end, int left, int right, long diff) {
		lazy_update(node, start, end);
		if (right < start || end < left) {
			return;
		}
		if (left <= start && end <= right) {
			tree[node] += ((end - start + 1) * diff);
			if (start != end) {
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			return;
		}
		int mid = (start + end) / 2;
		update(node * 2, start, mid, left, right, diff);
		update(node * 2 + 1, mid + 1, end, left, right, diff);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long get(int node, int start, int end, int left, int right) {
		lazy_update(node, start, end);
		if (right < start || end < left) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		return get(node * 2, start, mid, left, right) + get(node * 2 + 1, mid + 1, end, left, right);

	}

}
