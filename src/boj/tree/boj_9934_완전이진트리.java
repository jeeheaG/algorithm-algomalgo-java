// v1 : graph

/*
[문제]
완전 이진트리 - 모두 자식 2개 (리프는 0개)
탐색 순서 : 왼중오

트리 깊이 K와 방문 번호가 선형으로 주어졌을 때,
완전 이진 트리를 복원하여 각 레벨의 노드를 왼쪽부터 출력하기
1 <= K <= 10

[구상]
3
1 6 4 3 5 2 7

K=1 : 3,
K=2 : 6, 2
K=3 : 1,4, 5,7

완전이진트리니까 항상 노드가 홀수개.

4
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
0 1 2 3 4 5 6 7 8  9 10 11 12 13 14


K=1 : 8 -> 2^K /2
K=2 : 4, 12 -> 2^K /4,
K=3 : 2, 6, 10, 14
K=4 : 1,3, 5,7, 9,11, 13,15

입력받고
중앙에서 시작
항상 현재 위치의 양쪽의 중간지점을 재귀 탐색

항상 자기자신 기준으로 오른쪽/왼쪽 영역의 가운데 인덱스에 대해 recursive

 */


package boj.tree;

import java.io.*;
import java.util.*;

public class boj_9934_완전이진트리 {
    private static ArrayList<Integer>[] ans;
    private static int K;
    private static int[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ans = new ArrayList[K+1];
        for (int i=0; i<K+1; i++) { ans[i] = new ArrayList<>(); }

        // 루트노드 위치
        int n = (int) Math.pow(2,K)/2 - 1;

        // 재귀 탐색 시작
        recursive(n, 1);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<K+1; i++) {
            String line = ans[i].toString().replace(",", "").replace("[", "").replace("]", "");
            sb.append(line).append("\n");
        }
        System.out.print(sb);
    }

    private static void recursive(int cur, int curDepth) {
        ans[curDepth].add(nodes[cur]);

        if (curDepth == K) {
            return;
        }

        int dist = (int) Math.pow(2, K-curDepth-1);
        recursive(cur-dist, curDepth+1);
        recursive(cur+dist, curDepth+1);
    }

}
