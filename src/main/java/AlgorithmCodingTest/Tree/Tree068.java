package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tree068 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int root, tmp, m, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //인접리스트 크기 할당 및 초기화
        A = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<>();
        }

        //방문 배열 선언 및 초기화
        visited = new boolean[n];

        //인접리스트에 값 할당
        StringTokenizer st = new StringTokenizer(br.readLine());
        root = 0;
        tmp = 0; //인덱스
        while (st.hasMoreTokens()) {
            int value = Integer.parseInt(st.nextToken());
            if (value == -1) root = tmp; //그 때의 인덱스가 root 노드라는 것이기 때문에 인덱스 값 저장
            else {
                A[value].add(tmp);
                A[tmp].add(value);
            }
            tmp++;
        }

        //삭제 노드 값 저장
        m = Integer.parseInt(br.readLine());

        //DFS
        if (root == m) {
            System.out.println(0);
        } else {
            DFS(root);
            System.out.println(result);
        }
    }

    private static void DFS(int idx) {
        visited[idx] = true;
        int cnt = 0; //자식 노드 탐색 변수
        for (int i : A[idx]) { //인접 노드 탐색하면서
            if (!visited[i] && i != m) { //방문하지 않았고, 삭제노드가 아닐 때
                cnt++;
                DFS(i);
            }
        }
        if(cnt==0) {
            result++;
        }
    }
}
