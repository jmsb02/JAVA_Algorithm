package AlgorithmCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Greedy033 {
    public static void main(String[] args) throws IOException {
        //데이터 삽입, 삭제, 정렬이 많이 사용 됨 -> 우선순위 큐
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        //배열 초기화
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            queue.add(value);
        }

        //변수 초기화
        int dt = 0;
        int dt2 = 0;
        int sum = 0;
        int result = 0;

        //로직 작성
        while(queue.size()!=1) {
            dt = queue.remove();
            dt2 = queue.remove();
            sum = (dt + dt2);
            queue.add(sum);
            result += sum;
        }
        System.out.println(result);
    }
}
