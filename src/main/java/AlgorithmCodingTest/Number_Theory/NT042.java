package AlgorithmCodingTest.Number_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NT042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int i=0;i<tc;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int result = A*B/gcd(A,B);
            System.out.println(result);
        }
    }

    private static int gcd(int a, int b) {
        if(b==0) { //b가 0이면 나눌 수 없음 그대로 0 return
            return a;
        }
        else {
            return gcd(b, a % b); //재귀 구현 gcd(작은수, 나머지)
        }
    }
}
