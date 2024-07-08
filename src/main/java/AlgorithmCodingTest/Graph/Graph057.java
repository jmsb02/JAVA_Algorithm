package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //노드의 개수
        int M = Integer.parseInt(br.readLine()); //엣지의 개수

        //인접 리스트 구현
        ArrayList<Node>[] A = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            A[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A[s].add(new Node(e,v));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        //방문 배열 및 최단 거리 저장 배열 선언 및 초기화
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1];
        for(int i=1;i<=N;i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;

        //우선순위 큐 선언 및 관련 로직
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start,0));

        while(!q.isEmpty()) {
            Node cN = q.poll();
            int vertax = cN.vertax;
            if(visited[vertax]) continue;
            else
                visited[vertax] = true;
            for(Node nextNode : A[vertax]) {
                int next_vertax = nextNode.vertax;
                int next_value = nextNode.value;
                if(distance[next_vertax] > distance[cN.vertax] + next_value) {
                    distance[next_vertax] = distance[cN.vertax] + next_value;
                }
                q.add(new Node(next_vertax, distance[next_vertax]));
            }
        }

        System.out.println(distance[end]);

    }
}

class Node implements Comparable<Node>{
    int vertax;
    int value;

    public Node(int vertax, int value) {
        this.vertax = vertax;
        this.value = value;
    }

    public int compareTo(Node node) {
        return Integer.compare(this.value, node.value);
    }

}
