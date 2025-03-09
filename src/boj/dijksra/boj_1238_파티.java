// v1 : 최단경로 - 다익스트라 (216ms)
// TS : 다익스트라 방문체크 위치 조심

/*
[해석]
단방향 그래프에서
특정 노드X로 왕복하는 최단경로가 가장 긴 노드 구하기

1 <= N <= 1000
1 <= M 1<= 10^4

[구상]
X에서 모든 노드로의 최단 경로 구함 -> 다익스트라
그리고 모든 경로의 출발-도착점을 서로 바꿔서 반복하면 복귀하는 경로가 됨
다시 X에서 모든 노드로의 최단경로 구함 -> 다익스트라
두 경로 합이 제일 긴 노드 구함

**다익스트라
- 우선순위큐에 현재 노드의 인접노드 넣음
- 하나씩 꺼내며 꺼낸 노드의 현재 최단경로보다 현재 노드를 거쳐가는 경로가 더 최단이면 갱신하고 탐색함
- 반복

경로를 뒤집어서 복귀경로를 찾는 게 핵심이었던 문제
 */

package boj.dijksra;

import java.util.*;
import java.io.*;

public class boj_1238_파티 {
    static int MAX = 987654321;
    static int[] NMX;
    static int N;
    // 경로와 우선순위큐에 사용할 자료형 - Comparable.compareTo(T) 구현
    static class Node implements Comparable<Node>{
        int end, cost;
        Node(int end, int cost){
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        NMX = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NMX[0];

        ArrayList<Node>[] edges1 = new ArrayList[N+1];
        ArrayList<Node>[] edges2 = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            edges1[i] = new ArrayList<>();
            edges2[i] = new ArrayList<>();
        }

        // 간선 입력
        for(int i=0; i<NMX[1]; i++){
            int[] nnc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges1[nnc[0]].add(new Node(nnc[1], nnc[2]));
            edges2[nnc[1]].add(new Node(nnc[0], nnc[2]));
        }

        // 각각 X 로부터 다익스트라
        int[] min1 = dijkstra(edges1);
        int[] min2 = dijkstra(edges2);

        // 가는길+오는길 거리 합 최대값 구함
        int maxSum = 0;
        for(int i=1; i<N+1; i++){
            int sum = min1[i] + min2[i];
            if(maxSum < sum){
                maxSum = sum;
            }
        }

        System.out.println(maxSum);
    }

    private static int[] dijkstra(ArrayList<Node>[] edges){
        int[] min = new int[N+1];
        Arrays.fill(min, MAX);
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(NMX[2], 0));
        min[NMX[2]] = 0;

        // 큐 빌 때까지
        while(que.size() != 0){
            Node cur = que.poll();
            int curN = cur.end;

            // TS : 방문체크 꼭 여기서 해야함!!!!
            if(visited[curN]){
                continue;
            }

            // 방문 - 인접노드 최소거리 갱신
            visited[curN] = true;
            for(Node nxt : edges[curN]){
                int nxtN = nxt.end;

                // 현재노드를 거치는 거리가 현재의 최소거리보다 작으면 갱신
                int nCost = min[curN] + nxt.cost;
                if(nCost < min[nxtN]){
                    min[nxtN] = nCost;
                    que.add(new Node(nxtN, nCost));
                }

            }
        }

        return min;
    }
}
