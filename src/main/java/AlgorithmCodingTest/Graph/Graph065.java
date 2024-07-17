package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graph065 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] tmp;
    static int n, m, cnt;
    static int[] parent; //사이클 검증을 위한 parent배열
    static boolean[][] visited; //BFS로 같은 섬 탐색할 때 필요한 visited 배열
    static ArrayList<ArrayList<int[]>> island; //BFS로 모든 노드 탐색 후 값 저장 배열
    static ArrayList<int[]> each_island; //각각의 섬에서 다리 탐색 시 필요한 값 저장 배열
    static PriorityQueue<Edge> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //2차원 배열에 값 저장
        tmp = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int now = Integer.parseInt(st.nextToken());
                tmp[i][j] = now;
            }
        }

        //1. BFS탐색을 통해 섬 분리
        cnt = 1; //각 섬 분리 위한 변수
        visited = new boolean[n][m];
        island = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //바다가 아니면서 방문하지 않았을 경우 BFS 탐색
                if (tmp[i][j] != 0 & visited[i][j] != true) {
                    BFS(i, j);
                    cnt += 1;
                    //모든 섬 좌표 저장 리스트에 BFS 탐색을 통해 발견된 하나의 섬의 모든 좌표들 저장
                    island.add(each_island);
                }
            }
        }

        //2. 모든 섬에서 상하좌우 탐색 후 다리 지을 수 있는지 확인
        q = new PriorityQueue<>();
        //각 섬의 모든 지점에서 상하좌우 탐색
        for (int i = 0; i < island.size(); i++) {
            //현재 섬에 속한 모든 지점들의 좌표를 담고 있는 리스트 하나씩 뽑음
            ArrayList<int[]> now = island.get(i);
            //현재 섬에 속한 모든 지점들의 좌표들만큼 돌면서
            for (int j = 0; j < now.size(); j++) {
                //그 때의 행, 열, 위치를 변수에 저장한다
                int x = now.get(j)[0];
                int y = now.get(j)[1];
                int loc = tmp[x][y];
                //상하좌우 탐색
                for (int k = 0; k < 4; k++) {
                    int cx = x + dx[k];
                    int cy = y + dy[k];
                    int length = 0;
                    while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                        //탐색하는데 같은 섬의 다른 지점이면 탐색 중단
                        if (tmp[cx][cy] == loc)
                            break;
                            //같은 섬도 아니고 바다가 아닐 때 (다른 섬을 만났을 때)
                        else if (tmp[cx][cy] != 0) {
                            //길이가 2이상이면 q에 저장
                            if (length > 1) {
                                q.add(new Edge(loc, tmp[cx][cy], length));
                            }
                            break;
                        } else {
                            //바다일경우 길이 +=1;
                            length++;
                        }
                        cx += dx[k];
                        cy += dy[k];
                    }
                }
            }
        }
        //3. 탐색 후 우리가 수집한 모든 에지를 오름차순 정렬 후 MST 알고리즘 진행
        //거리 최소 <- 사이클이 돌면 안 됨 <- union-find 실행
        parent = new int[cnt]; //섬의 개수만큼 만들고
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int useEdge = 0; //n-1번 반복해야 하니까 이를 처리하기 위한 변수
        int result = 0;  //엣지의 합(결과값)
        while (!q.isEmpty()) {
            Edge edge = q.poll();
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                result += edge.value;
                useEdge++;
            }
        }
        //섬의 개수 cnt이고 cnt는 1부터 시작 -> 진짜 섬의 개수 cnt-1(노드)이고 여기서 1을 빼줘야 함 -> cnt-2
        if(useEdge == cnt-2) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    static void union(int a,int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static void BFS(int i, int j) { //탐색 후 each_island(탐색한 섬의 좌표 모아둔 배열)에 저장해줘야 함
        Queue<int[]> q = new LinkedList<>();
        each_island = new ArrayList<>(); //현재 점의 좌표를 저장할 리스트
        int[] start = {i, j}; //시작 값을 배열로 넣는 이유 - 탐색을 시작할 여러 초기 좌표나 상태를 한 번에 처리할 수 있음.
        q.add(start);
        each_island.add(start);
        visited[i][j] = true;
        tmp[i][j] = cnt;

        //BFS 탐색
        while (!q.isEmpty()) {
            //현재 위치 꺼내기
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            //상하좌우 탐색
            for (int k = 0; k < 4; k++) {
                int cx = x + dx[k];
                int cy = y + dy[k];
                while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                    //방문한 적 없고 바다가 아니면 같은 섬으로 취급
                    if (visited[cx][cy] == false && tmp[cx][cy] != 0) {
                        addNode(cx, cy, q);
                    } else {
                        break; //바다 만나면 그 방향으로 탐색 x
                    }
                    //다음 칸으로 이동
                    cx += dx[k];
                    cy += dy[k];
                }
            }
        }
    }

    //툭정 위치를 섬의 정보로 넣어 주는 함수
    private static void addNode(int i, int j, Queue<int[]> q) {
        tmp[i][j] = cnt; //현재 섬 번호로 갱신
        visited[i][j] = true; //방문 체크
        int[] next = {i, j}; //다음 노드 좌표 생성
        each_island.add(next); //각 섬 좌표 리스트에 추가
        q.add(next); //큐에 추가하여 BFS 탐색 계속
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int value;

        public Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.value, e.value); //크루스칼 엣지 리스트 -> 가중치 오름차순 정렬 -> PriorityQueue & Comparable 구현
        }
    }
}
