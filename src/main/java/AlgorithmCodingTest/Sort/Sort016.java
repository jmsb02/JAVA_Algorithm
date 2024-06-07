package AlgorithmCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sort016 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        MData[] A = new MData[n];
        for(int i=0;i<n;i++) {
            A[i] = new MData(Integer.parseInt(br.readLine()),i); //데이터 형식의 배열을 만들어서 value랑 index를 동시에 넘긴다.
        }
        Arrays.sort(A); //배열 정렬 (O(nlogn))
        int max = 0;
        for(int i=0;i<n;i++) {
            if (max<A[i].index-i)
                max = A[i].index-i;
        }
        System.out.println(max+1);
    }
}

class MData implements Comparable<MData> {

    int value;
    int index;

    public MData(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(MData o) {
        return this.value - o.value; //value 기준 오름차순 정렬
    }
}
