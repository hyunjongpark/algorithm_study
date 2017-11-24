package DP;
/*
 * https://www.acmicpc.net/problem/11266
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ArticulationPoint {

	static int N;

	static int[] VISITED;
	static boolean[] ISPOINT;
	static int IDX;
	static LinkedList<Integer>[] LIST;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("articucationpoint"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		VISITED = new int[N + 1];
		ISPOINT = new boolean[N + 1];
		IDX = 1;
		LIST = new LinkedList[N + 1];
		for (int i = 0; i <= N; i++) {
			LIST[i] = new LinkedList<Integer>();
		}
		int edgesCount = Integer.parseInt(st.nextToken());
		for (int i = 0; i < edgesCount; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			LIST[a].add(b);
			LIST[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			if (VISITED[i] == 0) {
				ariticulation(i, true);
			}
		}

		int result = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			if (ISPOINT[i] == true) {
				result++;
				sb.append(i + " ");
			}
		}
		System.out.println(result);
		System.out.println(sb.toString());
	}

	static int ariticulation(int v, boolean isRoot) {
		int ret = VISITED[v] = IDX++;

		int childCount = 0;
		for (Integer nextV : LIST[v]) {
			if (VISITED[nextV] == 0) {
				childCount++;
				int min = ariticulation(nextV, false);
				ret = Math.min(ret, min);
				if (!isRoot && min >= VISITED[v]) {
					ISPOINT[v] = true;
				}
			} else {
				ret = Math.min(ret, VISITED[nextV]);
			}
		}

		if (isRoot && childCount > 1) {
			ISPOINT[v] = true;
		}
		return ret;
	}
}
