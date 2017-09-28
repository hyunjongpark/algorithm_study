/*
 * https://algospot.com/judge/problem/read/MORDOR
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mordor {
	static int N = 0;
	static int M = 0;
	static int K = 0;
	
	static long[] arrMax = null;
	static long[] treeMax = null;
	
	static long[] arrMin = null;
	static long[] treeMin = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("mordor"));
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer token;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0 ; t < T; t++){
			token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			int P = Integer.parseInt(token.nextToken());
			int size = (int) Math.pow(2, height() + 1);
			treeMax = new long[size];
			arrMax = new long[N];
			
			treeMin = new long[size];
			for (int i = 0; i < size; i++) {
				treeMin[i] = 987654321; 
			}
			
			arrMin = new long[N];
			
			token = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arrMax[i] = Integer.parseInt(token.nextToken());
				arrMin[i] = arrMax[i]; 
			}
			
			initMax(1, 0, N - 1);
			initMin(1, 0, N - 1);
			
			StringBuilder sb =  new StringBuilder();
			for (int i = 0; i < P; i++) {
				token = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(token.nextToken());
				int end = Integer.parseInt(token.nextToken());
				long max = getMax(1, 0, N - 1, start, end);
				long min = getMin(1, 0, N - 1, start, end);
				sb.append((max - min) + "\n");
			}
			System.out.print(sb);
		}
	}

	public static long initMax(int node, int start, int end) {
		if (start == end) {
			treeMax[node] = arrMax[start];
//			System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + treeMax[node]);
			return arrMax[start];
		}

		long a = initMax(node * 2, start, (start + end) / 2);
		long b = initMax(node * 2 + 1, (start + end) / 2 + 1, end);
		if (a > b){
			treeMax[node] = a;
		}else{
			treeMax[node] = b;
		}
//		System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + treeMax[node]);
		return treeMax[node];
	}


	public static long getMax(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
//			System.out.println("sum node: " + node + " start: " + start + " end: " + end + " tree[node]: " + treeMax[node]);
			return treeMax[node];
		}
		long a = getMax(node * 2, start, (start + end) / 2, left, right);
		long b = getMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		if (a > b){
			return a;
		}else{
			return b;
		}
	}
	
	
	public static long initMin(int node, int start, int end) {
		if (start == end) {
			treeMin[node] = arrMin[start];
//			System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + treeMin[node]);
			return arrMin[start];
		}

		long a = initMin(node * 2, start, (start + end) / 2);
		long b = initMin(node * 2 + 1, (start + end) / 2 + 1, end);
		if (a < b){
			treeMin[node] = a;
		}else{
			treeMin[node] = b;
		}
//		System.out.println("node: " + node + " start: " + start + " end: " + end + " tree[node]: " + treeMin[node]);
		return treeMin[node];
	}


	public static long getMin(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return 987654321;
		}
		if (left <= start && end <= right) {
//			System.out.println("sum node: " + node + " start: " + start + " end: " + end + " tree[node]: " + treeMin[node]);
			return treeMin[node];
		}
		long a = getMin(node * 2, start, (start + end) / 2, left, right);
		long b = getMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		if (a < b){
			return a;
		}else{
			return b;
		}
	}
	
	private static int height() {
		int i = 1;
		while (true) {
			if (Math.pow(2, i) > N) {
				break;
			}
			i++;
		}
		return i;
	}
}
