package AlgorithmCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sort017 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String n = br.readLine();
        int[] A = new int[n.length()];
        for (int i = 0; i < n.length(); i++) {
            A[i] = Integer.parseInt(n.substring(i, i + 1)); //[i~i+1)
        }

        for (int i = 0; i < n.length(); i++) { //총 루프 개수
            int max = i;
            for (int j = i + 1; j < n.length(); j++) { //선택정렬로 첫번째 정렬되었다고 가정 후 처리
                if (A[j] > A[max]) { //내림차순으로 최댓값을 찾음
                    max = j; //인덱스를 넘김
                }
                //swap 처리
                if (A[i] < A[max]) { //남은 정렬부분에서 맨 앞 인덱스와 최댓값을 가진 인덱스와 비교해서 더 크면
                    int tmp = A[i];
                    A[i] = A[max];
                    A[max] = tmp;
                }
            }
            sb.append(A[i]);
        }
        System.out.println(sb);
    }
}

