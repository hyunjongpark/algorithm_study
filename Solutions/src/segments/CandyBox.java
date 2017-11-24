package segments;
/*
 * https://www.acmicpc.net/problem/2243
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CandyBox {
	static int N = 1000000;
	static int M = 0;
	static int K = 0;
	static long[] arr = null;
	static long[] tree = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("candybox"));
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer token;
        
        
		int T = Integer.parseInt(br.readLine());
		int size = (int) Math.pow(2, height() + 1);
		tree = new long[size];
		arr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = 0;
		}
		init(1, 0, N - 1);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			token = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(token.nextToken());
			if (type == 2) {
				int changeIndex = Integer.parseInt(token.nextToken()) - 1;
				int changeValue = Integer.parseInt(token.nextToken());
				arr[changeIndex] = arr[changeIndex] + changeValue;
				update(1, 0, N - 1, changeIndex, changeValue);
			} else {
				int index = Integer.parseInt(token.nextToken());
				int r = findIndex(1, 0, N - 1, index, -1);
				sb.append(r + "\n");
			}
		}
		System.out.print(sb);
	}

	public static long init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
//			System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
			return arr[start];
		}

		tree[node] = init(node * 2, start, (start + end) / 2) + init(node * 2 + 1, (start + end) / 2 + 1, end);
//		System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
		return tree[node];
	}

	public static void update(int node, int start, int end, int index, int diff) {
		if (index < start || end < index) {
			return;
		}
		tree[node] = tree[node] + diff;
//		System.out.println("node: " + node + " start: " + start + " end: " + end + " index: " + index + " tree[node]: "+ tree[node]);
		if (start == end) {
			return;
		}
		update(node * 2, start, (start + end) / 2, index, diff);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
	}

	public static int findIndex(int node, int start, int end, int index, int diff) {
		int findNode = start;
//		System.out.println("node: " + node + " start: " + start + " end: " + end + " index: " + index + " tree[node]: "+ tree[node]);
		if (start == end) {
			tree[node] = tree[node] -1;
			return start + 1;
		}
		long saa = sum(1, 0, N - 1, 0, (start + end) / 2);
		if (saa >= index) {
			findNode = findIndex(node * 2, start, (start + end) / 2, index, diff);
			tree[node] = tree[node] - 1;
		} else {
			findNode = findIndex(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
			tree[node] = tree[node] -1;
		}

		return findNode;
	}

	public static long sum(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
//			System.out.println("sum node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
			return tree[node];
		}
		return sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
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
