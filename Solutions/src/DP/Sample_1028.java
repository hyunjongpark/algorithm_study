package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Sample_1028 {

	static int N;
	static int K;
	static int arrs[];

	static public class Node implements Comparable<Node> {
		public int money;
		public int count = 0;
		public ArrayList<Node> list = new ArrayList<Node>();

		public Node(int money) {
			this.money = money;
		}

		@Override
		public int compareTo(Node o) {
			if (this.money < o.money) {
				return 1;
			} else if (this.money > o.money) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	static ArrayList<Node> nodes;
	static ArrayList<Node> sortNodes;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample1028"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arrs = new int[N + 1];
			nodes = new ArrayList<Node>();
			nodes.add(new Node(-1));
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arrs[j] = Integer.parseInt(st.nextToken());
				nodes.add(new Node(arrs[j]));
			}
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				nodes.get(s).list.add(nodes.get(e));
				nodes.get(e).list.add(nodes.get(s));
			}
			Collections.sort(nodes);
			for (int j = 1; j < nodes.size(); j++) {
				Node node = nodes.get(j);
				for (int t = 0; t < node.list.size(); t++) {
					Node subNode = node.list.get(t);
					if (node.money < subNode.money) {
						node.count += (subNode.count + 1);
					}
				}
			}
			int pathCount = 0;
			for (int j = 1; j < nodes.size(); j++) {
				Node node = nodes.get(j);
				pathCount += node.count;
			}
			System.out.println(pathCount % 1000000007);
		}
	}
}
