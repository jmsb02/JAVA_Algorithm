package AlgorithmCodingTest.Number_Theory;

import java.io.*;
import java.util.StringTokenizer;

public class NT043 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long result = gcd(a,b);
        while(result>0) {
            bw.write("1");
            result--;
        }
        bw.flush();
        bw.close();
    }

    private static long gcd(long a, long b) {
        if(b==0) return a;
        else {
            return gcd(b,a%b);
        }
    }
}
