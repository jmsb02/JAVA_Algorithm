package AlgorithmCodingTest.Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C078 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] D = new int[15][15];

        for(int i=0;i<15;i++) {
            D[i][1] = 1;
            D[0][i] = i;
        }

        for(int i=1;i<15;i++) {
            for(int j=2;j<15;j++) {
                D[i][j] = D[i-1][j] + D[i][j-1];
            }
        }

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<t;i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            sb.append(D[k][n]).append("\n");
        }
        System.out.println(sb.toString().trim());
    }

}
