package boj.dp;


/*
- v1 : dp
- TS : int 범위, index 주의 -> 항상 테스트 시 조건 최대최소값 넣어볼 것!!!

[문제해석]
- f(n) = f(n-1) + f(n-3) 인 수열의 N번째 값 구하기
- f(1) = f(2) = f(3) = 1
- 1 <= N <= 116

1, 1, 1, 2, 3, 4, 6, 9, 13, 19, ...

점화식을 대놓고 줬으니 dp 를 생각했어야..

크기 n 인 배열 dp 테이블 써서 계산

** 재귀 dp 로도 해보자

 */

import java.io.*;

public class boj_14495_피보나치_비스무리한_수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 예외 경우 처리
        if (N < 4) {
            System.out.println(1);
            return;
        }

        long[] dp = new long[N+1]; // 0 1 2 .. N

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for (int i=4; i<N+1; i++) { // 4~N
            dp[i] = dp[i-1] + dp[i-3];
        }

        System.out.println(dp[N]);
    }
}
