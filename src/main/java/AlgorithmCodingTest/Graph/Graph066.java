package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph066 {
    static int sum, tmp, useEdge, mst_result, total;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();


        //랜선 총합, 엣지리스트 형식으로 값을 큐에 저장
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); //abc
            char[] charArray = st.nextToken().toCharArray(); //[a,b,c]
            for (int j = 0; j < n; j++) {
                tmp = 0;
                if ('a' <= charArray[j] && charArray[j] <= 'z') {
                    tmp = charArray[j] - 'a' + 1;
                } else if ('A' <= charArray[j] && charArray[j] <= 'Z') {
                    tmp = charArray[j] - 'A' + 27;
                }
                //문자값을 숫자로 변환 후 총합을 구한다.
                sum += tmp;
                //다른 컴퓨터를 연결할 때 엣지리스트에 저장
                if (i != j && tmp != 0) {
                    pq.add(new Edge(i, j, tmp));
                }
            }
        }

        //union-find를 위한 parent 배열 생성 및 값 저장
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        //크루스칼 알고리즘
        useEdge = 0;
        mst_result = 0;
        while (!pq.isEmpty()) { //NullPointerException 방지
            Edge edge = pq.poll();
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                mst_result += edge.value;
                useEdge++;
            }
        }
        total = sum - mst_result;
        if (useEdge == n - 1) {
            System.out.println(total);
        } else {
            System.out.println(-1);
        }

    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    public static void union(int s, int e) {
        int a = find(s);
        int b = find(e);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int value;

        public Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
