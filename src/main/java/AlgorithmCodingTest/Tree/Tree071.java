package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree071 {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //1. 트리 초기화 하기
        //(1) 트리 크기 설정 2^(length+1)
        int length = 0;
        while(Math.pow(2,length) <= n) { // 2^legth >= n일때의 length 최솟값 나옴
            length++;
        }
        int treeSize = (int)Math.pow(2, length+1);

        //(2) 리프노드 시작 인덱스
        int leefStartIndex = treeSize/2-1;
        //(3) 트리에 값 저장
        tree = new long[treeSize+1];
        for(int i=leefStartIndex+1;i <=leefStartIndex+n;i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        //(4) 초기 트리 생성
        setTree(treeSize-1); //마지막 인덱스

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m+k;i++) {
            //a - 1 or 2, b - index, c - update 가능 => a,b int c long
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a==1) {
                //주어진 인덱스 -> 리프노드 인덱스로 변환 후 update -> 리프 노드 시작 인덱스 + 주어진 인덱스로 잡고 품
                update(leefStartIndex + b, c);
            }else if(a==2) {
                //[b~c] -> update와 똑같이 리프 노드의 인덱스로 변환 후 부분합 구함
                b = leefStartIndex + b;
                c = leefStartIndex + c;
                sb.append(prefix_sum(b, (int) c)).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    private static long prefix_sum(int s, int e) {
        //부분합을 담는 변수 선언
        long preSum = 0;
        //s>e될 때 탐색 종료
        while(s<=e) {
            if(s%2==1) { //독립 노드로 선택(더해줌)
                preSum+=tree[s];
            }
            if (e%2==0) {
                preSum += tree[e];
            }
            s = (s+1)/2;
            e = (e-1)/2;
        }
        return preSum;
    }

    private static void update(int idx, long val) { //ex. 인덱스 3에 해당하는 값을  6으로 변경해주세요
        long diff = val - tree[idx];
        while(idx>0) {
            tree[idx] += diff;
            idx = idx/2;
        }
    }

    //최초 부분합 트리 생성
    private static void setTree(int i) { //15
        while(i>1) {
            tree[i/2] += tree[i];
            i--;
        }
    }
}
