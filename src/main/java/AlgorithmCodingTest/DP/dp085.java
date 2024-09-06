package AlgorithmCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dp085 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+2];
        int[] t = new int[n+1];
        int[] p = new int[n+1];

        for(int i=1;i<=n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            t[i] = t1;
            p[i] = p1;
        }

        for(int i=n;i>0;i--) {
            if(i + t[i] > n+1) {
                dp[i] = dp[i+1];
            } else {
                dp[i] = Math.max(dp[i+t[i]]+p[i], dp[i+1]);
            }
        }
        System.out.println(dp[1]);

    }
}
