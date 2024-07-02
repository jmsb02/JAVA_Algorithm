package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph051 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());


        //인접 행렬
        int[][] dosi = new int[n + 1][n + 1];
        //2차원 배열 초기화
        for (int i = 1; i <= n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                dosi[i][j] = Integer.parseInt(split[j - 1]);
            }
        }

        //여행 경로 저장 배열 (마지막 줄)
        int[] route = new int[m + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        //union&find 연산을 위한 대표 노드 저장 배열
        parent = new int[n + 1];
        //대표 노드를 자기 자신으로 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        //인접 행렬 탐색
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dosi[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        //parent대신 route를 통해 결과를 내는 이유
        //우리가 구하고자 하는 것 -> 여행계획에 속한 도시들이 순서대로 주어졌을 때 여행이 가능한지 판별
        //=> 여행 계획 배열(route)에 있는 값들의 대표 노드들이 같은 값을 가지고 있으면 여행이 가능하다고 판단 (YES or NO)
        int idx = find(route[1]);
        for(int i=2;i<route.length;i++) {
            if (idx != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }


    private static void union(int a, int b) {
        //합집합 계산
        //대표 노드 값 찾고 다를 경우 대표 노드 값을 a로 통일
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        //해당 값이 대표노드 값이면 리턴
        if (a == parent[a]) {
            return a;
        } else {
            //그렇지 않을 때 대표 노드 값을 인덱스로 정해서 대표 노드 값을 구한 뒤 그 자리 업데이트
            return parent[a] = find(parent[a]);
        }
    }

}
