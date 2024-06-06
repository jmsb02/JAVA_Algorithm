package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array006 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //배열 초기화
        int[] arr = new int[N + 1];
        arr[0] = 0; //인덱스 값 맞추기 위해 넣어줌
        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }

        //변수 초기화
        int start_index = 1;
        int end_index = 1;
        int sum = 1;
        int count = 1;

        while (end_index != N) {
            if (sum == N) {
                count += 1;
                end_index++;
                sum += end_index;
            } else if (sum > N) {
                sum-=start_index;
                start_index++;
            } else if (sum < N) {
                end_index++;
                sum+=end_index;
            }
        }
        System.out.println(count);
    }

}
