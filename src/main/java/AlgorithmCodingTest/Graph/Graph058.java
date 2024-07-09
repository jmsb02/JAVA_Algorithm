package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //도시 수 -> 노드
        int m = Integer.parseInt(st.nextToken());  //도로 수  -> 엣지
        int k = Integer.parseInt(st.nextToken());  //k번째 최단 경로
        //인접 행렬로 구현 -> 1. 빠른 접근 속도, 2. 간선이 많을 때 효율적
        int[][] w = new int[1001][1001];
        //각각 배열의 크기가 하나가 k인 우선순위 큐
        PriorityQueue<Integer>[] distanceQ = new PriorityQueue[n+1];

        //우선순위큐는 최소힙이지만 최대힙으로 바꿔서 처리
        //이유: 크기가 k 초과시 들어오는 값과 가장 "큰"값(가장 먼 값)과 비교해서 업데이트해줘야 하기 때문

        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        };

        //우선순위 큐 초기화 - k가 초기 용량, cp - 정렬 기준
        for(int i=0;i<n+1;i++) {
            distanceQ[i] = new PriorityQueue<Integer>(k, cp);
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            w[a][b] = c;
        }

        //다익스트라 알고리즘을 위한 우선순위 큐 선언
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        pq.add(new Node(1, 0)); //노드 1이 시작점이고 가중치 0 즉, 초기값 넣어줌
        distanceQ[1].add(0); //1->1로 갈 때는 가중치 0이니까 0으로 설정
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            for(int next = 0; next <= n; next++) {
                if(w[current.node][next] != 0) { //현재 노드 -> 다음 노드로 직접 연결된 노드가 있으면
                    //저장 경로 개수에 따라 로직 분리

                    //저장된 경로가 k개가 안 될 경우 -> 그냥 저장
                    //다음 노드까지의 경로 비용이 k보다 작으면
                    if(distanceQ[next].size() < k) {
                        //현재 노드 + 다음 노드로 이동할 때 드는 비용(가중치)를 next에 저장한다.
                        distanceQ[next].add(current.value + w[current.node][next]);
                        pq.add(new Node(next, current.value + w[current.node][next]));
                    }
                    //저장된 경로가 k개이고, 현재 가장 큰 값(최대힙 기준 맨 위)보다 작을 때만 추가
                    else if (distanceQ[next].peek() > current.value + w[current.node][next]) {
                        distanceQ[next].poll();
                        distanceQ[next].add((current.value) + w[current.node][next]);
                        pq.add(new Node(next, current.value + w[current.node][next]));
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            if(distanceQ[i].size() == k) {
                System.out.println(distanceQ[i].peek());
            } else {
                System.out.println(-1);
            }
        }

    }
}

 class Node implements Comparable<Node> {
    int node;
    int value;

    Node(int node, int value) {
        this.node = node;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.value, o.value);
    }
}

