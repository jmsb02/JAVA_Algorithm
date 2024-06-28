package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph047 {
     static boolean visited[]  = new boolean[10001];;
        static int cnt[] = new int[10001];;
        static ArrayList<Integer> A[] = new ArrayList[10001];;
        static Queue<Integer> q = new ArrayDeque<Integer>();

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; i++)
                A[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                A[a].add(b);
            }
            for (int i = 1; i <= n; i++) { //모든 정점에 대하여 BFS 실행
                for (int j = 1; j <= n; j++) {
                    visited[j] = false;
                }

                BFS(i);
            }

            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (max < cnt[i]) {
                    max = cnt[i];
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (cnt[i] == max) //answer배열에서 maxVal와 같은 값을 가진 index를 정답으로 출력
                    sb.append(i).append(" ");
            }
            System.out.println(sb);
        }
        public static void BFS(int node) {
            q.add(node);
            visited[node] = true;
            while (!q.isEmpty()) {
                int top = q.poll();
                for (int i : A[top]) {
                    if (visited[i]) continue;
                    visited[i] = true;
                    cnt[i]++; //신규 정점인덱스의 정답 배열 값을 증가 시키기
                    q.add(i);
                }
            }
        }
    }
