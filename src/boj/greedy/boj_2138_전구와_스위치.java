// v1 : greedy
// TS 틀렸습니다 : 예외 답 케이스 처리안함, 구현 실수

/*
[해석]
N개의 스위치, 전구
i번째 스위치를 누르면 i-1, i, i+1 세가지가 바뀜
원하는 상태를 만들기 위해 최소 스위치를 몇번 눌러야 하는가?

2초, 128MB
2 <= N <= 10^5
불가능하면 -1 출력

[구상]
어떻게 알지?? 완탐?

000
010

??

앞에서부터 스위치를 누를지 말지 결정. 직전까지만 영향을 줄 수 있기 때문에
i번 스위치를 누를 때 i-1번 전구가 원하는 상태가 되도록 결정

1번 스위치를 누를지 / 말지는 두가지 경우 모두 해봐야 함!
원하는 결과와 동일하면서, 횟수가 더 작은 쪽이 답이다

Q. 근데 정말 이게 최적인가? 더 최소 횟수가 없는 건가?

 */


package boj.greedy;

import java.io.*;
import java.util.*;

public class boj_2138_전구와_스위치 {
    private static int N;
    private static int[] bulb;
    private static int[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] bulbInit = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        target = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        // 첫번째 누름
        bulb = bulbInit.clone();
        bulb[0] = turn(bulb[0]);
        bulb[1] = turn(bulb[1]);
        int cnt1 = f(1);

        // 첫번째 안누름
        bulb = bulbInit.clone();
        int cnt2 = f(0);

        // 출력
        int ans;
        if(cnt1 == Integer.MAX_VALUE && cnt2 == Integer.MAX_VALUE){
            ans = -1;
        } else{
            ans = Math.min(cnt1, cnt2);
        }
        System.out.println(ans);
    }

    private static int f(int cnt) {
        // i = 1~N-1 스위치 돌기
        for(int i=1; i<N; i++){
            if(bulb[i-1] != target[i-1]){
                cnt++;
                bulb[i-1] = turn(bulb[i-1]);
                bulb[i] = turn(bulb[i]);

                if(i == N-1){ break; }
                bulb[i+1] = turn(bulb[i+1]);
            }
        }

        // 마지막값이 목표와 다르면 MAX_VALUE 값, 아니면 cnt 반환
//        System.out.println
        if(bulb[N-1] != target[N-1]) {
            return Integer.MAX_VALUE;
        }
        return cnt;
    }

    private static int turn(int x){
        return x==1 ? 0 : 1;
    }
}
