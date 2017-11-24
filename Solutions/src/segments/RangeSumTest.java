package segments;
/*
 * https://www.acmicpc.net/problem/2042
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RangeSumTest {

	static int N;

	static long[] tree;
	static long[] arrs;

	static public void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("2042"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		tree = new long[N * 4 + 1];
		arrs = new long[N + 1];

		int updateCount = Integer.parseInt(st.nextToken());
		int getCount = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			arrs[i] = Integer.parseInt(br.readLine());
		}
		init(1, 0, N);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < updateCount + getCount; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				arrs[b - 1] = c;
				update(1, 0, N, b - 1);
			} else {
				long r = getSum(1, 0, N, b - 1, c - 1);
				sb.append(r + "\n");
			}
		}
		System.out.println(sb.toString());
	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arrs[start];
			return;
		}
		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void update(int node, int start, int end, int index) {
		if (index < start || end < index) {
			return;
		}
		if (start == end) {
			tree[node] = arrs[start];
			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, index);
		update(node * 2 + 1, mid + 1, end, index);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long getSum(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return getSum(node * 2, start, mid, left, right) + getSum(node * 2 + 1, mid + 1, end, left, right);
	}

}
