package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //각 노드 탐색해야 하니 ArrayList<ArrayList<Integer>>로 선언
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();

        //인접리스트 초기화
        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
        }

        //진입차수 배열 선언
        int[] indgree = new int[n + 1];

        //각 시간 담는 배열 선언
        int[] time = new int[n + 1];

        //시간 배열 및 진입차수 배열 초기화
        for (int i = 1; i <= n; i++) { //5
            StringTokenizer st = new StringTokenizer(br.readLine());
            int timeOfnode = Integer.parseInt(st.nextToken());
            time[i] = timeOfnode;
            //진입 차수 배열 초기화
            while (true) {
                int indgree_node = Integer.parseInt(st.nextToken()); //1
                if(indgree_node == -1) {
                    break;
                }
                A.get(indgree_node).add(i); //인덱스로 처리
                indgree[i]++; //진입차수 배열 초기화
            }
        }

        //0부터 queue에 추가
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++) {
            if(indgree[i] == 0) {
                q.offer(i); //1
            }
        }

        //정답 배열 선언
        int[] result = new int[n+1];

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int next : A.get(now)) { //가리키는 애들을 진입 차수 배열 값 -=1;
                indgree[next]--;
                //시간 업데이트
                //결과값 업데이트 -> (현재 시간, 이전 시간 + 결과값에 담겨진 시간 값) 최대
                result[next] = Math.max(result[next], time[now] + result[now]);
                if(indgree[next] ==0) {
                    q.offer(next);
                }
            }
        }

        for(int i=1;i<=n;i++) {
            //구하고자 하는 것 : 각 건물을 짓기 위해 필요한 최소 시간
            //지연시간 + 기존에 걸리는 시간
            System.out.println(result[i] + time[i]);
        }
    }
}
