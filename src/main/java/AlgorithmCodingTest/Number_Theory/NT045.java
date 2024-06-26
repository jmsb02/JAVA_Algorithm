package AlgorithmCodingTest.Number_Theory;

import java.io.*;
import java.util.StringTokenizer;
public class NT045 {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            long ABgcd  = gcd(a,b);
            if (c%ABgcd != 0) {
                System.out.println(-1);
            }
            else {
                int Cgcd = (int)(c/ABgcd);
                long[] EArray = Euclidean(a,b); //최종 x,y
                System.out.println(EArray[0]*Cgcd + " " + EArray[1]*Cgcd);

            }

        }

        private static long[] Euclidean(int a, int b) {
            long[] longs = new long[2]; //나머지, 몫 담는 배열
            if (b==0) {
                longs[0] = 1; //x=1, y=0으로 설정
                return longs;
            }

            long q = a/b; //몫
            long[] v = Euclidean(b,a%b);
            longs[0] = v[1];
            longs[1] = v[0] - v[1] *q;
            return longs;
        }

        private static long gcd(long a, long b) {
            if (b==0)
                return a;
            else
                return gcd(b,a%b);
        }
    }

