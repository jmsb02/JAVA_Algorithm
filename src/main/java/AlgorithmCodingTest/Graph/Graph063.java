package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph063 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] D = new int[n + 1][n + 1];

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(i==j) {
                    D[i][j] = 0;
                } else {
                    D[i][j] = 500001;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            D[a][b] = 1;
            D[b][a] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    D[s][e] = Math.min(D[s][e], D[s][k] + D[k][e]);
                }
            }
        }

        int Min = Integer.MAX_VALUE;
        int idx = -1;
        for(int i=1;i<=n;i++) {
            int temp = 0;
            for(int j=1;j<=n;j++){
                temp =  temp + D[i][j];
            }
            if (Min > temp) {
                Min = temp;
                idx = i;
            }
        }

        System.out.println(idx);

    }
}
