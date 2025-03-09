// v1 : 최단경로 - 플루이드 워셜 (132ms)

/*
[해석]
케빈베이컨수 : 모든 사람과 케빈베이컨 게임을 했을 때 나오는 단계 합
이게 최소인 사람을 구하라
-> 연결그래프에서 다른 모든 노드로 갈 수 있는 경로의 합이 최소인 노드 구하기
2 <= N <= 100, 1<= M <= 5000

친구관계 M 은 중복입력이 있을 수 있음
모든 노드가 연결되어 있음

[구상]
모든노드에서 모든 노드로의 최단거리 구하고 합 가장 작은 거 반환?
-> 플루이드워셜 O(N^3)

 */


package boj.graph;

import java.util.*;
import java.io.*;

public class boj_1389_케빈베이컨의_6단계법칙 {
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NM[0];

        board = new int[N+1][N+1];
        for(int i=1; i<N+1; i++){ Arrays.fill(board[i], 1000); }

        for(int i=0; i<NM[1]; i++){
            int[] nn = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            board[nn[0]][nn[1]] = 1;
            board[nn[1]][nn[0]] = 1;
        }

        // 플루이드 워셜
        for(int e=1; e<N+1; e++){
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    int withE = board[i][e] + board[e][j];
                    if(withE < board[i][j]){
                        board[i][j] = withE;
                    }
                }
            }
        }

        // 케빈베이컨 수 : 합이 최소인 노드 구함
        int min = Integer.MAX_VALUE;
        int ans = -1;
        for(int i=1; i<N+1; i++){
            int cur = Arrays.stream(board[i]).sum();
            if(cur < min){
                min = cur;
                ans = i;
            }
        }

        System.out.print(ans);
    }
}
