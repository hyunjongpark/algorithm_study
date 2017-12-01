package segments;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/9426
 */

public class CenterValue {

	static int N;
	static int K;
	static int[] arrs;
	static int[] tree;
	static int size = 65535 + 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("centervalue"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arrs = new int[N + 1];
		tree = new int[size * 4];
		for (int i = 0; i < N; i++) {
			arrs[i] = Integer.parseInt(br.readLine());
		}

		int j;
		for (j = 0; j < K; j++) {
			set(1, 0, size, arrs[j]);
		}
		long output = 0;
		output += getValue(1, 0, size, (K + 1) / 2);

		for (int i = 0; i < N - K; i++) {
			set(1, 0, size, arrs[j]);
			remove(1, 0, size, arrs[j - K]);
			output += getValue(1, 0, size, (K + 1) / 2);
			j++;
		}
		System.out.println(output);

	}

	public static void set(int node, int start, int end, int index) {
		if (index < start || end < index) {
			return;
		}

		if (start == end) {
			tree[node] += 1;
			return;
		}

		int mid = (end + start) / 2;
		set(node * 2, start, mid, index);
		set(node * 2 + 1, mid + 1, end, index);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	public static void remove(int node, int start, int end, int index) {
		if (index < start || end < index) {
			return;
		}

		if (start == end) {
			tree[node] -= 1;
			return;
		}

		int mid = (end + start) / 2;
		remove(node * 2, start, mid, index);
		remove(node * 2 + 1, mid + 1, end, index);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static int getValue(int node, int start, int end, int index) {
		if (start == end) {
			return start;
		}
		int mid = (start + end) / 2;
		int left = tree[node * 2];
		if (left >= index) {
			return getValue(node * 2, start, mid, index);
		} else {
			return getValue(node * 2 + 1, mid + 1, end, index - left);
		}
	}
}
