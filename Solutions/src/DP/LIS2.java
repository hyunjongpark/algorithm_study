package DP;
/*
 * https://www.acmicpc.net/problem/12015
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS2 {
	static int T;
	static long[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("insertbox"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		T = Integer.parseInt(br.readLine());
		arr = new long[T];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
		}
		System.out.println(lis());
		br.close();
	}

	public static int lis() {
		long[] lis = new long[T];
		lis[0] = arr[0];
		int lisLength = 1;

		for (int i = 1; i < T; i++) {
			if (arr[i] < lis[0]) {
				lis[0] = arr[i];
			} else if (arr[i] > lis[lisLength - 1]) {
				lis[lisLength] = arr[i];
				lisLength++;
			} else {
				int idx = Arrays.binarySearch(lis, 0, lisLength, arr[i]);
				idx = idx < 0 ? -idx - 1 : idx;
				lis[idx] = arr[i];
			}
		}
		return lisLength;
	}

}
