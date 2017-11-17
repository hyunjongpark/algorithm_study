package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class sample_1111 {

	static public class Node {
		int index;
		ArrayList<Integer> line = new ArrayList<>();

		public Node(int i) {
			index = i;
		}
	}

	static Node[] list;

	static int maxCount = 0;

	static public void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample1111"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());

		int nodeCount = Integer.parseInt(st.nextToken());
		list = new Node[nodeCount + 1];
		for (int i = 0; i <= nodeCount; i++) {
			list[i] = new Node(i);
		}

		int lineCount = Integer.parseInt(st.nextToken());
		for (int i = 0; i < lineCount; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].line.add(b);
			list[b].line.add(a);
		}
		for (int i = 1; i <= nodeCount; i++) {
			Node node = list[i];
			for (int j = 0; j < node.line.size(); j++) {
				maxCount = Math.max(maxCount, (list[node.line.get(j)].line.size() - 1));
			}
		}
		System.out.println(maxCount);

	}

}
