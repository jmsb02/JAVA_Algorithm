package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph062 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] D = new int[n+1][n+1];

        for(int i=1;i<=n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++) {
                D[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int k=1;k<=n;k++) {
            for(int s=1;s<=n;s++) {
                for(int e=1;e<=n;e++) {
                    if(D[s][k] == 1 && D[k][e] == 1) {
                        D[s][e] = 1;
                    }
                }
            }
        }
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }

    }
}
