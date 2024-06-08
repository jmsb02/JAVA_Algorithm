package AlgorithmCodingTest.Sort;

import java.io.*;

public class Sort022 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        radix_sort(arr, 5);
        for (int i = 0; i < n; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void radix_sort(int[] arr, int max_size) {
        int[] tmp = new int[arr.length]; // 임시 정렬을 위한 배열
        int jarisu = 1;
        int count = 0; // 최대 자릿수만큼 반복하기 위해 설정한 변수
        while (count != max_size) { // 최대 자리수만큼 반복
            int[] bucket = new int[10]; // 기수 정렬 -> 10개의 큐를 사용 (자릿수 0 ~ 9 총 10개)
            for (int i = 0; i < arr.length; i++) {
                bucket[(arr[i] / jarisu) % 10]++; // 일의 자리부터 시작하기
            }
            for (int i = 1; i < 10; i++) { // 합 배열을 이용해 index 계산하기
                bucket[i] += bucket[i - 1];
            }

            // 역순으로 순회하며 올바른 위치를 찾아 임시배열에 배치 후 버킷 업데이트(안정 정렬 보장)
            for (int i = arr.length - 1; i >= 0; i--) {
                int currentDigit = (arr[i] / jarisu) % 10;
                tmp[bucket[currentDigit] - 1] = arr[i];
                bucket[currentDigit]--;
            }
            // 다음 자릿수 이동하기 위해 실제 배열에 임시 배열값 저장
            for (int i = 0; i < arr.length; i++) {
                arr[i] = tmp[i];
            }
            // 자릿수와 카운트 증가
            jarisu = jarisu * 10;
            count++;
        }
    }
}
