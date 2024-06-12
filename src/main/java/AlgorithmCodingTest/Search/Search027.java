package AlgorithmCodingTest.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Search027 {

    static int n, m;
    static int[][] A;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //x
        m = Integer.parseInt(st.nextToken()); //y
        A = new int[n][m];

        visited = new boolean[n][m];

        //2차원 배열 초기화
        for (int i = 0; i < n; i++) {
            String text = br.readLine();
            for (int j = 0; j < m; j++) {
                A[i][j] = text.charAt(j) - '0';
            }
        }
        BFS(0,0); //(0,0) ~ (n-1,m-1)
        System.out.println(A[n-1][m-1]);
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i,j});
        visited[i][j] = true;
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int k=0;k<4;k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                if (x>=0 && y>=0 && x<n && y<m) {
                    if(A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        A[x][y] = A[now[0]][now[1]] + 1;
                        queue.add(new int[] {x,y});
                    }
                }
            }
        }
    }
}
