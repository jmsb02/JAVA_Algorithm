package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Search031 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //배열의 길이
        int k = Integer.parseInt(br.readLine()); //구하고자 하는 인덱스
        long start = 1; //시작 인덱스
        long end = k; //종료 인덱스
        long result = 0; //결과값 변수

        //이진탐색
        while(start<=end) {
            long mid = (start+end)/2;
            long cnt = 0; //중앙값 보다 작은 수는 몇 개인지 판단 인덱스
            for(int i=1;i<=n;i++) {
                cnt += Math.min(mid/i, n);
            }
            if (cnt<k) { //오른쪽으로 이동
                start = mid+1;
            } else {
                result = mid;
                end = mid-1;
            }
        }
        System.out.println(result);
    }
}
