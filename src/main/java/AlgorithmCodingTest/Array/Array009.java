package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Array009 {

    static int[] checkArr; //A C G T
    static int[] myArr;  //판단하는 배열
    static int checkSecret; //A C G T가 해당 myArr 값과 일치하는지 판단하는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int S = Integer.parseInt(st.nextToken()); //DNA 문자열의 길이
        int P = Integer.parseInt(st.nextToken()); //부분 문자열의 길이

        int result = 0; //반환되는 결과값

        char A[]; // = new char[S]; //DNA 문자열 담는 배열

        checkArr = new int[4]; //A C G T
        myArr = new int[4];  //판단하는 배열
        checkSecret = 0; //A C G T가 해당 myArr 값과 일치하는지 판단하는 변수
        A = br.readLine().toCharArray();


        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken()); //2 0 1 1
            if (checkArr[i] == 0) {
                checkSecret++;
            }
        }

        //초기 부분 문자열 처리 부분
        for (int i = 0; i < P; i++) {
            ADD(A[i]); //해당 문자열을 돌면서 그 값이 일치하는지 확인해주도록 하는 함수
        }

        if (checkSecret == 4) {
            result += 1;
        }

        //슬라이싱 윈도우 시작
        for (int i = P; i < S; i++) { //인덱스 기준으로 i는 초기 배열 끝 부분 바로 뒷 값 -> 맨 앞 부분만 구해주면 됨
            int j = i - P;
            ADD(A[i]);
            Remove(A[j]);
            if (checkSecret == 4) {
                result++;
            }
        }
        System.out.println(result);
        br.close();
    }

    private static void ADD(char c) {
        switch (c) {
            case 'A':
                myArr[0]++;
                if (checkArr[0] == myArr[0]) {
                    checkSecret++;
                }
                break;
            case 'C':
                myArr[1]++;
                if (checkArr[1] == myArr[1]) {
                    checkSecret++;
                }
                break;

            case 'G':
                myArr[2]++;
                if (checkArr[2] == myArr[2]) {
                    checkSecret++;
                }
                break;

            case 'T':
                myArr[3]++;
                if (checkArr[3] == myArr[3]) {
                    checkSecret++;
                }
                break;

        }
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (checkArr[0] == myArr[0]) {
                    checkSecret--;
                }
                myArr[0]--;
                break;
            case 'C':
                if (checkArr[1] == myArr[1]) {
                    checkSecret--;
                }
                myArr[1]--;
                break;
            case 'G':
                if (checkArr[2] == myArr[2]) {
                    checkSecret--;
                }
                myArr[2]--;
                break;
            case 'T':
                if (checkArr[3] == myArr[3]) {
                    checkSecret--;
                }
                myArr[3]--;
                break;
        }
    }
}
