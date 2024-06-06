package AlgorithmCodingTest.StackAndQueue;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Array012 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        //결과 처리 인덱스 배열
        int[] result = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        java.util.Stack<Integer> stack = new Stack<>();
        stack.push(0); //최초 스택 초기화

        for (int i = 1; i < n; i++) {
            //pop 경우 비워져 있지 않으면서 stack top에 있는 값이 arr[i]보다 작을 경우
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int top = stack.pop();
                result[top] = arr[i]; //pop한 인덱스에다가 해당 값(오큰수)를 넣어줌
            }
            stack.push(i); //최초랑 들어오는 값이 기존 존재하는 값보다 작을 경우
        }
        while (!stack.isEmpty()) {
            //스택에 빌 때까지
            result[stack.pop()] = -1; //그 인덱스에다가 -1처리
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(result[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
