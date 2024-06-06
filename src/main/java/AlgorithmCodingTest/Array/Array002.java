package AlgorithmCodingTest.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Array002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); //3
        int[] arr1 = new int[n];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); //40 80 60
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken()); // [40, 80, 60]
            sum += arr1[i];
        }
        Arrays.sort(arr1);

        int m = arr1[n-1];

        double result = ((sum*100.0)/(n*m)); //P

        System.out.println(result);

    }
}
