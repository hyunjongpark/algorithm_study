import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class pdf87 {
	static int N;
	static String R = new String();
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("83"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		F(N);
		
		br.close();
	}
	
	
	public static void F(int n){
		if (n == 0 ){
			return;
		}
		if (n % 10 == 0 ){
			F(n/10);
			return;
		}
		System.out.print(n % 10);
		F(n/10);
		R.contains(String.valueOf(n % 10));
		System.out.print(R);
	}

}
