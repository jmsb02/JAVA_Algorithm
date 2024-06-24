package AlgorithmCodingTest.Number_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NT040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        //min ~ max 사이 제곱수 판별 배열
        boolean[] check = new boolean[(int) (max - min) + 1];


        //2의 제곱수 4부터 max보다 작거나 같은 값까지 반복하기
        for(long i=2; i*i<=max; i++) {
            long pow = i * i; //제곱 수 계산
            long start_index = min / pow; //min부터 시작하는 제곱수의 배수를 찾기 위해 min/pow 몫 계산
            if (min % pow != 0) { //min이 pow의 배수가 아닌 경우 start_index 증가시켜 첫 번쨰의 제곱수의 배수를 찾음
                start_index++;
            }
            //제곱수의 배수 표시
            for (long j = start_index; pow * j <= max; j++) {
                check[(int) ((j * pow) - min)] = true;
            }
        }

        int count = 0;
        for(int i=0;i<=max-min;i++) { //인덱스 범위 (0~9)
            if(!check[i]) {
                count+=1;
            }
        }

        System.out.println(count);
    }}
