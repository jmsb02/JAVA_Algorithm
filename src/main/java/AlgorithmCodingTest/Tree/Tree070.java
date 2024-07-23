package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree070 {
    static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new int[26][2]; //A~Z 26, 왼쪽 오른쪽 2
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = st.nextToken().charAt(0) - 'A';
            String left = st.nextToken();
            String right = st.nextToken();

            //자식 노드 없을 때 -1 저장 그렇지 않으면 본 값 저장
            if (left.equals(".")) {
                tree[idx][0] = -1;
            } else {
                tree[idx][0] = left.charAt(0) - 'A';
            }
            if (right.equals(".")) {
                tree[idx][1] = -1;
            } else {
                tree[idx][1] = right.charAt(0) - 'A';
            }
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
    }


    private static void preOrder(int now) {
        //전위 순회 - 가운데 -> 왼 -> 오
        if (now == -1)
            return;
        System.out.print((char) (now + 'A'));
        preOrder(tree[now][0]);
        preOrder(tree[now][1]);
    }

    private static void inOrder(int now) {
        //중위 순회 - 왼 -> 가운데 -> 오
        if (now == -1)
            return;
        inOrder(tree[now][0]);
        System.out.print((char) (now + 'A'));
        inOrder(tree[now][1]);
    }

    private static void postOrder(int now) {
        //후위 순회 - 왼 -> 오 -> 가운데
        if (now == -1)
            return;
        postOrder(tree[now][0]);
        postOrder(tree[now][1]);
        System.out.print((char) (now + 'A'));
    }
}
