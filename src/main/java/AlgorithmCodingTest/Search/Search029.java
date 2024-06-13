package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Search029 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] search = new int[n];

        for(int i=0;i<n;i++) {
            int value = Integer.parseInt(st.nextToken());
            search[i] = value;
        }
        Arrays.sort(search);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++) {
            boolean find = false;
            int start = 0;
            int end = n-1;
            int value = Integer.parseInt(st.nextToken());
            while(start<=end) {
                int mid = (start+end)/2;

                if(value > search[mid]) {
                    start = mid+1;
                }
                else if (value < search[mid]) {
                    end = mid -1;
                }
                else {
                    find = true;
                    break;
                }
            }
            if(find) {
                System.out.println("1");
            }
            else {
                System.out.println("0");
            }
        }
    }
}
