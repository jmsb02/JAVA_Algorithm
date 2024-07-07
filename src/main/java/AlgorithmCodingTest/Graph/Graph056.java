package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        //인접 리스트 선언 및 초기화
        ArrayList<Node>[] A = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            A[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            A[s].add(new Node(e, val));
        }

        //최단 거리 배열 초기화
        int[] distance = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            //이외의 노드 -> 노드의 개수 * 최대 가중치 + 1;
            distance[i] = 20000 * 10 + 1;
        }
        //최단 거리 배열에서 출발 노드는 0으로 설정
        distance[K] = 0;

        //방문 배열 생성 및 초기화
        boolean[] visited = new boolean[V + 1];

        //다익스트라 알고리즘 -우선순위 큐
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(K, 0)); //시작노드 인덱스와 가중치 0 삽입
//        visited[K] = true; -> 시작점이 다른 경로로 가기전에 이미 방문처리가 되어서 최단 거리 계산 x

        while (!q.isEmpty()) {
            //현재 인덱스 먼저 뽑고 다음의 값들이 방문했으면 continue, 아니면 방문 체크 해줌
            Node now = q.poll();
            int target_Node = now.target_Node;
            if (visited[target_Node]) continue; //방문할 노드가 방문했었는지 안했었는지 구분
            visited[target_Node] = true;

            for (int i = 0; i < A[target_Node].size(); i++) { //두 노드 사이에 엣지가 2개 이상 존재 가능 A[target_Node].size()만큼 for문 돌려서 처리
                Node next_Node = A[target_Node].get(i);
                int next = next_Node.target_Node; //다음 노드
                int value = next_Node.value; //가중치
                //최단 경로 업데이트 (다음 값 > 현재까지의 최단 거리 + 가중치)
                if (distance[next] > distance[target_Node] + value) {
                    distance[next] = distance[target_Node] + value; //최소 거리로 업데이트
                    q.add(new Node(next, distance[next]));
                }
            }
        }
        for (int i = 1; i <= V; i++) {
            if(visited[i]) {
                System.out.println(distance[i]);
            }
            else { //이어져 있지 않음 -> visited[i] == false -> "INF"
                System.out.println("INF");
            }
        }
    }
}

class Node implements Comparable<Node> {
    int target_Node;
    int value;

    Node(int next_node, int value) {
        this.target_Node = next_node;
        this.value = value;
    }

    //우선순위 큐에서 작은 값 우선 정렬
    public int compareTo(Node node) {
        return Integer.compare(this.value, node.value); //우선순위 큐 -> 작은 값 우선 정렬
    }
}
