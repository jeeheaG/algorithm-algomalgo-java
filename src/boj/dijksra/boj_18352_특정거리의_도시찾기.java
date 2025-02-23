// v1 : dijkstra (1448ms)
// Q1. 큐에 넣기 전에 방문체크 해야함?
// Q2. 우선순위큐 대신 그냥 큐 쓰면 안됨?
// MLE : 메모리 초과 : 간선을 booelan[][] 으로 입력받음

/*
[해석]
도시 1~N, 단방향 도로 M개
특정도시와의 최단거리가 정확히 K인 모든 도시 찾기

-> 정석 다익스트라
    : 특정 노드로브터 모든 노드로의 최단거리 구하기


[구상]
다익스트라
1. 방문 전인 노드 중 가장 가까운 거 방문 (우선순위 큐)
2. 최단거리 갱신

가중치가 동일한데 우선순위큐가 필요해? 먼저 들어갔으면 가장 가까운거 아닌가
-> 둘다 해봐

 */


package boj.dijksra;

import java.io.*;
import java.util.*;

public class boj_18352_특정거리의_도시찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NMKX = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NMKX[0];
        int K = NMKX[2];
        int X = NMKX[3];

        List<Integer>[] edges = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) { edges[i] = new ArrayList(); }
        for(int i=0; i<NMKX[1]; i++){
            String[] e = br.readLine().split(" ");
            edges[Integer.parseInt(e[0])].add(Integer.parseInt(e[1]));
        }

        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        //// 다익스트라로 X에서 최단거리 구함
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[0]-o2[0];
        });
        pq.add(new int[] {0, X});
        dist[X] = 0;

        // 방문 처리 반복문
        while(!pq.isEmpty()){
            // 제일 가까운 노드 방문
            int[] DN = pq.poll();
            int curDist = DN[0];
            int cur = DN[1];

            // 해당 노드의 인접노드 탐색
            for(Integer n : edges[cur]){
                // 현재 노드를 거쳐가는 경우가 더 가까우면 최단거리 갱신하고 큐에 넣음
                int newDist = curDist+1;
                if(newDist < dist[n]){
                    dist[n] = newDist;
                    pq.add(new int[] {newDist, n});
                }
            }
        }

        // dist 값 K인 노드 번호 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<N+1; i++){
            if(dist[i] == K){
                sb.append(i).append("\n");
            }
        }
        if(sb.length() == 0){
            sb.append(-1);
        }

        System.out.print(sb);
    }
}
