package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        //인접리스트 선언
        ArrayList<ArrayList<Node>> A = new ArrayList<>();

        //역방향 인접리스트 선언
        ArrayList<ArrayList<Node>> reverseA = new ArrayList<>();

        //인접리스트 및 역방향 인접리스트 초기화
        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
            reverseA.add(new ArrayList<>());
        }

        //진입 차수 배열 선언 및 크기 할당
        int[] indgree = new int[n + 1];

        //인접, 역방향 인접리스트, 진입 차수 배열 값 채워주기
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A.get(s).add(new Node(e, v)); //인접리스트 정보 저장
            reverseA.get(e).add(new Node(s, v)); //역방향 엣지 정보 저장
            indgree[e]++; //진입 차수 배열 초기화
        }

        //출발 도시, 도착 도시
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start_dosi = Integer.parseInt(st.nextToken());
        int end_dosi = Integer.parseInt(st.nextToken());

        //위상 정렬
        Queue<Integer> q = new LinkedList<>();
        q.offer(start_dosi);

        //임계 경로 배열 선언 및 초기화
        int[] criticalPath = new int[n + 1];

        //임계차수 배열 값 채워주기
        while (!q.isEmpty()) {
            int now = q.poll();
            for (Node next : A.get(now)) { //가리키고 있는 노드들을 하나씩 뽑으면서
                //진입 차수 배열 업데이트 --;
                indgree[next.target_Node]--;
                //임계 경로 배열에 최장 경로를 저장
                //다 마치고 마지막 도시에서 만나기로 함 -> "최장"
                //다음 노드까지 최장 경로 저장 = Math.max(다음 노드까지 최장 경로 저장, 현재 노드까지의 최장 경로 + 현재 -> 다음 넘어갈 때의 가중치
                criticalPath[next.target_Node] = Math.max(criticalPath[next.target_Node], criticalPath[now] + next.value);
                if (indgree[next.target_Node] == 0) {
                    q.offer(next.target_Node);
                }
            }
        }

        //위상 정렬 reverse -> 최적의 경로를 효율적으로 탐색 가능

        //1분도 쉬지 않고 달려야 하는 도로의 수
        int result_cnt = 0;

        //중복 탐색 x
        boolean[] visited = new boolean[n + 1];

        q = new LinkedList<>();
        q.offer(end_dosi);
        visited[end_dosi] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Node next : reverseA.get(now)) {

                //이전 도시의 임계 경로 값 == 이 도시의 임계 경로 값 + 도로 시간(value)  -> 이 도로를 1분도 쉬지 않고 달려야 하는 도로로 카운팅
                //예제 1로 예시를 들면 criticalPath[7] + 5 == criticalPath[6]
                if (criticalPath[next.target_Node] + next.value == criticalPath[now]) {
                    result_cnt++;

                    //중복 카운트 방지
                    if (visited[next.target_Node] == false) {
                        visited[next.target_Node] = true;
                        q.offer(next.target_Node);
                    }
                }

            }
        }
        System.out.println(criticalPath[end_dosi]);
        System.out.println(result_cnt);


    }
}


class Node {
    int target_Node; //다음 노드
    int value; //가중치

    public Node(int e, int v) {
        target_Node = e;
        value = v;
    }
}
