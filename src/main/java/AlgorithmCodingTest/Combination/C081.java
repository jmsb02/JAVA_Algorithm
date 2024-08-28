package AlgorithmCodingTest.Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C081 {
    public static void main(String[] args) throws IOException {
        int N, Q;
        long[] F = new long[21]; //factorial 저장 배열
        int[] S = new int[21]; //순열 저장 배열
        boolean[] visited = new boolean[21];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //순열의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());

        //팩토리얼 배열 초기화
        F[0] = 1;
        for (int i = 1; i <= N; i++) {
            F[i] = F[i - 1] * i;
        }

        //K번째 순열 출력하기
        if (Q == 1) {
            long k = Long.parseLong(st.nextToken());
            for (int i = 1; i <= N; i++) { //총 자릿수
                for (int j = 1, cnt = 1; j <= N; j++) { //각 자릿수
                    if (visited[j])
                        continue;
                    if (k <= cnt * F[N - i]) { //k값이 cnt변수 * 직전 factoral 값보다 작으면
                        k -= ((cnt - 1) * F[N - i]); //k를 k-(경우의 수 * (cnt-1))로 업데이트
                        S[i] = j;
                        visited[j] = true;
                        break;
                    }
                    cnt++;
                }
            }
            for(int i=1;i<=N;i++) {
                System.out.print(S[i] + " ");
            }
        }
        //입력된 순열의 순서 구하기
        else {
            long k = 1; //순열 순서 저장 배열 변수 - 최솟값 1로 초기화
            for (int i = 1; i <= N; i++) {
                S[i] = Integer.parseInt(st.nextToken());
                long cnt = 0; //가능한 경우의 수 카운트 변수

                //순열의 순서를 계산 - 주어진 순열에서 현재 자리의 숫자보다 작은 숫자들 중에서,
                //아직 사용되지 않은 숫자가 몇 개있는지 세기 위함
                for (int j = 1; j < S[i]; j++) {
                    if (!visited[j]) {
                        cnt++;
                    }
                }
                //현재 자리의 숫자보다 작은 숫자들 중에서 사용되지 않은 숫자들의 개수(cnt)를 이용해
                //현재 자리에서 가능한 경우의 수를 순서에 추가
                k += cnt * (F[N - i]);
                visited[S[i]] = true;
            }
            System.out.println(k);
        }
    }
}
