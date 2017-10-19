package DP;

/*
https://www.acmicpc.net/problem/2629
/	0	1	4	
0	1	1	1	
1	0	1	1	
2	0	0	0	
3	0	0	1	
4	0	0	1	
5	0	0	1	
6	0	0	0	
7	0	0	0	
8	0	0	0
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BalanceScale {
	static boolean debug = true;
	static int N;
	static int[] weight;
	static int B;
	static int[] bread;

	static long[][] dpt;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("balancescale"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		N = Integer.parseInt(br.readLine());
		weight = new int[N + 1];
		dpt = new long[N * 500 + 1][N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		B = Integer.parseInt(br.readLine());
		bread = new int[B + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++) {
			bread[i] = Integer.parseInt(st.nextToken());
		}
		updateTable(0, 0);
		printTable();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < B; i++) {
			if (bread[i] > N * 500) {
				sb.append("N ");
				continue;
			}
			if (dpt[bread[i]][N] == 1) {
				sb.append("Y ");
			} else {
				sb.append("N ");
			}
		}
		System.out.println(sb.toString());
	}

	static public void updateTable(int idx, int w) {
		if (idx > N) {
			return;
		}
		if (dpt[w][idx] != 0) {
			return;
		}
		dpt[w][idx] = 1;

		updateTable(idx + 1, w);
		updateTable(idx + 1, w + weight[idx]);
		updateTable(idx + 1, Math.abs(w - weight[idx]));
	}

	static public void printTable() {
		if (!debug) {
			return;
		}
		System.out.print("/	0	");
		for (int i = 0; i < N; i++) {
			System.out.print(weight[i] + "	");
		}
		System.out.println();

		for (int i = 0; i < N * 10; i++) {
			System.out.print(i + "	");
			for (int j = 0; j <= N; j++) {
				System.out.print(dpt[i][j] + "	");
			}
			System.out.println();
		}
		System.out.println();
	}

}
