package AlgorithmCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Greedy032 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int[] A = new int[n];
        for(int i=n-1;i>=0;i--) {
            int value = Integer.parseInt(br.readLine());
            A[i] = value;
        }

        while(k!=0) {
            for(int i=0;i<A.length;i++) {
                if(A[i] <= k) { //1000 <= 4200
                    cnt += k/A[i]; //4;
                    k = k%A[i];
                }
            }
        }
        System.out.println(cnt);

    }
}
