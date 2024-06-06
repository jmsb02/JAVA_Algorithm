package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Array008 {
    public static void main(String[] args) throws IOException {
        //배열 두 수 의 합 -> 투 포인터
        //N 최대 2000 -> 최소(O(nlgn)) -> 배열 + 투포인터

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        //배열 초기화
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<arr.length;i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr); //투 포인터 정렬!

        int count = 0;

        for(int i=0; i<arr.length;i++) {
            //변수 초기화
            long k = arr[i];
            int s = 0;
            int e = n-1;

            while(s<e) {
                if (arr[s] + arr[e] ==k) {
                    if(s != i && e != i) {
                        //서로 다른 두 값을 가리킬 때
                        count++;
                        break;
                    } else if (s == i) {
                        s++;
                    } else if (e ==i){
                        e--;
                    }
                }
                else if (arr[s] + arr[e] <k) {
                    s++;
                }
                else{
                    e--;
                }
            }
        }
        System.out.println(count);
    }
}
