package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Array004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st1.nextToken());
        int m = Integer.parseInt(st1.nextToken());

        //기본 배열
        int[][] arr = new int[n+1][n+1]; //5*5
        for(int i=1; i<arr.length; i++) { //1부터 돌려야 0으로 빈 곳 채워짐
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<arr.length;j++) {
                arr[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        //합 배열
        int[][] S = new int[n+1][n+1];
        for(int i=1; i<arr.length; i++) {
            for (int j=1;j<arr.length;j++) {
                S[i][j] = arr[i][j] + S[i-1][j] + S[i][j-1] - S[i-1][j-1];
            }
        }
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<m;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if (x1==x2 && y1==y2) {
                sb.append(arr[x1][y1]).append("\n");
            } else {
                int sum = 0;
                sum = S[x2][y2] - S[x1-1][y2] - S[x2][y1-1] + S[x1-1][y1-1];
                sb.append(sum).append("\n");
            }
        }
        System.out.println(sb);
    }
}
