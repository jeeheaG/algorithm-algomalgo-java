// v1 : dfs 재귀
// TS : NPE : 처음에 현재 노드가 리프노드일 경우에만 max 값인지 검사하도록 짰는데, 그 부분에서 NPE 가 발생
//              거기서 NPE 가 날 부분은 edge[cur].size() 밖에 없음. Index 에러가 아니고 Null pointer 면 cur == 0 밖에 없음. 그걸 고치니까 Index 에러로 바뀜.
//              어떤 상황에서 cur = 0 인거지???


/*
[해석]
트리의 지름 구하기 = 트리 내 경로 중 가장 긴 경로

1 <= N <= 10^4

[구상]
아무 노드로부터 출발해 어떤 리프노의 까지의 경로 중 가장 먼 경로를 dfs 로 찾음
해당 리프노드부터 다른 리프노드까지의 경로 중 가장 먼 노드를 dfs로 찾음

 */


package boj.tree;

import java.io.*;
import java.util.*;

public class boj_1967_트리의지름 {
    private static class Edge {
        int node;
        int weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    private static int maxNode;
    private static int maxWeight;
    private static ArrayList<Edge>[] edges;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 노드가 하나인 경우
        if(N==1){ System.out.println(0); return; }

        // 각 노드별 간선을 담을 array
        edges = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) { edges[i] = new ArrayList<Edge>(); }

        for(int i=0; i<N-1; i++){
            int[] nnw = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges[nnw[0]].add(new Edge(nnw[1], nnw[2]));
            edges[nnw[1]].add(new Edge(nnw[0], nnw[2]));
        }

        // 노드가 둘이라 모두 리프노드인 경우
        if(N==2){ System.out.println(edges[1].get(0).weight); return; }

        // 아무점 골라서 dfs 탐색
        visited = new boolean[N+1];
        dfs(1, 0);
        System.out.println("maxNode1 : "+maxNode);

        // 두번째 dfs
        maxWeight = 0;
        Arrays.fill(visited, false);
        dfs(maxNode, 0);

        System.out.println("maxNode2 : "+maxNode);
        System.out.println(maxWeight);
    }

    private static void dfs(int cur, int curWeight){
        visited[cur] = true;

        if(maxWeight < curWeight){
            maxWeight = curWeight;
            maxNode = cur;
        }

        for(Edge e : edges[cur]) {
            int nxt = e.node;
            if(visited[nxt]) { continue; }

            dfs(nxt, e.weight+curWeight);
        }
    }
}
