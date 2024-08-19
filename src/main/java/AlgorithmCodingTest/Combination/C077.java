package AlgorithmCodingTest.Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C077 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] D = new int[n+1][n+1];

        for(int i=0;i<=n;i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }

        for(int i=2;i<=n;i++) {
            for(int j=1;j<i;j++) {
                D[i][j] = (D[i-1][j] + D[i-1][j-1])%10007;
            }
        }
        System.out.println(D[n][k]);

    }
}
