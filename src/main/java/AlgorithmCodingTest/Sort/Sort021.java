package AlgorithmCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sort021 {
    public static int[] arr, tmp;

    public static long swap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        tmp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        swap = 0;
        //병합 정렬 실행
        merge_sort(1,n);

        System.out.println(swap);

    }

    private static void merge_sort(int s, int e) {
        //더 이상 병합 정렬 못하는 경우 리턴
        if (e-s<1) {
            return;
        }

        //중앙 값 구하기 및 overflow 방지
        int m = s + (e-s)/2;

        //병합 정렬 재귀로 실행
        merge_sort(s,m);
        merge_sort(m+1,e);

        for(int i=s;i<=e;i++) {
            tmp[i] = arr[i];
        }

        int k = s; //원본 인덱스 값 추적 인덱스 설정
        int idx1 = s;
        int idx2 = m+1;

        while(idx1<=m && idx2<=e) {
            if (tmp[idx1] > tmp[idx2]) {
                arr[k] = tmp[idx2];
                swap = swap +idx2 - k;
                k++;
                idx2++;
            } else{
                arr[k] = tmp[idx1];
                k++;
                idx1++;
            }
        }

        //다 돌았는데도 남아있을 경우
        while(idx1<=m) {
            arr[k] = tmp[idx1];
            k++;
            idx1++;
        }
        while(idx2<=e) {
            arr[k] = tmp[idx2];
            k++;
            idx2++;
        }
    }
}
