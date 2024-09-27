package AlgorithmCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dp088 {
    static long mod = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //D[N][H] = 길이가 N인 계단에서 H 높이로 종료되는 계단 수를 만들 수 있는 경우의 수
        long[][] dp = new long[n+1][11]; //11 - 0~9까지인데 넉넉하게 잡음

        //1의 자리수는 다 1로 초기화
        for(int i=1;i<=9;i++) {
            dp[1][i] = 1;
        }

        for(int i=2;i<=n;i++) {
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];

            for(int j=1;j<=8;j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%mod;
            }
        }

        long sum = 0;
        for(int i=0;i<=9;i++) {
            sum = (sum+dp[n][i])%mod;
        }
        System.out.println(sum);
    }
}
