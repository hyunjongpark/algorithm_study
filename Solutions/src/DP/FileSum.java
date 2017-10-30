package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FileSum {

	static int[] arrs;
	static int[][] dpt;
	static int[] sum;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("filesum"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			int n = Integer.parseInt(br.readLine());
			arrs = new int[n + 1];
			sum = new int[n + 1];
			dpt = new int[n + 1][n + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arrs[j] = Integer.parseInt(st.nextToken());
				sum[j] = sum[j - 1] + arrs[j];
				Arrays.fill(dpt[i], Integer.MAX_VALUE);
			}
			System.out.println(updateTable(1, n));
		}
	}

	static int updateTable(int start, int end) {
		if (start >= end) {
			return 0;
		}

		if (dpt[start][end] != 0) {
			return dpt[start][end];
		}

		if (end - start == 1) {
			return dpt[start][end] = arrs[start] + arrs[end];
		}

		for (int i = start; i <= end; i++) {
			int temp = updateTable(start, i) + updateTable(i + 1, end) + sum[end] - sum[start - 1];
			dpt[start][end] = Math.min(dpt[start][end], temp);
		}
		return dpt[start][end];

	}

}
