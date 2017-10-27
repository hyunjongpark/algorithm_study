package segments;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AnalogDial {

	static int[] tree;
	static int[] lazy;
	static int[] arr;

	static public void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("analogdial"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int height = (int) Math.ceil(Math.log(n) / Math.log(2));
		int size = (int) Math.pow(2, height + 1);
		tree = new int[size];
		lazy = new int[size];
		arr = new int[n + 1];
		int tc = Integer.parseInt(st.nextToken());
		String arrs = br.readLine();
		for (int i = 1; i <= n; i++) {
			arr[i] = Character.getNumericValue(arrs.charAt(i - 1));
		}
		init(1, 1, n);
		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			System.out.println(getSum(1, 1, n, s, e));
			update_rage(1, 1, n, s, e);
		}
	}

	static int init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
//			System.out.println("[" + node + "] : " + tree[node]);
			return tree[node];
		}
		int mid = (start + end) / 2;
		tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
//		System.out.println("[" + node + "] : " + tree[node]);
		return tree[node];
	}

	static void lazy_update(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if (start != end) {
				lazy[node * 2] += lazy[node * 2] + 1;
				lazy[node * 2 + 1] += lazy[node * 2 + 1] + 1;
			}
			lazy[node] = 0;
		}
	}

	static int getSum(int node, int start, int end, int left, int right) {
		lazy_update(node, start, end);
		if (right < start || end < left) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return getSum(node * 2, start, mid, left, right) + getSum(node * 2 + 1, mid + 1, end, left, right);
	}

	static void update_rage(int node, int start, int end, int left, int right) {
		lazy_update(node, start, end);
		if (right < start || end < left) {
			return;
		}
		if (left <= start && end <= right) {
			tree[node] += (end - start + 1) * 1;
			if (start != end) {
				lazy[node * 2] += lazy[node * 2] + 1;
				lazy[node * 2 + 1] += lazy[node * 2 + 1] + 1;
			}
			return;
		}
		int mid = (start + end) / 2;
		update_rage(node * 2, start, mid, left, right);
		update_rage(node * 2 + 1, mid + 1, end, left, right);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

}
