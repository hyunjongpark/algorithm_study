package DP;
/*
 * LIS
 * https://www.acmicpc.net/problem/11568
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SchemeOfMinkyun {

	static int N;
	static int arr[];
	static int sort[];
	static int tree[];
	static boolean[] sortCheck;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("minkyun"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		N = Integer.parseInt(br.readLine());
		int size = (int) Math.pow(2, height() + 1);
		tree = new int[size];
		init(1, 0, N - 1);

		arr = new int[N];
		sort = new int[N];
		sortCheck = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sort[i] = arr[i];
			sortCheck[i] = false;
			// System.out.println(sort[i]);
		}
		// System.out.println("-----");

		Arrays.sort(sort);
		for (int i = 0; i < N; i++) {
			int a = getArrayIndex(arr, sort[i]);
			// System.out.println(sort[i] + " - " + a);
			int s = sum(1, 0, N - 1, 0, a);
			// System.out.println("LIS: " + s);
			update(1, 0, N - 1, a, s + 1);
			print(1, 0, N - 1);
		}
		System.out.println(tree[1]);
	}

	public static int getArrayIndex(int[] arr, int v) {
		for (int i = arr.length - 1; i >= 0; i--) {
			if (sortCheck[i] == true) {
				continue;
			}
			if (arr[i] == v) {
				sortCheck[i] = true;
				return i;
			}
		}
		return -1;
	}

	public static void print(int node, int start, int end) {
		if (start == end) {
			// System.out.println("node: " + node + " start: " + start + " end:
			// " + end + " tree[node]: " + tree[node]);
			return;
		}
		print(node * 2, start, (start + end) / 2);
		print(node * 2 + 1, (start + end) / 2 + 1, end);
		// System.out.println("node: " + node + " start: " + start + " end: " +
		// end + " tree[node]: " + tree[node]);
	}

	public static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = 0;
			// System.out.println("node: " + node + " start: " + start + " end:
			// " + end + " tree[node]: " + tree[node]);
			return;
		}

		tree[node] = 0;
		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		// System.out.println("node: " + node + " start: " + start + " end: " +
		// end + " tree[node]: " + tree[node]);
	}

	public static void update(int node, int start, int end, int index, int diff) {
		if (index < start || end < index) {
			return;
		}
		if (tree[node] < diff) {
			tree[node] = diff;
		}

		// System.out.println("node: " + node + " start: " + start + " end: " +
		// end + " index: " + index + " tree[node]: "+ tree[node]);
		if (start == end) {
			return;
		}
		update(node * 2, start, (start + end) / 2, index, diff);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
	}

	public static int sum(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			// System.out.println(" node: " + node + " start: " + start + " end:
			// " + end + " tree[node]: " + tree[node]);
			return tree[node];
		}
		return Math.max(sum(node * 2, start, (start + end) / 2, left, right),
				sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}

	private static int height() {
		int i = 1;
		while (true) {
			if (Math.pow(2, i) > N) {
				break;
			}
			i++;
		}
		return i;
	}
}
