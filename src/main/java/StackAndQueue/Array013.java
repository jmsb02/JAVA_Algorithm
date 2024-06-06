package StackAndQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Array013 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cards = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        //배열 초기화
        for(int i=0;i<cards;i++) {
            queue.add(i+1);
        }

        while(queue.size() != 1) {
            queue.remove(); //맨 위의 값
            int value = queue.remove();
            queue.add(value);
        }
        int result = queue.peek();
        System.out.println(result);
    }
}
