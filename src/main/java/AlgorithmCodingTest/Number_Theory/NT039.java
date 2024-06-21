package AlgorithmCodingTest.Number_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NT039 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[10000001];

        //배열 초기화
        for(int i=2;i<arr.length;i++) {
            arr[i] =i;
        }

        //에라토스테네스의 체 -> 소수 구하기
        for(int i=2;i<Math.sqrt(arr.length);i++) {
            if(arr[i] == 0)
                continue;
            for(int j=i+i;j<arr.length;j=j+i) {
                arr[j] = 0;
            }
        }

        int i=n;
        //팰린드롬 수 판별
        while(true) {
            if(arr[i] !=0) {
                int judge = arr[i];
                if(isPd(judge)) {
                    System.out.println(judge);
                    break;
                }
            }
            //함수 들갔다 나오면 인덱스 증가
            i++;
        }

    }

    private static boolean isPd(int judge) {
        char[] charArray = String.valueOf(judge).toCharArray(); //int배열 -> char

        //투 포인터 사용 -> 앞 뒤 글자 같은지 판단
        int s=0;
        int e = charArray.length-1;

        while(s<e) {
            if(charArray[s]!=charArray[e]) {
                return false;
            }
            s++;
            e--;
        }
        return true;
    }
}
