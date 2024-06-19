package AlgorithmCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Greedy034 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //변수 초기화
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Comparator.reverseOrder()); //양수는 큰수부터 처리해야함 -> reverse
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();
        int one = 0, zero = 0;

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value < 0) {
                minusPq.add(value);
            } else if (value == 0) {
                zero += 1;
            } else if (value == 1) {
                one += 1;
            } else { //value >=1
                plusPq.add(value);
            }
        }

        int sum =0;
        //양수 배열 로직
        while(plusPq.size()>1) {
            int plus1 = plusPq.remove();
            int plus2 = plusPq.remove();
            sum += (plus1*plus2);
        }
        //남아있을 때 양수 배열 같은 경우 그냥 더해줌
        if(!plusPq.isEmpty()) {
            sum += plusPq.remove();
        }

        //음수 배열 로직
        while(minusPq.size()>1) { //묶어서 곱하려면 사이즈가 2 이상이여야 함
            int minus1 = minusPq.remove();
            int minus2 = minusPq.remove();
            sum += (minus1*minus2);
        }

        if(!minusPq.isEmpty()) {
            if(zero==0) { //0이 하나도 존재하지 않으면 그냥 음수 값 더해줌
                sum += minusPq.remove();
            }
        }

        //1은 또 그냥 더해줌
        sum += one;
        System.out.println(sum);
    }
}
