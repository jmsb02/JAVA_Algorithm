package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph059 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //노드 수
        int m = Integer.parseInt(st.nextToken()); //엣지 수

        Edge edges[] = new Edge[m+1]; //엣지 리스트 선언 및 크기 할당

        //값 받은 후 엣지리스트에 저장
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start,end,value);
        }

        //최단거리 저장 배열 선언 및 크기 할당
        long[] distance = new long[n+1];
        //최단거리 배열 초기화
        for(int i=0;i<n+1;i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[1] = 0;

        //벨만 포드 알고리즘 (n-1번)
        for(int i=1;i<n;i++) { //1번노드부터 노드 당
            for(int j=0;j<m;j++) { //엣지를 탐색함
                Edge edge = edges[j];
                //최단 거리 업데이트
                if(distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.value) {
                    distance[edge.end] = distance[edge.start] + edge.value;
                }
            }
        }
        //음수 사이클 탐색 (1번)
        boolean minus = false;
        for(int i=0;i<m;i++) {
            Edge edge = edges[i];
            //최단 거리 업데이트
            if(distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.value) {
                distance[edge.end] = distance[edge.start] + edge.value;
                minus = true;
            }
        }
        //출력
        if(!minus) { //음수 사이클이 아니면
            for(int i=2;i<=n;i++) {
                if(distance[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(distance[i]);
                }
            }
        } else {
            System.out.println(-1);
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
