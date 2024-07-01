package AlgorithmCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph050 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        for(int i=1;i<=n;i++) { //초기 배열 원소 초기화
            parent[i] = i;
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int judge = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(judge==0) {
                union(a,b);
            } else {
                //find 연산 - 같은 집합 원소인지 확인하고 결과값 출력
                if(check(a,b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }

    }

    private static void union(int a, int b) {
        //대표노드를 찾아서 연결
        a= find(a);
        b = find(b);
        if(a!=b) {
            parent[b] = a; //a를 대표노드로 설정
        }
    }

    private static int find(int a) {
        //대표 노드를 찾는 메서드
        if(a==parent[a]) { //idx == value -> 대표노드
            return a;
        } else {
            //대표 노드가 아닐 경우
            return parent[a] = find(parent[a]); //value를 idx로 변환해서 재귀, 끝날 때마다 대표 노드의 값 업데이트
        }

    }

    private static boolean check(int a, int b) {
        a = find(a);
        b = find(b);
        if (a==b) {
            return true;
        } else {
            return false;
        }
    }
}
