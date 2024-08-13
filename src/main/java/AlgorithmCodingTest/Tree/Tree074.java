package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tree074 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //15
        depth = new int[n + 1];
        parent = new int[n + 1];
        visited = new boolean[n + 1];

        tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        bfs(1);

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine()); //15
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b)).append("\n");
        }
        System.out.println(sb.toString().trim());
        br.close();

    }

    private static int LCA(int a, int b) {
        while (depth[a] > depth[b]) {
            a = parent[a];
        }
        while (depth[b] > depth[a]) {
            b = parent[b];
        }
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void bfs(int idx) {
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        visited[idx] = true;

        //bfs - 레벨별로 탐색하는데, 각 레벨병 어떻게 되어있는지 모름 -> 정확하게 구하기 위해 이렇게 변수를 설정해야 함
        int tree_level = 1; //현재 트리 높이
        int now_size = 1; //탐색할 노드 수
        int cnt = 0; // 현재 레벨에서 탐색한 노드 수

        while (!q.isEmpty()) {
            Integer now = q.poll();
            for (int next : tree[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    parent[next] = now; //부모 노드 저장
                    depth[next] = tree_level; //노드 depth 저장
                }
            }
            cnt++; //cnt를 증가시키면서 탐색한 노드 수 체크

            if (cnt == now_size) { //depth당 노드 다 체크했다면
                cnt = 0; //cnt를 0으로 리셋
                now_size = q.size(); //큐 크기로 업데이트 후 다음 레벨에 탐색할 노드 수 결정
                tree_level++; //다음 depth로 이동
            }
        }
    }
}