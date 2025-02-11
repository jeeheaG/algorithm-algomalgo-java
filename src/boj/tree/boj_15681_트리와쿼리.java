// v1 : tree

/*
[해석]
트리 특정 지점을 루트로 하는 서브트리의 노드 개수를 구하기

2 <= N <= 10^5

[구상]
트리를 루트부터 dfs 재귀 탐색
돌아나갈 때 자식노드 수(자신 포함) 전파하기
 */

package boj.tree;

import java.io.*;
import java.util.*;

public class boj_15681_트리와쿼리 {
    private static int[] cnt;
    private static ArrayList<Integer>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nrq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = nrq[0];
        int R = nrq[1];

        cnt = new int[N+1];

        edges = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) { edges[i] = new ArrayList<>(); }

        // 간선 입력
        for(int i=0; i<N-1; i++) {
            int[] e = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges[e[0]].add(e[1]);
            edges[e[1]].add(e[0]);
        }

        // 루트부터 dfs 재귀
        cnt[R] += recursive(R, -1);

//        System.out.println("[cnt]");
//        for(int i : cnt) { System.out.print(i + " "); }

        // Q
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<nrq[2]; i++) {
            int node = Integer.parseInt(br.readLine());
            sb.append(cnt[node]).append("\n");
        }
        System.out.print(sb);
    }

    private static int recursive(int cur, int prev) {
        List<Integer> curEdge = edges[cur];

        // 자식노드 재귀 탐색
        int addCnt = 0;
        for(Integer e : curEdge) {
            if(e == prev) {
                continue;
            }

            // 자식노드 개수 누적
            addCnt += recursive(e, cur);
        }

        cnt[cur] = addCnt + 1; // 자기 자신과 자식노드들 모두 포함 개수
        return cnt[cur];
    }

}