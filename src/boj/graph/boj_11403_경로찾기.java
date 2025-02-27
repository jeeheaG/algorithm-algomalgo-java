// v1 : 플루이드워셜

/*
[해석]
가중치 없는 방향 그래프에서 모든 정점에서 다른 모든 정점으로 경로가 있는지없는지 출력


1초
1 <= N <= 100


[구상]
모든경로->모든경로 를 구하는 플루이드워셜 문제
모든 노드를 중간노드로 설정해보고 최적경로를 구한다
O(N^3)


 */



package boj.graph;

import java.util.*;
import java.io.*;

public class boj_11403_경로찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] edges = new int[N+1][N+1];

        for(int i=1; i<N+1; i++){
            int[] nedge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<N; j++){
                if(nedge[j] == 1){
                    edges[i][j+1] = 1;
                }
            }
        }

        // 플루이드 워셜 활용, 대신 최적거리 말고 갈 수 있는 경로가 있는지만 저장
        for(int m=1; m<N+1; m++){
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    if(edges[i][m]==1 && edges[m][j]==1){
                        edges[i][j] = 1;
                    }
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<N+1; i++){
            sb.append(Arrays.toString(Arrays.copyOfRange(edges[i], 1,N+1)).replace("[", "").replace("]", "").replace(",", ""));
            sb.append("\n");
        }
        System.out.print(sb);

    }
}
