package AlgorithmCodingTest.Number_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NT037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] A = new int[m+ 1];

        for (int i = 2; i <= m; i++) {
            A[i] = i;
        }

        for(int i=2; i<=Math.sqrt(m);i++) {
            if (A[i] == 0) {
                continue;
            }
            for(int j=2*i; j<=m;j=j+i) {
                A[j] = 0;
            }
        }

        for(int i=n; i<=m; i++) {
            if(A[i]!=0) {
                System.out.println(A[i]);
            }
        }
    }
}
