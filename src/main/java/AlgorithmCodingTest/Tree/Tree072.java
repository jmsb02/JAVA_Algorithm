package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree072 {
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //n, m
        int n = Integer.parseInt(st.nextToken()); //10
        int m = Integer.parseInt(st.nextToken());

        //1. 트리의 길이, 트리의 사이즈, 시작 인덱스 구하기
        int length = 0;
        while (Math.pow(2, length) < n) {
            length++;
        }
        int treeSize = (int) (Math.pow(2, length + 1)); //2^length+1
        int leftStartIndex = treeSize / 2 - 1; //시작인덱스, 트리 인덱스와 시작 인덱스 맞추기 위해 -1해줌


        tree = new long[treeSize + 1];
        //최솟값을 구하기 위해 배열을 최댓값으로 초기화
        for (int i = 0; i < tree.length; i++) {
            tree[i] = Integer.MAX_VALUE;
        }

        //트리 배열 초기화
        for (int i = leftStartIndex + 1; i <= leftStartIndex + n; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        //초기 트리 설정
        setTree(treeSize - 1);

        StringBuilder sb = new StringBuilder();
        //최솟값 로직
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            s = s + leftStartIndex;
            e = e + leftStartIndex;
            sb.append(prefixMin(s,e)).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static long prefixMin(int s, int e) {
        long Min = Long.MAX_VALUE;
        while (s <= e) {
            if (s % 2 == 1) {
                Min = Math.min(Min,tree[s]);
                s++;
            }
            if (e % 2 == 0) {
                Min = Math.min(Min,tree[e]);
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return Min;
    }

    private static void setTree(int idx) {
        while (idx > 1) {
            tree[idx / 2] = Math.min(tree[idx / 2], tree[idx]);
            idx--;
        }
    }
}
