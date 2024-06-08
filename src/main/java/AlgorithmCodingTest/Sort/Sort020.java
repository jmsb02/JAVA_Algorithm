package AlgorithmCodingTest.Sort;

import java.io.*;

public class Sort020 {
    public static int[] arr, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //변수 및 배열 초기화
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        tmp = new int[n+1]; //정렬할 때 잠시 사용할 임시 배열 선언
        for(int i=1;i<=n;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //병합 정렬
        merge_sort(1,n);

        for(int i=1;i<=n;i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();

    }

    private static void merge_sort(int s, int e) {

        if (e-s<1) { //배열의 크기가 1이하인 경우
            return;
        }
        int m = s + (e-s)/2; //중앙값

        //재귀 형태로 병합 정렬 처리
        merge_sort(s,m); //왼쪽 병합 정렬
        merge_sort(m+1,e); //오른 쪽 병합 정렬

        for(int i=s;i<=e;i++) {
            tmp[i] = arr[i]; //임시 배열로 복사
        }

        int k = s;
        int idx1 = s;
        int idx2 = m+1;
        while(idx1<=m && idx2 <=e) {
            if (tmp[idx1] > tmp[idx2]) {
                arr[k] = tmp[idx2];
                k++;
                idx2++;
            } else { //tmp[idx1] <= tmp[idx2]
                arr[k] = tmp[idx1];
                k++;
                idx1++;
            }
        }
        while(idx1<=m) {
            arr[k] = tmp[idx1];
            k++;
            idx1++;
        }
        while(idx2<=m) {
            arr[k] = tmp[idx2];
            k++;
            idx2++;
        }
    }
}
