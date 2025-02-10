package boj.graph;
// f1 : 메모리 초과 (간선을 보드에 입력받음). 테스트 케이스만 통과


/*
[해석]
트리에서 각 노드와 엣지가 단절점과 단절선인지 판단하라

[구상]
그래프에서 단절점, 단절선을 찾는 법
-> dfs spanning tree 에서 dfs 방문번호가 더 작은 노드를 통해 해당 노드에 갈 수 있다면, 다른 경로가 있는 것이므로 해당 노드의 직전 노드는 단절점이 아님
    돌아나올 때 자신의 최소 방문 번호를 다음 노드에 전파해서 단절점 여부를 판단함
    단절선도 유사하게 다른 경로가 있는지로 판단함

but
그래프가 모두 트리인 경우?
트리는 사이클이 없으므로 dfs 시 다른 경로가 존재하지 않음
-> 리프노드가 아니면 모두 단절점임. 루트노드의 경우 자식이 2개 이상이면 단절점임
    따라서 연결된 간선이 2개 이상인 노드는 모두 단절점임
    사이클이 없으므로 모든 엣지가 단절선임

시간 : 1초
메모리 : 512MB
2 ≤ N ≤ 100,000

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_14675_fail_단절점과_단절선 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[][] edges = new boolean[N+1][N+1]; // false 로 초기화

        // 간선 입력받기
        for (int i=0; i<N-1; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges[edge[0]][edge[1]] = true;
            edges[edge[1]][edge[0]] = true;
        }

        // 질문 입력받고 답
        StringBuilder sb = new StringBuilder();
        int qCnt = Integer.parseInt(br.readLine());
        for (int i=0; i<qCnt; i++) {
            int[] q = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 단절선
            if (q[0] == 2) {
                sb.append("yes\n");
            }
            // 단절점
            else {
                int edgeCnt = 0;
                for (boolean isEdge : edges[q[1]]) {
                    if (isEdge) edgeCnt++;
                }

                if (1 < edgeCnt) {
                    sb.append("yes\n");
                }
                else { sb.append("no\n"); }
            }
        }

        System.out.print(sb);


    }
}
