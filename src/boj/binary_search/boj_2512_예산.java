// v1 :
// Q. 이진탐색과 이분탐색이 정확한 값을 찾는지 가까운 값을 찾는지로 나뉜다고 전에 스터디에서 들었는데
//      둘중 어느건지 기억이 안나고 다른 자료도 못찾겠다,, 질문하기

/*
[해석]
예산을 배정
모두 요구대로 배정 가능하면 그대로 배정
불가능하면 상한액을 정해 상한을 넘는 곳은 상한액만 배정
전체 예산 배정액을 가능한 최재로 하는 상한액 찾기

3 <= N <= 10^4

[구상]
가능한 최대값(상한선)을 찾는 이진참색
1. 모두 배정 가능하면 그대로 배정
2. 불가능할 경우 상한선을 이진탐색



 */


package boj.binary_search;

import java.io.*;
import java.util.*;

public class boj_2512_예산 {
    static int[] budgets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        budgets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int total = Integer.parseInt(br.readLine());

        Arrays.sort(budgets);
        int maxBudget = budgets[budgets.length-1];

        // 모두 배정 가능
        if(Arrays.stream(budgets).sum() <= total){
            System.out.println(maxBudget); // 최대값 반환
            return;
        }

        int l = 0;
        int r = maxBudget;
        int ans = 0;

        while (l <= r) {
            int mid = (l+r) / 2;
            int curTotal = getTotal(mid);

            // 현재 상한선 적용 결과에 따라 이진탐색
            if(curTotal < total) {
                if (ans < mid) {
                    ans = mid;
                    l = mid + 1;
                }
            }
            else if(curTotal == total){
                ans = mid;
                break;
            }
            else {
                r = mid - 1;
            }
        }

        System.out.println(ans);
    }

    // 해당 상한선을 적용할 경우 전체 사용 예산
    private static int getTotal(int limit){
        int s = 0;
        for(int b : budgets){
            s += Math.min(limit, b);
        }
        return s;
    }
}
