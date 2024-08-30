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

        //한 명일 때는 선물 교환 안 됨
        D[1] = 0;
        //두 명일 때는 선물 교환 양방향으로만
        D[2] = 1;

        for (int i = 3; i <= n; i++) {
            //한 명을 기준으로 삼고 (양방향 + 단방향) 경우의 수 곱해주면 됨
            D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % mod;
        }
        System.out.println(D[n]);
    }
}
