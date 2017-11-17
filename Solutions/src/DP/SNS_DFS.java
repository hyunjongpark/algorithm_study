package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SNS_DFS {

	static public class Node {
		int parentIndex;
		int index;
		int none;
		int earlyAdaptor;
		ArrayList<Node> list = new ArrayList<>();

		public Node(int i) {
			this.index = i;
		}
	}

	static Node[] list;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sns"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int N = Integer.parseInt(br.readLine());
		list = new Node[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new Node(i);
		}
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].list.add(list[b]);
			list[b].list.add(list[a]);
		}
		dfs(list[1]);
		System.out.println(Math.min(list[1].none, list[1].earlyAdaptor));
	}

	static void dfs(Node node) {
		if (node.list.size() == 0) {
			node.none = 0;
			node.earlyAdaptor = 1;
			return;
		}
		node.none = 0;
		node.earlyAdaptor = 1;
		for (int i = 0; i < node.list.size(); i++) {
			Node n = node.list.get(i);
			if (n.index == node.parentIndex) {
				continue;
			}
			n.parentIndex = node.index;
			dfs(n);
			node.none += n.earlyAdaptor;
			node.earlyAdaptor += Math.min(n.none, n.earlyAdaptor);
		}
	}

}
