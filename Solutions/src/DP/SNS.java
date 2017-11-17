package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SNS {

	public static class Node {
		int index;
		int depth;
		int isEarlyAdaptor;
		int isNotEarlyAdaptor;
		ArrayList<Node> list = new ArrayList<>();

		public Node(int i) {
			index = i;
		}
	}

	static Node[] nodes;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sns"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int lineCount = Integer.parseInt(br.readLine());
		nodes = new Node[lineCount + 1];
		for (int i = 0; i <= lineCount; i++) {
			nodes[i] = new Node(i);
		}
		StringTokenizer st;
		for (int i = 0; i < lineCount - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].list.add(nodes[b]);
			nodes[b].list.add(nodes[a]);
		}

		setDepthNumber();

		Arrays.sort(nodes, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.depth > o2.depth) {
					return -1;
				} else if (o1.depth < o2.depth) {
					return 1;
				}
				return 0;
			}
		});
		for (int i = 0; i < lineCount; i++) {
			Node n = nodes[i];
			if (n.list.size() == 0) {
				n.isEarlyAdaptor = 1;
				n.isNotEarlyAdaptor = 0;
			} else {
				for (int j = 0; j < n.list.size(); j++) {
					Node s = n.list.get(j);
					n.isNotEarlyAdaptor += s.isEarlyAdaptor;
					n.isEarlyAdaptor += Math.min(s.isEarlyAdaptor, s.isNotEarlyAdaptor);
				}
				n.isEarlyAdaptor++;
			}
		}
		System.out.println(Math.min(nodes[lineCount - 1].isEarlyAdaptor, nodes[lineCount - 1].isNotEarlyAdaptor));
	}

	static public void setDepthNumber() {
		Node n = nodes[1];
		n.depth = 1;
		Queue<Node> q = new LinkedList<>();
		q.add(n);
		while (!q.isEmpty()) {
			Node s = q.poll();
			for (int i = 0; i < s.list.size(); i++) {
				Node ss = s.list.get(i);
				ss.depth = s.depth + 1;
				q.add(ss);
			}
		}

	}

}
