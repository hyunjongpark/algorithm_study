package DP;
/*
 * https://www.acmicpc.net/problem/2579
10	0	0	
30	20	0	
35	25	15	
50	55	35	
65	45	40	
65	75	55	
75
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stairs {
	static boolean debug = true;

	static int N;
	static int[] arrs;

	static int[] dpt;
	static int result;

	static public void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("stairs"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		N = Integer.parseInt(br.readLine());
		arrs = new int[N];
		for (int i = 0; i < N; i++) {
			arrs[i] = Integer.parseInt(br.readLine());
		}
		dpt = new int[N];
		dpt[0] = arrs[0];
		dpt[1] = dpt[0] + arrs[1];
		dpt[2] = Math.max(arrs[0] + arrs[2], arrs[1] + arrs[2]);

		for (int i = 3; i < N; i++) {
			dpt[i] = Math.max(dpt[i - 2] + arrs[i], dpt[i - 3] + arrs[i - 1] + arrs[i]);
		}
		System.out.println(dpt[N - 1]);
	}
}
