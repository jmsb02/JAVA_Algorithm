package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Array003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st1.nextToken()); //수의 개수
        int m = Integer.parseInt(st1.nextToken()); //합을 구해야 하는 횟수

        //인덱스 값을 맞추기 위해 배열 조작
        int[] arr = new int[n + 1];
        arr[0] = 0;


        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        //배열 나머지 값 초기화
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        //합 배열 생성
        int[] S = new int[n+1];
        S[0] = 0;
        for(int i=1;i<S.length;i++) {
            S[i] = S[i-1] + arr[i];
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<m;i++) {
            int result = 0;
            StringTokenizer st3 = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st3.nextToken()); //인덱스
            int B = Integer.parseInt(st3.nextToken()); //인덱스
            result += S[B] - S[A-1];
            sb.append(result).append("\n");
        }
        System.out.println(sb);

    }
}
