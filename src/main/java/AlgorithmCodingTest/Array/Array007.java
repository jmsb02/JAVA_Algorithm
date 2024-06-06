package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Array007 {
    public static void main(String[] args) throws IOException {
        //배열에서 포인터 2개로 돌면서 그 두 인덱스에 속한 값 합이 M이 되면 COUNT+=1; -> 투 포인터로 처리
        //더 효율적으로 풀이하기 위해 인덱스 맨 앞, 맨 뒤 설정, 정렬된 배열에서 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //변수 초기화
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int count = 0;
        int s = 1;
        int e = n;

        //배열 초기화
        int[] arr = new int[n+1];
        arr[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i< arr.length;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        //투 포인터 로직
        while (s<e) { //배열을 다 돌 때까지 (n이 아닐 때까지)
            if (arr[s] +arr[e] ==m){
                count+=1;
                s+=1;
                e-=1;
            } else if (arr[s] + arr[e] < m) {
                s++;
            } else if (arr[s] + arr[e] > m) {
                e--;
            }
        }
        System.out.println(count);
    }
}
