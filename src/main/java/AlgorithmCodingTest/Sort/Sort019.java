package AlgorithmCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sort019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st2.nextToken());
        }
        quick_sort(A, 0, n - 1, k - 1);
        System.out.println(A[k - 1]);
    }

    private static void quick_sort(int[] A, int left, int right, int k) {
        if (left < right) {
            int pivot = SearchPivot(A, left, right);
            if (pivot == k) {
                return;
            } else if (k < pivot) {
                quick_sort(A, left, pivot - 1, k);
            } else {
                quick_sort(A, pivot + 1, right, k);
            }
        }
    }


    private static int SearchPivot(int[] A, int left, int right) {
        if (left + 1 == right) { //데이터가 2개인 경우 바로 정렬
            if (A[left] > A[right]) {
                swap(A, left, right); // 인덱스 넘김
            }
            return right; //밑에 j랑 같은 역할
        }
        int M = (left + right) / 2;
        swap(A, M, left); // 중앙값이랑 맨 왼쪽값이랑 스왑해줌
        int pivot = A[left]; // 중앙값을 pivot으로 설정
        int i = left + 1, j = right;
        while (i <= j) {
            while (j >= left + 1 && pivot < A[j]) { // 피벗보다 작은 수가 나올 때까지 j--
                j--;
            }
            while (i <= right && pivot > A[i]) { // 피벗보다 큰 수가 나올 때까지 i++
                i++;
            }
            if (i <= j) { //i와 j가 교차되는 지점 swap
                swap(A, i++, j--);
            }
        }
        //마지막 단계 pivot이 다시 중앙으로 들어가야 하고 그 때의 중앙 인덱스 반환
        A[left] = A[j]; // 피벗 데이터를 나눠진 두 그룹의 경계 인덱스에 저장하기
        A[j] = pivot;
        return j;
    }

    private static void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }
}
