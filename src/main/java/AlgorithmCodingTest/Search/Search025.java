package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Search025 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;

    static boolean arrive;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //사람의 수 및 노드 수
        int m = Integer.parseInt(st.nextToken()); //친구 관계의 수 및 간선 수

        arrive = false;

        A = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        for (int i = 0; i < n; i++) {
            DFS(i,1);
            if (arrive) //깊이가 n일때 arrive 변수를 true로 만들어줌
                break;
        }
        if (arrive) //n만큼의 깊이가 존재한다
            System.out.println("1");
        else {
            System.out.println("0");
        }
    }

    private static void DFS(int v, int dep) {
        //끝나는 조건
        if(dep==5||arrive) {
            arrive = true;
            return;
        }

        //방문 체크 해줌
        visited[v] = true;

        //인접한 리스트에 접근하면서 depth 하나씩 늘려나감
        for(int i:A[v]) {
            if (!visited[i]) {
                DFS(i, dep + 1);
            }
        }
        visited[v] = false; //다른 경로를 탐색할 때 이 노드를 다시 방문할 수 있도록 함.
    }
}
