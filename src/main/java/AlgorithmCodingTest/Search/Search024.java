package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Search024 {
    static int n;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        //자릿수 값 초기화
        n = Integer.parseInt(br.readLine());

        //일의 자릿수 2,3,5,7부터 dfs 호출
        //DFS에 현재값과 우리가 구하고자 하는 자릿수까지 같이 넘김
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);

        System.out.println(sb);
    }

    static void DFS(int v, int d) {
        if (d == n) { //원하는 자릿수일경우 - 결괏값 추출
            //소수일경우
            if (isPrime(v)) {
                sb.append(v).append("\n");
            }
        }

        //십의 자릿수부터는 1 3 5 7 9
        for (int i = 1; i <= 9; i++) {
            if (i % 2 == 0) continue; //짝수는 소수가 아니므로 패스
            else {
                if (isPrime(10 * v + i)) {
                    DFS(10 * v + i, d + 1);
                }
            }
        }

    }

    static boolean isPrime(int v) {
        //소수 구하기 함수
        for (int i = 2; i <= Math.sqrt(v); i++)
            if (v % i == 0) //소수 x -> false
                return false;
        return true; //소수 o -> trues
    }
}
