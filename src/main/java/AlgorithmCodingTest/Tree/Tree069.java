package AlgorithmCodingTest.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        tNode root = new tNode(); //루트 노드 설정

        //트라이 자료구조 구축
        while (n > 0) {
            String line = br.readLine(); //startlink
            tNode now = root;
            char[] charArray = line.toCharArray(); //[s, t, a, r, t, l, i, n, k]
            for (int i = 0; i < charArray.length; i++) {
                if (now.next[charArray[i] - 'a'] == null) { //현재 노드의 자식노드가 존재 x
                    now.next[charArray[i] - 'a'] = new tNode(); //연결
                }
                now = now.next[charArray[i] - 'a'];
                if (i == charArray.length - 1) //현재 삽입 중의 문자열의 마지막 문자 도달 -> 해당 노드가 문자열 끝임을 표시
                    now.End = true;
            }
            n--;
        }

        //트라이 자료구조 검색
        int cnt = 0;
        while (m > 0) {
            String line = br.readLine(); //startlink
            tNode now = root;
            char[] charArray = line.toCharArray(); //[s, t, a, r, t, l, i, n, k]
            for (int i = 0; i < charArray.length; i++) {
                if (now.next[charArray[i] - 'a'] == null) {
                    break;
                }
                now = now.next[charArray[i] - 'a'];
                if (i == line.length() - 1 && now.End) {
                    cnt++;
                }
            }
            m--;
        }
        System.out.println(cnt);
    }
}

class tNode {
    tNode[] next = new tNode[26];
    boolean End;
}

