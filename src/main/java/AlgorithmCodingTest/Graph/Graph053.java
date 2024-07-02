package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //"각" 노드에 대한 인접 리스트 저장 필요 (선언) ->  ArrayList<ArrayList<Integer>>
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        //인접 리스트 초기화
        for(int i=0; i<=n;i++) {
            A.add(new ArrayList<>());
        }

        //진입 차수 저장 배열
        int[] indegree = new int[n+1];

        //인접리스트 채워 넣기
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A.get(a).add(b); //단 방향 그래프
            indegree[b]++; //진입 차수 배열 데이터 저장
        }


        //위상 정렬 수행
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++) { //indegree 배열 1~n
            //진입 차수가 0인 노드를 큐에 저장한다.
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            System.out.print(now + " ");
            for(int next : A.get(now)) { //해당 노드가 가리키는 진입 차수 1씩 감소
                indegree[next]--;
                if(indegree[next]==0) {
                    q.offer(next);
                }
            }

        }
    }
}
