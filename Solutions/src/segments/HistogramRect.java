package segments;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HistogramRect {

	static int leafNodeCount;
	static long[] arrs;
	static long[] tree;
	static long resut;

	static public void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("histogramrect"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			leafNodeCount = Integer.parseInt(st.nextToken());
			if (leafNodeCount == 0) {
				return;
			}
			arrs = new long[leafNodeCount + 1];
			for (int i = 1; i <= leafNodeCount; i++) {
				arrs[i] = Integer.parseInt(st.nextToken());
			}

			int height = (int) Math.ceil(Math.log(leafNodeCount) / Math.log(2));
			int size = (int) Math.pow(2, height + 1);
			tree = new long[size];
			init(1, 1, leafNodeCount);
			maxRect(1, leafNodeCount);
			System.out.println(resut);
		}
	}

	static public void maxRect(int start, int end) {
		if (start > leafNodeCount || end > leafNodeCount){
			return;
		}
		if (start > end) {
			end = start;
		}
		long minH = get(1, 1, leafNodeCount, start, end);
		resut = Math.max(resut, minH * (end - start + 1));
		if (start == end) {
			return;
		}
		int findMinHIndex = 0;
		for (int i = start; i <= end; i++) {
			if (arrs[i] == minH) {
				findMinHIndex = i;
				resut = Math.max(resut, minH);
				break;
			}
		}

		maxRect(start, findMinHIndex - 1);
		maxRect(findMinHIndex + 1, end);
	}

	static public long init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = arrs[start];
		}
		int mid = (end + start) / 2;
		tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
		return tree[node];
	}

	static public long get(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;

		return Math.min(get(node * 2, start, mid, left, right), get(node * 2 + 1, mid + 1, end, left, right));

	}

}
