package segments;
/*
 * https://www.acmicpc.net/problem/9426
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CenterValue {

	static int N;

	static int[] TREE;
	static int SIZE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("centervalue"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int height = (int) Math.ceil(Math.log(12) / Math.log(2));
		int size = (int) Math.pow(2, height + 1);

		SIZE = 12 * 4 + 1;
		TREE = new int[SIZE+1];
		int check = Integer.parseInt(st.nextToken());

		int min = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(br.readLine());
			
			
			set(1, 0, size, v);
			System.out.println(TREE[1]);
			remove(1, 0, size, v);
			System.out.println(TREE[1]);
		}
	}

	static void set(int node, int start, int end, int index) {
		if (index < start || end < index) {
			return;
		}
		if (start == end) {
			TREE[node] += 1;
			return;
		}
		int mid = (start + end) / 2;
		set(node * 2, start, mid, index);
		set(node * 2 + 1, mid + 1, end, index);
		TREE[node] = TREE[node * 2] + TREE[node * 2 + 1];
	}
	
	static void remove(int node, int start, int end, int index) {
		if (index < start || end < index) {
			return;
		}
		if (start == end) {
			TREE[node] -= 1;
			return;
		}
		int mid = (start + end) / 2;
		remove(node * 2, start, mid, index);
		remove(node * 2 + 1, mid + 1, end, index);
		TREE[node] = TREE[node * 2] + TREE[node * 2 + 1];
	}

}
