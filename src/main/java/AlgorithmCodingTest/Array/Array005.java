package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Array005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st1.nextToken());
        int m = Integer.parseInt(st1.nextToken());

        //원본 배열
        long[] A = new long[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");// 1 2 3 1 2
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st2.nextToken());
        }

        //합 배열
        long[] S = new long[n];
        S[0] = A[0];
        for (int i = 1; i < n; i++) {
            S[i] = S[i - 1] + A[i];
        }

        //(결과값)
        long result = 0;

        //나머지 배열
        long[] C = new long[m]; //나머지 카운트 인덱스 배열
        for (int i = 0; i < n; i++) {
            int judge = (int)(S[i]%m); //3이하
            if(judge == 0) {
                result++;
            }
            C[judge]++;
        }

        for(int i=0;i<m;i++) {
            if (C[i]>1) {
                result += (C[i] * (C[i]-1)/2);
            }
        }
        System.out.println(result);
    }
}
