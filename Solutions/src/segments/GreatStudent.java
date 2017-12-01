package segments;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class GreatStudent {

	static int N;
	static int[] TREE;

	static int[] test1;
	static int[] test2;
	static int[] test3;

	static public class TEST {
		int x;
		int y;
		int z;
	}

	static TEST[] LIST;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("greatstudent"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		N = Integer.parseInt(br.readLine());
		TREE = new int[N * 4];
		Arrays.fill(TREE, Integer.MAX_VALUE);
		test1 = new int[N + 1];
		test2 = new int[N + 1];
		test3 = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			test1[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			test2[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			test3[i] = Integer.parseInt(st.nextToken());
		}

		LIST = new TEST[N];
		for (int i = 0; i < N; i++) {
			LIST[i] = new TEST();
		}

		for (int i = 0; i < N; i++) {
			LIST[test1[i]-1].x = i + 1;
			LIST[test2[i]-1].y = i + 1;
			LIST[test3[i]-1].z = i + 1;
		}

		Arrays.sort(LIST, new Comparator<TEST>() {
			@Override
			public int compare(TEST o1, TEST o2) {
				if (o1.x > o2.x) {
					return 1;
				} else if (o1.x < o2.x) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		int resutl = 1;
//		System.out.println("GreteStudent : " + LIST[0].x);
		for (int i = 1; i < N; i++) {
			insert(1, 1, N, LIST[i - 1].y, LIST[i - 1].z);
			long min = getMin(1, 1, N, 1, LIST[i].y - 1);
			if (min > LIST[i].z) {
//				System.out.println("GreteStudent : " + LIST[i].x);
				resutl++;
			}
		}
		System.out.println(resutl);

	}

	static void insert(int node, int start, int end, int index, int value) {
		if (index < start || end < index) {
			return;
		}

		if (start == end) {
			TREE[node] = value;
			return;
		}

		int mid = (start + end) / 2;
		insert(node * 2, start, mid, index, value);
		insert(node * 2 + 1, mid + 1, end, index, value);
		TREE[node] = Math.min(TREE[node * 2], TREE[node * 2 + 1]);
	}

	static long getMin(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && end <= right) {
			return TREE[node];
		}

		int mid = (start + end) / 2;
		long leftV = getMin(node * 2, start, mid, left, right);
		long rightV = getMin(node * 2 + 1, mid + 1, end, left, right);
		return Math.min(leftV, rightV);

	}

}
