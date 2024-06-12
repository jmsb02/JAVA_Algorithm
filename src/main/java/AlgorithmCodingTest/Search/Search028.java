package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Search028 {

    static int n;
    static ArrayList<Edge>[] A;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        A = new ArrayList[n + 1];

        //인접 리스트 Edge 형식으로 초기화
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Edge>();
        }

        //인접 리스트 값 채워 넣기
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            while (true) {
                int node1 = input;
                int node2 = Integer.parseInt(st.nextToken());
                if(node2 == -1) {
                    break;
                }
                int edge = Integer.parseInt(st.nextToken());
                A[node1].add(new Edge(node2, edge));
            }
        }
        distance = new int[n+1];
        visited =new boolean[n +1];
        BFS(1);

        //bfs 한 번 다 돌렸으니 가장 먼 노드 인덱스 찾기
        int max = 1;
        for(int i=2; i<=n;i++) {
            if(distance[max] < distance[i]) {
                max = i;
            } //max = 가장 먼 노드의 인덱스 -> 두 번째 bfs의 시작점
        }

        distance = new int[n+1];
        visited = new boolean[n+1];
        BFS(max);
        Arrays.sort(distance);
        System.out.println(distance[n]);

    }

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;
        while(!queue.isEmpty()) {
            int now_node = queue.poll();
            for(Edge i : A[now_node]) {
                int e = i.e;
                int v = i.value;
                if (!visited[e]) {
                    queue.add(e);
                    visited[e] = true;
                    distance[e] = distance[now_node] + v; //거리 배열 업데이트
                }
            }
        }
    }


    //엣지 -> 값, 가중치
    private static class Edge {
        int e;
        int value;

        public Edge(int e, int value) {
            this.e = e;
            this.value = value;
        }
    }
}
