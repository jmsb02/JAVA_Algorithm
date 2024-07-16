package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph064 {
    static int[] parent;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        //엣지 리스트 선언 및 초기화
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            pq.add(new Edge(start, end, value));
        }

        //parent 배열 선언 및 초기화
        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        //n-1번 돌면서 크루스칼 알고리즘 수행
        int edgeCount = 0;
        while (edgeCount < v-1) {
            Edge edge = pq.poll();
            int start = edge.start;
            int end = edge.end;
            int value = edge.value;
            if (find(start) != find(end)) { //사이클이 존재하지 않는 경우
                union(start, end); //1. union 연산
                cnt+=value; //2, 결과값 더해줌
                edgeCount++;
            }
        }
        System.out.println(cnt); //최소 가중치 출력


    }


    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    public static void union(int a, int b) {
        int start = find(a);
        int end = find(b);
        if (start != end) {
            parent[end] = start;
        }
    }


    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int value;

        public Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }


        @Override
        public int compareTo(Edge e) {
            return Integer.compare(value, e.value);
        }
    }
}
