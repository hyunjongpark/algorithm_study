package segments;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class NorthwestwardWind {

	static int pointCount;

	static public class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Point[] pList;

	static int leafNodeCount = 0;

	static long[] tree;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("northwind"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			pointCount = Integer.parseInt(br.readLine());
			pList = new Point[pointCount];

			for (int j = 0; j < pointCount; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				pList[j] = new Point(x, y);

			}
			Arrays.sort(pList, new Comparator<Point>() {
				@Override
				public int compare(Point arg0, Point arg1) {
					if (arg0.y < arg1.y) {
						return -1;
					} else if (arg0.y > arg1.y) {
						return 1;
					} else {
						return 0;
					}
				}
			});

			adjX();
			tree = new long[(leafNodeCount + 1) * 4];
			call();

		}
	}

	static public void adjX() {
		leafNodeCount = 0;
		int temp = 0;
		for (int i = 0; i < pList.length; i++) {
			Point p = pList[i];
			if (temp != p.y) {
				leafNodeCount++;
			}
			temp = p.y;
			p.y = leafNodeCount;
		}
	}

	static public void call() {
		Arrays.sort(pList, new Comparator<Point>() {
			@Override
			public int compare(Point arg0, Point arg1) {
				if (arg0.x > arg1.x) {
					return -1;
				} else if (arg0.x < arg1.x) {
					return 1;
				} else {
					if (arg0.y > arg1.y) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});

		long result = 0;
		for (int i = 0; i < pList.length; i++) {
			Point p = pList[i];
			result += getSum(1, 0, leafNodeCount, 0, p.y);
			update(1, 0, leafNodeCount, p.y);
		}
		System.out.println(result);
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

	static void update(int node, int start, int end, int index) {
		if (index < start || end < index) {
			return;
		}

		tree[node] += 1;
		if (start == end) {
			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, index);
		update(node * 2 + 1, mid + 1, end, index);
	}

}
