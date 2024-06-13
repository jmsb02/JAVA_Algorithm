package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Search030 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] A =new int[n];
        int start = 0;
        int end = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            int value = Integer.parseInt(st.nextToken());
            A[i] = value;
            if(start<A[i]) start = A[i]; //start - 최소한의 가장 긴 레슨 길이보단 커야 함
            end = end + A[i]; // end - 모든 레슨 길이의 합 (한 블루레이에 집어넣는 경우)
        }

        //이진탐색 실행
        while(start<=end) {
            int mid = (start+end)/2;
            int sum = 0; //레슨 합
            int count = 0; //현재 사용한 블루레이 개수
            for(int i=0;i<n;i++) {
                if(sum+A[i] > mid) { //현재 블루레이에 현재 값 추가 시 중앙값 초과 한 다음 블루레이 하나 더 필요
                    count++;
                    sum = 0; //레슨 합 초기화
                }
                sum = sum + A[i]; //중앙값 초과 하지 않을 시 (sum+A[i]<=mid)
            }
            if(sum!=0) //탐색이 끝났는데 sum이 0이 아니라면 블루레이가 하나 더 필요
                count++;

            //이진 탐색 결과를 바탕으로 블루레이 크기 조정
            if(count>m) {
                start = mid +1;
            }
            else {
                end = mid -1;
            }
        }
        System.out.println(start);
    }
}
