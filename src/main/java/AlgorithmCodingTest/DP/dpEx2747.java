package AlgorithmCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dpEx2747 {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        //dp 초기화
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        //dp 가장 작은 값 설정
        dp[0] = 0;
        dp[1] = 1;

        //피보나치 함수 호출
        fibo(n);

        System.out.println(dp[n]);

    }

    private static int fibo(int n) {
        if(dp[n] != -1) {
            return dp[n];
        }
        return dp[n] = fibo(n-2) + fibo(n-1);
    }
}
