package test;
/*
 * https://www.acmicpc.net/problem/11266
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class leaderboard {

	static int N;

	static int[] VISITED;
	static boolean[] ISPOINT;
	static int IDX;

	static public class Node {
		int level;
		int start;
		int end;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("leaderboard"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int scoresCount = Integer.parseInt(st.nextToken());
		int[] scores = new int[scoresCount];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < scoresCount; i++) {
			int a = Integer.parseInt(st.nextToken());
			scores[i] = a;
		}
		st = new StringTokenizer(br.readLine());
		int aliceCount = Integer.parseInt(st.nextToken());
		int[] alice = new int[aliceCount];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < aliceCount; i++) {
			int a = Integer.parseInt(st.nextToken());
			alice[i] = a;
		}

		int[] result = climbingLeaderboard(scores, alice);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		Integer[] integerArr = Arrays.stream(scores).boxed().toArray(Integer[]::new);
		Arrays.sort(integerArr, Comparator.reverseOrder());
		int level = 0;
		int lastScore = Integer.MAX_VALUE;
		List<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < integerArr.length; i++) {
			if (lastScore == integerArr[i]) {
				continue;
			}
			level++;
			lastScore = integerArr[i];
			Node node = new Node();
			node.level = level;
			if (i == 0) {
				node.start = Integer.MAX_VALUE;
				node.end = integerArr[i];
			} else {
				node.start = integerArr[i - 1] - 1;
				node.end = integerArr[i];
			}
			nodeList.add(node);
		}
		int[] ret = new int[alice.length];
		for (int i = 0; i < alice.length; i++) {
			ret[i] = binarySearch(alice[i], nodeList);
		}
		return ret;
	}

	public static int binarySearch(int iKey, List<Node> nodeList) {
		int mid;
		int left = 0;
		int right = nodeList.size() - 1;
		while (right >= left) {
			mid = (right + left) / 2;
			if (nodeList.get(mid).start >= iKey && iKey >= nodeList.get(mid).end) {
				return nodeList.get(mid).level;
			}
			if (iKey < nodeList.get(mid).end) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return nodeList.get(right).level + 1;
	}
}
