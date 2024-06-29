package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Graph048 {
    static boolean[] visited;
    static ArrayList<Integer>[] A; //인접 배열 선언
    static int[] check;
    static boolean IsEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        for (int s = 0; s < k; s++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken()); //3
            int edge = Integer.parseInt(st.nextToken()); //2

            visited = new boolean[node + 1]; //4 -> 0,1,2,3
            A = new ArrayList[node + 1]; //배열 크기 할당
            check = new int[node + 1];
            IsEven = true; //결과값 출력 변

            for (int i = 1; i <= node; i++) {
                A[i] = new ArrayList<>(); //인접 리스트 배열 초기화
            }

            //실제 값 넣기
            for (int i = 0; i < edge; i++) { //1,2 -> 2번
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                A[start].add(end);
                A[end].add(start);
            }

            for (int i = 1; i <= node; i++) { //1~3 노드 탬색 bfs (전체 노드 탐색하는데 시간 복잡도 줄이기 위해 이분그래프가 아니면 바로 break)
                if (IsEven) {
                    DFS(i);
                } else {
                    break;
                }
            }
            if (IsEven)
                System.out.println("YES");
            else
                System.out.println("NO");

        }
    }

    private static void DFS(int s) {
        visited[s] = true;
        for (int i : A[s]) {
            if (!visited[i]) {
                //이전 노드 체크 배열 값 != 현재 노드 체크 배열 값 (바로 직전에 있는 노드와 다른 집합으로 분류)
                check[i] = (check[s] + 1) % 2; //0과 1로 완벽히 분류할 수 있음
                DFS(i);
            } else if (check[s] == check[i]) {
                IsEven = false;
            }
        }

    }
}
