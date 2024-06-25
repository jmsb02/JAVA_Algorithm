package AlgorithmCodingTest.Number_Theory;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NT044 {

    static ArrayList<Node>[] A; // 인접 리스트
    static long lcm;
    static long[] D; // 각 노드값 저장 배열
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 변수 초기화
        A = new ArrayList[n];
        lcm = 1;
        D = new long[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<Node>(); // 1. 인접 리스트 배열 초기화
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            A[a].add(new Node(b, p, q));
            A[b].add(new Node(a, q, p)); // 반대 방향도 추가

            lcm *= (p*q /gcd(p,q));
//            lcm = lcm(lcm, p * q / gcd(p, q)); // 2. 값을 받으면서 최소공배수 업데이트 (모든 비율을 통일된 기준으로 맞추기 위해)
        }

        D[0] = lcm; // 3. 임의의 시작점에 최소공배수 값 저장
        DFS(0);
        long mgcd = D[0]; // 최대 공약수를 저장하는 변수

        for (int i = 1; i < n; i++) {
            mgcd = gcd(mgcd, D[i]); // 최대 공약수 업데이트
        }

        for (int i = 0; i < n; i++) {
            System.out.print(D[i] / mgcd + " "); // 최대 공약수로 나누어 처리
        }
    }

    private static void DFS(int node) {
        // 현재 노드 방문 처리
        visited[node] = true;
        // 현재 노드 연결된 모든 노드 탐색
        for (Node n : A[node]) {
            // 다음 값 찾고
            int next = n.getB();
            // 아직 방문하지 않았다면
            if (!visited[next]) {
                // D 배열 업데이트
                D[next] = D[node] * n.getQ() / n.getP();
                DFS(next);
            }
        }
    }

    private static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    private static class Node {
        int b;
        int p;
        int q;

        public Node(int b, int p, int q) {
            this.b = b;
            this.p = p;
            this.q = q;
        }

        public int getB() {
            return b;
        }

        public int getP() {
            return p;
        }

        public int getQ() {
            return q;
        }
    }
}
