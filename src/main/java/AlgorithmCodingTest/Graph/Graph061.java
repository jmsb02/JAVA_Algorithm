package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph061 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //도시의 개수
        int m = Integer.parseInt(br.readLine()); //노선의 개수
        //인접 행렬 구현
        int[][] distance = new int[n+1][n+1];

        //인접 행렬 초기화
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(i==j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = 10000001;
                }
            }
        }
        //인접 행렬 업데이트
        for(int i=0;i<m;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if(distance[start][end] > value) { //비용 최솟값
                distance[start][end] = value;
            }
        }
        //플로이드-워셜 알고리즘
        for(int k=1;k<=n;k++) {
            for(int s=1;s<=n;s++) {
                for(int e=1;e<=n;e++) {
                    if(distance[s][e] > distance[s][k] + distance[k][e]) {
                        distance[s][e] = distance[s][k] + distance[k][e];
                    }
                }
            }
        }
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(distance[i][j] == 10000001) {
                    System.out.print(0);
                } else {
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }


    }
}
