package DP;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class pdf97 {
	static int V;
	static int A[];
	static int DIS[];
	
	static int micCount = 9999999;
	static String R = new String();
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("97"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		V = e - s;
		
		A = new int[3];
		st = new StringTokenizer(br.readLine());
		A[0] = Integer.parseInt(st.nextToken());
		A[1] = Integer.parseInt(st.nextToken());
		A[2] = Integer.parseInt(st.nextToken());
		
		DIS = new int[V + 1];
//		solve(0,0);
//		System.out.println(micCount);
		
		solve_dy();
		System.out.println(DIS[V]);
		
		br.close();
	}
	
	
	public static void solve_dy(){
		for(int i = 0 ; i < V ; i++){
			DIS[i] = 987654321;
		}
		DIS[0] = 0 ;
		for(int i = 1 ; i <= V ; i++){
			int temp = 987654321;
			for(int j = 0 ; j < 3 ; j++){
				if (i - A[j] < 0 ){
					continue;
				}
				temp = Math.min(temp, DIS[i - A[j]] +1);				
			}
			DIS[i] = temp;
			
			System.out.print(i + " : ");
			for(int t = 0 ; t <= V ; t++){
				System.out.print(DIS[t] + " ");
			}
			System.out.println();
		}
	}
	
	
	public static int solve(int sum, int count){
//		System.out.println("sum: " + sum+ " count: " + count);
		if (sum ==  V){
			if(micCount > count){
				micCount = count;
			}
			return micCount;
		}
		if (sum >  V){
			return 99999;
		}
		
		
		count = count + 1;
		if (micCount < count){
			return micCount;
		}
		int a = solve(sum + A[0], count);
		int b = solve(sum + A[1], count);
		int c = solve(sum + A[2], count);
			
		int aa = Math.min(a, b);
		int bb = Math.min(aa, c);
		
		return bb;
	}

}
