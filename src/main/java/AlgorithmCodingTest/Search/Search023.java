package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Search023 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //변수 및 배열 초기화
        int n = Integer.parseInt(st.nextToken()); //노드 개수
        int m = Integer.parseInt(st.nextToken()); //에지 개수
        A = new ArrayList[n + 1];//인접 리스트
        visited = new boolean[n+1]; //방문 리스트

        for(int i=1;i<=n;i++) {
            A[i] = new ArrayList<Integer>(); //A 인접 리스트의 각 ArrayList 초기화
        }

        //A 인접 리스트에 그래프 데이터 저장
        for(int i=0;i<m; i++) { //간선의 개수만큼 반복 (주어진 간선 정보 입력 받은 후 그래프 구성하기 위함)
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());
            A[u].add(v); //u->v
            A[v].add(u); //무방향이므로 v -> u 간선 추가
        }

        int count = 0;
        for(int i=1;i<=n;i++) {
            if (!visited[i]) { //방문하지 않았다면
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    private static void DFS(int v) {
        if (visited[v]) { //이미 방문한 곳이면
            return;
        }
        visited[v] = true; //방문하지 않았을 경우에는 true로 해주고
        for(int i : A[v]) {
            if(visited[i] == false) {
                DFS(i);
            }
        }
    }
}
