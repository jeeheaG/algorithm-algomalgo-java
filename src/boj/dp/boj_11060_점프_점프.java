package boj.dp;

// v1 : dp
// TS : dp 값이 한번도 갱신되지 않았던 값일 경우의 예외처리 잊음

/*
[문제 해석]
각 칸의 번호 이하만큼 다음 칸으로 이동 가능
마지막칸까지 갈 수 있는 최소 횟수는?

[구상]
각 칸을 처음부터 돌면서, 해당 칸에서 이동할 수 있는 모든 칸에 대해 도착칸에 도착할 수 있는 최소 횟수를 기록
-> dp

0 1 2 3 4 5 6 7 8 9
1 2 0 1 3 2 1 5 4 2


 */

import java.io.*;
import java.util.*;

public class boj_11060_점프_점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] board = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N]; // 초기값 0
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i=0; i<board.length; i++) {
            if (dp[i]==Integer.MAX_VALUE) { continue; } // TS

            int cnt = dp[i]+1;
            for (int j=i+1; j<i+1+board[i]; j++) {
                if (j>=dp.length) {
                    break;
                }
                dp[j] = Math.min(cnt, dp[j]);
            }
        }

        int ans = (dp[N-1] == Integer.MAX_VALUE) ? -1 : dp[N-1];
        System.out.println(ans);
    }
}
