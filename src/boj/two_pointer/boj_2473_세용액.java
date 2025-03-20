// v1 : 투포인터
// TS : int 간 연산 시 결과가 int 범위 넘으면 정상적인 결과가 나오지않음. 연산 대상도 long 을 써야함!!


/*
[해석]
용액 3개 합쳐서 0에 근접한 농도 만들기
가장 근접한 세 용액 찾기

용액 수
3 <= N <= 5000
농도값
-10^9 <= 농도 <= 10^9

[구상]
3포인터???
다 해봐야 되지 않나

방법 고민
1. 포인터 3개 - 1개를 고정시켜놓고 두개 투포인터 탐색 -> O(N+N) = O(N)
2. 각 용액을 선택한다/안한다 BFS, 3개를 이미 선택했으면 백트래킹 -> O(N^2)
 => 1번으로 시도

** int 범위는 +- 2 * 10^9 를 조금 넘는다
-> 세 용액의 합이 최대 3*10^9 이므로 int 가 아닌 long 사용

1. 하나 고정
2. 투 포인터로 두개 +고정 더함
3. 합의 절댓값의 최소값 갱신, 갱신이면 현재 용액 세가지 저장
4. 합의 부호가 양수이면 r포인터, 음수이면 l포인터 안쪽으로 이동
   이동 시 위치가 고정이랑 겹치면 한 칸 더 이동

-97 -6 -2 98


 */

package boj.two_pointer;

import java.io.*;
import java.util.*;

public class boj_2473_세용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] liquid = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Arrays.sort(liquid);

        long minAbs = Long.MAX_VALUE;
        long[] minLiq = {0, 0, 0};
        // 한개 고정하고 투포인터
        for(int i=0; i<N; i++){

            int l = 0;
            int r = N-1;
            long liquidFix = liquid[i];
            while(l<r){
                long curSum = liquid[l] + liquid[r] + liquidFix;
                long curAbs = Math.abs(curSum);

                // 갱신
                if(l!=i && r!=i && curAbs < minAbs){
                    minAbs = curAbs;
                    minLiq[0] = liquid[l];
                    minLiq[1] = liquid[r];
                    minLiq[2] = liquidFix;

                    if(curSum == 0){
                        break;
                    }
                }

                // 포인터 이동
                if(curSum < 0){
                    l++;
                }
                else{
                    r--;
                }
            }

            // 0인 경우 찾았으면 탐색 종료
            if(minAbs == 0){
                break;
            }
        }

        Arrays.sort(minLiq);
        System.out.print(Arrays.toString(minLiq)
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));


    }
}
