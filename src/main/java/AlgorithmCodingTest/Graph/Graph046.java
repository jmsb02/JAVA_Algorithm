package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph046 {

    static ArrayList<Integer>[] A; //인접리스트 배열 선언
    static boolean[] visited;
    static int[] I;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //도시의 개수 (node)
        int m = Integer.parseInt(st.nextToken()); //도로의 개수 (edge)
        int k = Integer.parseInt(st.nextToken()); //거리 정보 (최단 거리, 결과 매칭값)
        int x = Integer.parseInt(st.nextToken()); //출발 도시의 번호 (시작점)

        A = new ArrayList[n + 1]; //인접리스트 배열 크기 할당
        I = new int[n + 1];

        for (int i = 1; i <= n; i++) { //인접리스트 배열 초기화
            A[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e); //단방향
        }

        visited = new boolean[n + 1];

        BFS(x);

        boolean b= false; //-1 판별
        for (int i = 1; i <= n; i++) {
            if (I[i] == k) {
                System.out.println(i);
                b = true;
            }
        }
        if(!b) { //k값이 I리스트에 존재 x
            System.out.println(-1);
        }

    }

    private static void BFS(int x) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visited[x] = true;

        while (!q.isEmpty()) {
            int top = q.poll();
            for (int i : A[top]) {
                if (!visited[i]) {
                    visited[i] = true;
                    I[i] = I[top] + 1; //depth -> 값 측정
                    q.add(i);
                }
            }
        }
    }
}
