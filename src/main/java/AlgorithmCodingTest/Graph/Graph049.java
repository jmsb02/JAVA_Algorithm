package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph049 {
    //총 6가지 경우의 수 탐색을 위한 배열 선언
    static int[] Sender = {0,0,1,1,2,2};
    static int[] Receiver = {1,2,0,2,0,1};
    static boolean[][] visited; //A,B만 알고 있으면 C값 유추 가능
    static int[] now; //값 저장 배열
    static boolean[] answer; //

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        now = new int[3];
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        now[0] = a;
        now[1] = b;
        now[2] = c;

        visited = new boolean[201][201];
        answer = new boolean[201];
        bfs(); //물통 - 노드, 물 옮기는 과정 - 간선, 탐색하는 문제 -> 최단 경로로 중복 없이 탐색 => bfs
        for(int i=0;i<answer.length;i++) {
            if(answer[i])
                System.out.print(i + " ");
        }
    }

    private static void bfs() {
        Queue<AB> queue = new LinkedList<>();
        queue.add(new AB(0,0));
        visited[0][0] = true; //방문 체크 해주고
        answer[now[2]] = true; //현재 c의 값 체크

        while(!queue.isEmpty()) {
            AB top = queue.poll();
            int a = top.A;
            int b = top.B;
            int c = now[2] - a - b;
            for(int k=0;k<6;k++) { //6가지 경우 처리
                int[] next = {a,b,c}; //A,B,C 각각 물통에 있는 물의 양
                next[Receiver[k]] += next[Sender[k]]; //값 보내는 로직
                next[Sender[k]] = 0;
                if (next[Receiver[k]] > now[Receiver[k]]) { //물을 보냈는데 그 물통의 크기보다 커 즉, 넘칠 때
                    //넘치는 만큼 (남은 값) Sender에 넣어줌
                    next[Sender[k]] = next[Receiver[k]] - now[Receiver[k]];
                    next[Receiver[k]] = now[Receiver[k]]; //최대 크기만큼 넣어줌
                }
                if(!visited[next[0]][next[1]]) { //뽑은 값(top)에 대한 방문처리
                    visited[next[0]][next[1]] = true;
                    queue.add(new AB(next[0], next[1])); //for문을 k로 탐색하고 물 옮기고 다 한 그 이후의 새로운 상태를 넣어줌
                    if (next[0] ==0) //A 물의 양이 0일 때
                        answer[next[2]] = true; //C값을 출력해줘야 함
                }

            }
        }

    }

    private static class AB { //A,B만 알면 C를 알 수 있기 때문에 A,B값 저장하고 처리하는 클래스 생성
        int A;
        int B;

        public AB(int a, int b) {
            A = a;
            B = b;
        }
    }
}
