package segments;
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
	static boolean debug = true;

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
		int height = (int)Math.ceil(Math.log(N)/Math.log(2));
		int size = (int) Math.pow(2, height + 1);
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
		}

		Arrays.sort(sort);
		for (int i = 0; i < N; i++) {
			int a = getArrayIndex(arr, sort[i]);
			int s = sum(1, 0, N - 1, 0, a);
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
		if (!debug) {
			return;
		}
		if (start == end) {
			System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
			return;
		}
		print(node * 2, start, (start + end) / 2);
		print(node * 2 + 1, (start + end) / 2 + 1, end);
		System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
	}

	public static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = 0;
			if (debug) {
				System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
			}
			return;
		}

		tree[node] = 0;
		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		if (debug) {
			System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
		}
	}

	public static void update(int node, int start, int end, int index, int diff) {
		if (index < start || end < index) {
			return;
		}
		if (tree[node] < diff) {
			tree[node] = diff;
		}

		if (debug) {
			System.out.println("node: " + node + " start: " + start + " end: " + end + " index: " + index + " tree[node]: " + tree[node]);
		}
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
			if (debug) {
				System.out.println(" node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
			}
			return tree[node];
		}
		return Math.max(sum(node * 2, start, (start + end) / 2, left, right), sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}
}
