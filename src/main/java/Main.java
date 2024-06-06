
import java.io.*;
import java.util.*;

import static java.lang.Math.abs;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pQ = new PriorityQueue<>(((o1, o2) -> {
            int first = Math.abs(o1);
            int last = Math.abs(o2);
            if (first == last) { //절댓값이 같으면 음수 우선 정렬
                return o1 > o2 ? 1 : -1; //1일 경우 정렬 순서 o2 -> o1, -1일 경우 정렬 순서 o1 -> o2
            } else {
                return first - last; //절댓값 기준 정렬, first - last>0 -> last -> first 순으로 정렬 (예. o1 = -3, o2 = 1)
            }
        })); //우선순위 큐로 최소 힙 구현

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value == 0) {
                if (pQ.isEmpty())
                    System.out.println("0");
                else
                    System.out.println(pQ.poll());
            } else {
                pQ.add(value);
            }
        }
    }

}