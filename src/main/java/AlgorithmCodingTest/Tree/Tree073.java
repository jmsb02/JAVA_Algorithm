package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree073 {
    private static long[] tree;
    static int mod;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        mod = 1000000007;

        //트리의 높이, 사이즈(2^k+1), 리프노드 인덱스(2^k-1) 초기화
        int length = 0;
        while(Math.pow(2,length)<n) {
            length++;
        }
        int treeSize = (int) Math.pow(2,length+1);
        int leftStartIndex = treeSize/2 -1;

        //트리 배열 초기화 (구간곱이니 1로 초기화 후 리프노드 값 초기화)
        tree = new long[treeSize+1];
        for(int i=0;i<tree.length;i++) {
            tree[i] = 1;
        }
        for(int i = leftStartIndex+1;i<=leftStartIndex+n;i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        //트리 초기화
        setTree(treeSize-1);

        StringBuilder sb = new StringBuilder();
        //m+k만큼 for문 돌리면서 값 받기
        for(int i=0;i<m+k;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            if(a==1) {
                update(leftStartIndex + s, e);
            } else if (a==2) {
                s = s + leftStartIndex;
                e = e + leftStartIndex;
                sb.append(multiply(s,(int)e)).append("\n");
            } else {
                return;
            }
        }
        System.out.println(sb.toString().trim());
        br.close();

    }


    private static void setTree(int idx) {
        while (idx != 1) {
            tree[idx / 2] = tree[idx / 2] * tree[idx] % mod;
            idx--;
        }
    }

    private static void update(int idx, long value) {
        tree[idx] = value; //c값으로 업데이트
        while(idx>1) {
            idx = idx/2; //부모노드
            tree[idx] = (tree[idx*2]* tree[idx*2+1])%mod; //자식 둘 곱하고 mod로 나눈 값을 tree[idx]에 넣어준다.
        }
    }

    private static long multiply(int s, int e) {
        long partMul = 1;
        while (s <= e) {
            if (s % 2 == 1) {
                partMul = partMul * tree[s] %mod;
                s++;
            }
            if (e % 2 == 0) {
                partMul = partMul * tree[e] %mod;
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return partMul;
    }

}
