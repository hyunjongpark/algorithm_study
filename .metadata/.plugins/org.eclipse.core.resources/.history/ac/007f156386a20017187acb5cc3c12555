import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class pdf112 {
	static int N;
	static int M;
	static int mi[];
	static int ci[];
	static int check[];
	static int max = 987654321;
	static int DT[][];
	static int sumTime = 0;

	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("112"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mi = new int[N];
		ci = new int[N];
		check = new int[N];
		DT = new int[102][1000];

		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++) {
			mi[j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++) {
			ci[j] = Integer.parseInt(st.nextToken());
			sumTime += ci[j];
		}

		solve();
		
		int res = 0;
		for(int i = 0 ; i <= sumTime ; i++){
			if (DT[N-1][i] >= M){
				res = i;
				break;
			}
		}
		System.out.println(res);

		// solve_recur(0, 0);
		// System.out.println(max);
		br.close();
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int t = 0; t <= sumTime; t++) {
				System.out.print(DT[i][t]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static void solve() {
		for (int i = 0; i < N; i++) {
			for (int t = 0; t <= sumTime; t++) {
				if (t >= ci[i]) {
					int a = 0;
					if (i - 1 < 0) {
						a = 0;
					} else {
						a = DT[i - 1][t];
					}

					int b = 0;
					if (i - 1 < 0) {
						b = 0;
					} else {
						if (t - ci[i] < 0) {
							b = 0;
						} else {
							b = DT[i - 1][t - ci[i]];
						}
					}
					DT[i][t] = Math.max(a, b + mi[i]);
				} else {
					if (i - 1 < 0) {
						DT[i][t] = 0;
					} else {
						DT[i][t] = DT[i - 1][t];
					}
				}
			}
			print();
		}
	}

	public static int solve_recur(int n, int k) {
		if (DT[n][k] != 0) {
			return DT[n][k];
		}

		if (n >= N) {
			return 987654321;
		}
		if (k >= M) {
			System.out.print(n + " " + k + " ");
			int t = 0;
			for (int i = 0; i < N; i++) {
				System.out.print(check[i]);
				if (check[i] == 1) {
					t += ci[i];
				}
			}
			System.out.println(" t: " + t);
			max = Math.min(max, t);
			System.out.println();
			return max;
		}

		int a = solve_recur(n + 1, k);
		check[n] = 1;
		int b = solve_recur(n + 1, k + mi[n]);
		check[n] = 0;
		return DT[n][k] = Math.min(a, b);
	}
}
