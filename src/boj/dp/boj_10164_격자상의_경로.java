// v1 : dp

/*
[해석]
k번 칸을 반드시 거쳐서 목적지(N*M)까지 도달할 수 있는 경로 개수
k번 칸은 없을 수도 있음. 없거나 한개뿐임
오른족, 아래쪽으로만 이동 가능

1 <= N,M <= 15
K = 0 or 1 < K < N*M

[구상]
각 포인트까지 가는 경로 수를 구할 때 dp
    - 각 칸으로 갈 수 있는 경우의 수
        dp[i][j] = dp[i-1][j] + dp[i][j-1]
각 포인트까지 가는 경로 수 다 곱함

(i,j) 칸 번호 n
n = (i-1)*M + j+1

**근데 dp 배열을 N*M 크기로 안쓰고 함수 안에다 선언해서 필요한만큼만 사용해도 될 듯?

*/

package boj.dp;

import java.io.*;
import java.util.*;

public class boj_10164_격자상의_경로 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NMK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NMK[0];
        int M = NMK[1];
        int K = NMK[2];

        dp = new int[N][M];

        if(K==0){
            System.out.println(f(0,0, N-1, M-1));
            return;
        }

        // 점 k의 좌표를 담는 x,y
        int[] xy = new int[2];
        xy[0] = (K-1) / M;
        xy[1] = (K-1) % M;

        int res1 = f(0, 0, xy[0], xy[1]);
        int res2 = f(xy[0], xy[1], N-1, M-1);

//        System.out.println(res1);
//        System.out.println(res2);
        System.out.println(res1*res2);

    }

    private static int f(int x1, int y1, int x2, int y2){
        for(int i=x1; i<x2+1; i++){
            for(int j=y1; j<y2+1; j++){
                if(i==x1 || j==y1) {
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[x2][y2];
    }

}
