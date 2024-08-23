package AlgorithmCodingTest.Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C080 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력값 및 배열 초기화
        int M, K, T; //M - 색 종류, K - 선택 조약돌 개수, T - total 조약돌 개수
        int[] D = new int[51]; //조약돌 값 저장 배열
        double[] P = new double[51]; //조약돌 당 확률 저장 배열
        double result;
        T = 0;
        M = Integer.parseInt(br.readLine());

        //조약돌 배열, total값 저장
        StringTokenizer st = new StringTokenizer(br.readLine()); //3
        for(int i=0;i<M;i++) {
            int now = Integer.parseInt(st.nextToken()); //5, 6, 7
            D[i] += now; //조약돌 값 저장
            T += D[i]; //total 값 저장
        }

        //조약돌 확률 구하기
        K = Integer.parseInt(br.readLine());
        result = 0.0;
        for(int i=0;i<M;i++) { //조약돌 개수만큼 for문을 돌면서
            //조약돌 각각의 개수 >= 뽑아야 하는 조약돌 개수일때만 구한다. (아닌 경우 확률은 0)
            if(D[i]>=K) {
                P[i] = 1.0;
                for(int j=0;j<K;j++) {
                    P[i]*= (double) (D[i]-j)/(T-j);
                }
                result += P[i];

            }
        }
        System.out.println(result);
    }

}
