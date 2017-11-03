package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GoldMine {
	static int N;

	static int[][] arrs;
	static int[][] sameXYCount;
	static int[] countY;

	static public class NodeX implements Comparable<NodeX> {
		int x;
		int y;
		int v;

		public NodeX(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public int compareTo(NodeX o) {
			if (this.x > o.x) {
				return 1;
			} else if (this.x < o.x) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	static public class NodeY implements Comparable<NodeY> {
		int x;
		int y;
		int v;

		public NodeY(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public int compareTo(NodeY o) {
			if (this.y > o.y) {
				return 1;
			} else if (this.y < o.y) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	static ArrayList<NodeX> nodesX;
	static ArrayList<NodeY> nodesY;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("goldmine"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		N = Integer.parseInt(br.readLine());
		nodesX = new ArrayList<NodeX>();
		nodesY = new ArrayList<NodeY>();

		arrs = new int[3000][3000];
		sameXYCount = new int[3000][3000];
		countY = new int[3000];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			nodesX.add(new NodeX(x, y, v));
			nodesY.add(new NodeY(x, y, v));

			arrs[x][y] = v;

			countY[y] = countY[y] + 1;
		}

		for (int y = 0; y < 3000; y++) {

		}

	}

}
