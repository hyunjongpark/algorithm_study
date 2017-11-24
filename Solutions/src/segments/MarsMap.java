package segments;
/*
 * https://www.acmicpc.net/problem/3392
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MarsMap {

	static int N = 5;

	static public class Point {
		int x;
		int sy;
		int ey;
		boolean isStart = false;

		public Point(int x, int y1, int y2, boolean isStart) {
			this.x = x;
			this.sy = y1;
			this.ey = y2;
			this.isStart = isStart;
		}
	}

	static Point[] list;
	static long[] tree;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("marsmap"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int pointSize = Integer.parseInt(br.readLine());
		list = new Point[pointSize * 2];
		StringTokenizer st;
		int listIndex = 0;
		for (int i = 0; i < pointSize; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken()) - 1;
			list[listIndex++] = new Point(sx, sy, ey, true);
			list[listIndex++] = new Point(ex, sy, ey, false);
		}

		Arrays.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x > o2.x) {
					return 1;
				} else if (o1.x < o2.x) {
					return -1;
				} else {
					if (o1.sy > o2.sy) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});

		int depth = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = (int) Math.pow(2, depth + 1);
		tree = new long[size];
		

		int idx = 0;
		long result = 0;
		for (int i = 0; i < list.length; i++) {
			Point p = list[i];
			int xValue = p.x - idx;
			idx = p.x;
			result += xValue * tree[1];
			update_h(1, 0, N, list[i].sy, list[i].ey, list[i].isStart);
		}

		System.out.println(result);
	}

	static long update_h(int node, int start, int end, int left, int right, boolean isStart) {
		if (right < start || end < left) {
			if (start == end) {
				if (tree[node] == 0) {
					return 0;
				} else {
					return 1;
				}
			}
			return tree[node];
		}
		if (start == end) {
			if (isStart) {
				tree[node] += 1;
			} else {
				tree[node] -= 1;
			}
			if (tree[node] == 0) {
				return 0;
			} else {
				return 1;
			}
		}

		int mid = (start + end) / 2;
		tree[node] = update_h(node * 2, start, mid, left, right, isStart)
				+ update_h(node * 2 + 1, mid + 1, end, left, right, isStart);
		return tree[node];
	}

}
