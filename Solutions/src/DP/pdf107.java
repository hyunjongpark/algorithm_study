package DP;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class pdf107 {
	static int H;
	static int W;
	static int A[][];
	static int S[][];
	
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("107"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		A = new int[H][W];
		S = new int[H][W];
		
		for(int i = 0 ; i < H ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < W ; j++){
				A[i][j] = Integer.parseInt(st.nextToken());
				S[i][j] = 0;
			}
		}
		
		solve();
		System.out.println(S[H-1][W-1]);
		
		br.close();
	}
	
	
	public static void print(){
		for(int i = 0 ; i < H ; i++){
			for(int j = 0 ; j < W ; j++){
				System.out.print(S[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void solve() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				int pV = 0;
				if (i - 1 < 0 && j - 1 < 0) {
					pV = 0;
				} else if (i - 1 < 0) {
					pV = S[i][j - 1];
				} else if (j - 1 < 0) {
					pV = S[i - 1][j];
				} else {
					pV = Math.max(S[i - 1][j], S[i][j - 1]);
				}

				if (A[i][j] == 1) {
					S[i][j] = pV + 1;
				} else {
					S[i][j] = pV;
				}
				print();
			}
		}
	}
}


