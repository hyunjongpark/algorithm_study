package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sample_1118 {

	static int N;
	static boolean[] visited;
	static int[] minLows;
	static int[] ids;
	static int id;
	static ArrayList<ArrayList<Integer>> adj;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample1118"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N + 1];
			minLows = new int[N + 1];
			ids = new int[N + 1];
			adj = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				adj.add(new ArrayList<Integer>());
			}
			StringTokenizer st;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj.get(a).add(b);
				adj.get(b).add(a);
			}

			id = 1;
			articulation(1, -1);
		}

	}

	static void articulation(int v, int parent) {
		visited[v] = true;
		int low = id++;
		minLows[v] = low;
		ids[v] = low;
		
		System.out.println("low : " + v);

		ArrayList<Integer> edges = adj.get(v);
		for (int i = 0; i < edges.size(); i++) {
			int nextV = edges.get(i);
			if (parent == nextV) {
				continue;
			}
			if (visited[nextV] == false) {
				articulation(nextV, v);
				minLows[v] = Math.min(minLows[v], minLows[nextV]);
				if (minLows[nextV] >= ids[v]) {
					System.out.println("articulatorin: " + v);
				}
			} else {
				minLows[v] = Math.min(minLows[v], ids[nextV]);
			}
		}
		if (parent == v && edges.size() > 1) {
			System.out.println("articulatorin: " + v);
		}
	}

}
