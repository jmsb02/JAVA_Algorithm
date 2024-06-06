package AlgorithmCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sort015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //변수 및 배열 초기화
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i=0;i<n-1;i++) { //총 루프 개수 n이 5인 경우 총 4번
            for(int j=0;j<n-1-i;j++) { //실제 swap n-1번에서 i만큼 빼줘야됨 (정렬되지 않은 영역 내에서만 루프 실행하기 때문)
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        for(int i=0;i<n;i++) {
            System.out.println(arr[i]);
        }
    }
}
