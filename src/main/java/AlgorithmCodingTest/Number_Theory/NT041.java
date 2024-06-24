package AlgorithmCodingTest.Number_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NT041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long result = n; //오일러 피 함수의 결과값을 저장할 변수 설정

        for(long i=2;i<=Math.sqrt(n);i++) { //소수구하기처럼 2~제곱근까지 탐색
            if (n%i==0) { //소인수(n의 약수)라면
                result = result - result/i; //오일러 피 적용 -> result에 소인수 업데이트
                while(n%i==0) { //소인수 분해
                    n/=i;
                }
            }
        }
        if(n>1) { //n자체가 소인수일 경우 별도로 처리
            result = result -result/n;
        }
        System.out.println(result);
    }
}
