package StackAndQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Array010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //변수 및 배열 초기화
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        //stack 선언 및 sb 선언
        java.util.Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        //자연수 선언
        int num = 1;

        boolean result = true;

        for (int i = 0; i < n; i++) {
            //수열 배열값이 자연수보다 크거나 같을 때, 같을 때까지 push해줌.
            if (A[i] >= num) {
                while (A[i] >= num) {
                    stack.push(num++);
                    sb.append("+").append("\n");
                }
                stack.pop(); //같으면 pop
                sb.append("-").append("\n");
            } else {
                // ex. 수열 값은 3인데 num이 5인 경우
                int pop = stack.pop();
                if (A[i] < pop) { //ex. 수열의 수 1,2,5,"3",4 자연수 pop할 때, 1,2,3,"4",5 즉 수열 값<자연수
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    sb.append("-").append("\n");
                }

            }
        }
        if (result) {
            System.out.println(sb);
        }
    }
}
