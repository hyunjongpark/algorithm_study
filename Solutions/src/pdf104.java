import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class pdf104 {
	static int C;
	static int W;

	static int WA[];
	static int VA[];

	static int DT[][];

	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("104"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		WA = new int[C + 1];
		VA = new int[C + 1];
		for (int i = 1; i <= C; i++) {
			st = new StringTokenizer(br.readLine());
			WA[i] = Integer.parseInt(st.nextToken());
			VA[i] = Integer.parseInt(st.nextToken());
		}

		DT = new int[102][10002];

		solve();
		System.out.println(DT[1][W]);

		br.close();
	}

	public static void print() {
		for (int i = 0; i <= C; i++) {
			for (int j = 0; j <= W; j++) {
				System.out.print(DT[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void solve() {
		for (int i = C; i >= 1; i--) {
			for (int j = 1; j <= W; j++) {
				System.out.println("[" + i + "][" + j + "]");
				if (j < WA[i]) {
					DT[i][j] = DT[i + 1][j];
				} else {
					DT[i][j] = Math.max(DT[i + 1][j], DT[i + 1][j - WA[i]] + VA[i]);
				}
				print();
				
			}
		}
	}
}
