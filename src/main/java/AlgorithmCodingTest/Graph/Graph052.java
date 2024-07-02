package AlgorithmCodingTest.Graph;

//두 번째줄 친구와 같은 집합에서 속하는지 확인(union연산) 후 count 갯수 세주고 처리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 진실을 아는 사람들 끼리 그룹핑
 * 2. 파티원들 끼리 그룹핑
 * 3. 각 파티에서 진실을 아는 사람 연결되어 있는지 확인 연결되어 있으면 count 증가 x, 연결되어 있지 않다면 count++;
 */
public class Graph052 {
    static int[] parent;
    static int[] TrueArray;
    static List<Integer>[] parties; //각 파티에 참가하는 사람들의 정보를 저장하는 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫 번째 줄 - n과 m 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //두 번째 줄 - 진실을 아는 사람 수와 번호(1~n)
        st = new StringTokenizer(br.readLine());
        int TrueManCount = Integer.parseInt(st.nextToken());
        //진실을 아는 사람이 없으면 그대로 0을 반환
        if (TrueManCount == 0) {
            System.out.println(m);
            return;
        }

        //진실이 아는 사람이 있으면 TrueManCount 개수만큼 배열을 생성하여 값 집어넣는다.
        TrueArray = new int[TrueManCount + 1]; //진실을 아는 사람들 배열
        for (int i = 1; i <= TrueManCount; i++) {
            TrueArray[i] = Integer.parseInt(st.nextToken());
        }

        //그룹핑을 위한 parent 배열 초기화
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        //진실을 아는 사람들끼리 그룹핑
        //1로 그냥 설정해도 되는 이유 -> 어짜피 union되면 다 연결 되기 때문에
        for (int i = 2; i <= TrueManCount; i++) {
            union(TrueArray[1], TrueArray[i]);
        }

        //파티 사람들끼리 그룹핑
        parties = new List[m]; //파티 정보 저장 -> 저장 이유 : 마지막에 값 계산 할 때 하나씩 뽑으면서 얘의 대표노드가 TrueArray에 속한 값의 대표노드랑 비교하기 때문에
        for(int i=0;i<m;i++) {
            parties[i] = new ArrayList<>(); //초기화
            st = new StringTokenizer(br.readLine()); //2 3 4
            int PartyManCount = Integer.parseInt(st.nextToken()); //2
            if (PartyManCount>0) {
                int firstPartMan = Integer.parseInt(st.nextToken()); //3
                parties[i].add(firstPartMan);
                for(int j=1;j<PartyManCount;j++) {
                    int nextMan = Integer.parseInt(st.nextToken());
                    parties[i].add(nextMan);
                    union(firstPartMan,nextMan);
                }
            }
        }

        //각 파티에서 진실을 아는 사람 연결되어 있는지 확인 연결되어 있으면 count 증가 x, 연결되어 있지 않다면 count++;
        int cnt = 0;
        for(int i=0; i<m;i++) { //파티 개수만큼 for문 돌면서
            boolean isLie = true;
            for(int person : parties[i]) {
                if(find(person) == find(TrueArray[1])) { //1로하든 다른 값이든 상관 없다. 이미 union을 통해 TrueArray가 하나로 묶였기 때문
                    isLie = false; //대표 노드 값이 같으면 진실을 아는 사람이 엮여 있다는 거니까 거짓말을 못함 -> false
                    break;
                }
            }
            if(isLie) cnt++; //대표노드값이 같지 않다는 건 거짓말을 할 수 있다는 거니까 cnt++
        }
        System.out.println(cnt);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a!=b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a==parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}
