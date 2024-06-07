package AlgorithmCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sort018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] P = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        //인출 시간이 낮은 애들로 정렬 후 합 배열 만든 후 총 합 구하면 -> 최솟값
        //n<=1000, 1초 -> O(n^2) -> 정렬 사용 여기서는 선택 정렬을 사용해보자
        for(int i=1;i<n;i++) {
            int target = P[i];
            int j = i-1;

            //j가 범위를 벗어나지 않고 target이 자기 자리를 찾아갈 때까지 while문을 돔
            while(j>=0 && target < P[j]) {
                P[j+1] = P[j]; //한 칸씩 땡겨주고
                j--; //인덱스 하나 감소
            }
            //반복문 탈출 == target이 P[j]보다 크거나 같다는 뜻 -> j 뒤에 target이 들어간다
            P[j+1] = target;
        }
        int[] S = new int[n];
        S[0] = P[0];
        for(int i=1;i<n;i++) {
            S[i] = S[i-1] + P[i];
        }
        int sum = 0;
        for(int i=0;i<n;i++) {
            sum+=S[i];
        }

        System.out.println(sum);
    }
}
