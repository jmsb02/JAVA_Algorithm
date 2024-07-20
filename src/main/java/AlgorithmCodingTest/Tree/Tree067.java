package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tree067 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //인접리스트 선언 및 초기화
        A = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        //값 할당
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a].add(b);
            A[b].add(a);
        }

        //방문 저장 배열 초기화
        visited = new boolean[n + 1];

        //결과 배열 초기화
        result = new int[n + 1];

        DFS(1);

        for (int i = 2; i <= n; i++) {
            System.out.println(result[i]);
        }
    }

    private static void DFS(int idx) {
        visited[idx] = true;
        for (int i : A[idx]) {
            if (!visited[i]) {
                result[i] = idx;
                DFS(i);
            }
        }
    }
}

