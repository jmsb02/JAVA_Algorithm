package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Search026 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //정점의 개수
        int m = Integer.parseInt(st.nextToken()); //간선의 개수
        int v = Integer.parseInt(st.nextToken()); //시작점

        A = new ArrayList[n + 1]; //배열 크기 초기화

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Integer>(); //인접 리스트 초기화
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a].add(b);
            A[b].add(a);
        }

        //방문할 수 있는 정점 여러 개 -> 정점 번호가 작은 것을 먼저 방문 -> 정점 번호를 오름차순.
        for (int i = 1; i <= n; i++) {
            Collections.sort(A[i]);
        }

        visited = new boolean[n + 1];
        //DFS 시작
        DFS(v);
        System.out.println();

        visited = new boolean[n + 1];
        //BFS 시작
        BFS(v);
    }

    private static void DFS(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        for(int i : A[v]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    private static void BFS(int v) {
        visited[v] = true;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(v);

        while(!queue.isEmpty()) { //queue가 빌 때까지
            int now = queue.poll();
            System.out.print(now + " ");
            for(int i : A[now]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
