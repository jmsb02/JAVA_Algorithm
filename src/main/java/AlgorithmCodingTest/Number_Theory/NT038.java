package AlgorithmCodingTest.Number_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NT038 {

        private void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            // 소수를 판별할 범위 설정
            int sqrtB = (int) Math.sqrt(b);
            boolean[] isPrime = new boolean[sqrtB + 1];
            for (int i = 2; i <= sqrtB; i++) isPrime[i] = true;

            // '에라토스테네스의 체'를 이용한 소수찾기
            for (int i = 2; i <= sqrtB; i++) {
                if (isPrime[i]) {
                    for (int j = i * 2; j <= sqrtB; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            // 주어진 범위 [a, b]에 해당하는 '거의 소수'의 개수 찾기
            int cnt = 0;
            for (int i = 2; i <= sqrtB; i++) {
                if (isPrime[i]) {
                    long pow = (long) i * i;
                    while (pow <= b) {
                        if (pow >= a) {
                            cnt++;
                        }                        if (pow > b / i) break; // 오버플로우 방지
                        pow *= i;
                    }
                }
            }
            System.out.println(cnt);
        }

        public static void main(String[] args) throws IOException {
            new NT038().solution();
        }
    }

