package AlgorithmCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy036 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("-"); //-를 기준으로 값들을 나눔 ex. [55, 50+40]

        int result = 0;
        for(int i=0;i<split.length;i++){
            int combine_result = combine(split[i]);
            if(i==0) {
                result += combine_result;
            }
            else {
                result -= combine_result;
            }
        }
        System.out.println(result);
    }

    private static int combine(String str) {
        int sum = 0;
        // 55와 같은 숫자 하나만 입력되어도 split("[+]")는 ["55"]를 반환하며, 이는 문제를 일으키지 않는다.
        String[] split = str.split("[+]");
        for(int i=0;i<split.length;i++){
            sum += Integer.parseInt(split[i]);
        }
        return sum;
    }
}
