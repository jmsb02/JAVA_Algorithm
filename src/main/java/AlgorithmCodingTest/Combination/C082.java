package AlgorithmCodingTest.Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C082 {
    static int n,m,k;
    static int[][] C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        C = new int[201][201];

        //배열 초기화
        for(int i=0;i<=200;i++) {
            for(int j=0;j<=i;j++) {
                if(j==0 || j== i)
                    C[i][j] = 1;
                else {
                    C[i][j] = C[i-1][j] + C[i-1][j-1];
                }
                //k 범위 넘어가면 범위의 최댓값으로 고정
                if(C[i][j] > 1000000000) C[i][j] = 1000000001;
            }
        }
        if(C[n+m][n] < k) { //주어진 자릿수로 만들 수 없는 k번째 수면
            System.out.println(-1);
        } else {
            while(!(n==0 && m==0)) { //남은 문자로 조합을 만들 때까지 계속 진행
                //a 선택시 남은 문자로 만들 수 있는 모든 경우의 수가 k보다 크면
                if(C[n-1+m][m] >= k) {
                    System.out.print("a");
                    n--; //a 선택시 남은 문자로 만들 수 있는 조합의 수 업데이트
                }
                else {
                    System.out.print("z");
                    //남은 조합수 반영하여 k를 업데이트 한다. k = k - t
                    k = k - C[n-1+m][m];
                    m--;
                }
            }
        }
    }
}
