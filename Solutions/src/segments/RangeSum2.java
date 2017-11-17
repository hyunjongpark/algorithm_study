package segments;
/*
 * https://www.acmicpc.net/problem/10999
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class RangeSum2 {
	static int N, M, K;
	static long arr[];
	static long lazy[];
	static long tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("10999"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int height = (int)(Math.ceil(Math.log(N) / Math.log(2)));
		int maxSize = (int)(Math.pow(2, height + 1) - 1);
		tree = new long[maxSize + 1];
		lazy = new long[maxSize + 1];
		arr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		init(1, 1, N);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (type == 1) {
				int diff = Integer.parseInt(st.nextToken());
				updateRange(1, 1, N, diff, start, end);
			} else {
				long ret = sum(1, 1, N, start, end);
				sb.append(ret + "\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	public static long init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
//			 System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
			return arr[start];
		}

		tree[node] = init(node * 2, start, (start + end) / 2) + init(node * 2 + 1, (start + end) / 2 + 1, end);
//		 System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + tree[node]);
		return tree[node];
	}
	
	public static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += (lazy[node] * (end - start + 1));
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	public static void updateRange(int node, int left, int right, long diff, int start, int end) {
//		System.out.println("stIdx: " + start + " edIdx: " + end + " node: " + node + " stRange: " + left + " edRange: " + right);
		update_lazy(node, left, right);
		if (right < start || end < left) {
			return;
		}
		if (start <= left && right <= end) {
			tree[node] += (diff * (right - left + 1));
			if (left != right) {
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			return;
		}
		int mid = (left + right) / 2;
		updateRange(node * 2, left, mid, diff, start, end);
		updateRange(node * 2 + 1, mid + 1, right, diff, start, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}


	public static long sum(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);
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
}

