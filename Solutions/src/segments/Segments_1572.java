package segments;
/*
 * https://www.acmicpc.net/problem/1
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Segments_1572 {
	static int N = 0;
	static int K = 0;
	static int arr[];
	static long tree[];

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("1572"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		int size = (int) Math.pow(2, height() + 1);
		tree = new long[size];
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		init(1, 0, N - 1);
		
	}

	public static long init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			 System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
			return arr[start];
		}

		tree[node] = init(node * 2, start, (start + end) / 2) + init(node * 2 + 1, (start + end) / 2 + 1, end);
		 System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
		return tree[node];
	}

	public static void update(int node, int start, int end, int index, int diff) {
		if (index < start || end < index) {
			return;
		}
		tree[node] = tree[node] + diff;
		// System.out.println("node: " + node + " start: " + start + " end: " +
		// end + " index: " + index+ " tree[node]: " + tree[node]);
		if (start == end) {
			return;
		}
		update(node * 2, start, (start + end) / 2, index, diff);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
	}

	public static long sum(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			// System.out.println(" node: " + node + " start: " + start + " end:
			// " + end + " tree[node]: " + tree[node]);
			return tree[node];
		}
		return sum(node * 2, start, (start + end) / 2, left, right)
				+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
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
