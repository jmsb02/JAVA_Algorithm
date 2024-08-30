package AlgorithmCodingTest.Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C083 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long mod = 1000000000;
        long[] D = new long[1000001];

        D[1] = 0;
        D[2] = 1;

        for (int i = 3; i <= n; i++) {
            D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % mod;
        }
        System.out.println(D[n]);
    }
}
