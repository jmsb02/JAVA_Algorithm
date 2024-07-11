package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //노드 수
        int start_dosi = Integer.parseInt(st.nextToken()); //시작 도시
        int end_dosi = Integer.parseInt(st.nextToken()); //도착 도시
        int m = Integer.parseInt(st.nextToken()); //엣지 수

        //엣지 리스트 생성 및 크기 할당
        Edge edges[] = new Edge[m];
        //엣지 리스트 초기화
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start,end,value);
        }

        //최단 거리 배열 크기 할당 및 초기화
        long[] distance = new long[n];
        for(int i=0;i<n;i++) {
            distance[i] = Long.MIN_VALUE;
        }

        //도시 별 돈 액수 초기화
        long[] price = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            price[i] = Long.parseLong(st.nextToken());
        }

        //우리가 구하고자 하는 것 -> 금액 최대화니까 초기값을 price[start_dosi]로 초기화
        distance[start_dosi] = price[start_dosi];

        //벨만 포드 알고리즘
        //양수 사이클 전파를 위해 충분히 큰 수로 for문 돌려줌
        for(int i=0;i<=n+100;i++) {
            for(int j=0;j<m;j++) {
                Edge edge = edges[j];
                int start = edge.start;
                int end = edge.end;
                int edge_value = edge.value;
                //방문하지 않는 노드를 출발점으로 하는 엣지는 무시
                if(distance[start] == Long.MIN_VALUE) {
                    continue;
                }
                //시작점이 양수사이클에 연결되어 있으면 도착점도 양수사이클에 연결한다. (금액 최대화)
                else if (distance[start] == Long.MAX_VALUE) {
                    distance[end] = Long.MAX_VALUE;
                }
                // 종료 노드 값 < 출발 노드 값 + 도착 도시 수입 - 비용(가중치) -> 최대 수익 업데이트
                else if (distance[end] < distance[start] + price[end] - edge_value) {
                    distance[end] = distance[start] + price[end] - edge_value;
                    //n-1번 넘어갔는데도 업데이트가 되면 양수 사이클이 존재 -> 수익 최댓값 갱신 가능
                    if(i>=n-1) distance[end] = Long.MAX_VALUE;
                }
            }
        }
        if(distance[end_dosi] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else if(distance[end_dosi] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(distance[end_dosi]);
        }

    }
}

class Edge {
    int start;
    int end;
    int value;

    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
}