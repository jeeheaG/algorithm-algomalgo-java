// v1 : greedy
// TLE(시간초과) 87% : 답이 없는 예외 케이스 처리 안해줬음

/*
[해석]
3/1 ~ 11/30 항상 꽃 한 개 이상 피어있게 함
심을 꽃 최소 개수는?

불가능하면 0출력
1 <= N <= 10^5

꽃은 같은 해에 피고 짐

30일까지 : 4,6,9,11
31일까지 : 1,3,5,7,8,10
28일까지 : 2


[구상]
최적은 모르겠지만 가능한 경우를 생각해보자,,
3/1 에 피어있는 꽃 중 가장 늦게 지는 꽃 선택. 해당 꽃이 지는 날짜 기준으로 반복 선택 -> 이걸로
아니면 가장 오래 피어있는 순으로 정렬해서 선택.. -> x

- 정렬해두고 쓰면 계산을 줄일 수 있나? -> 아니 빨리 피어있어도 빨리 지면 소용이 없음

 */


package boj.greedy;

import java.io.*;
import java.util.*;

public class boj_2457_fail_공주님의_정원 {

    static class Flower{
        int sMon, sDay, eMon, eDay;
        public Flower(int sMon, int sDay, int eMon, int eDay){
            this.sDay = sDay;
            this.sMon = sMon;
            this.eDay = eDay;
            this.eMon = eMon;
        }
    }
    static Flower[] flowers;

    static int[] cur = new int[] {3, 1};
    static int[] end = new int[] {11, 30}; // 11/30에도 피어있어야 함

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        flowers = new Flower[N];

        for(int i=0; i<N; i++){
            int[] f = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 하루 안에 피고 지는 꽃 사용안함
            if(f[0] == f[2] && f[1] == f[3]) { continue; }
            flowers[i] = new Flower(f[0], f[1], f[2], f[3]);
        }

        int cnt = 0;
        int[] maxEnd = cur.clone();
        int[] prevMaxEnd = cur.clone();
        boolean answerExist = true;
        while(true){
            for(Flower flower : flowers){
                // 피어있는 꽃 중 가장 늦게 지는 날짜 갱신
                if(exist(flower) && checkDate(maxEnd[0], maxEnd[1], flower.eMon, flower.eDay)){
                    maxEnd[0] = flower.eMon;
                    maxEnd[1] = flower.eDay;
                }
            }

            // 해당하는 꽃 없음
            if(maxEnd == cur){
                cnt = 0;
                break;
            }

            // 선택
            cnt++;

            // 종료날짜까지 끝났음
            if(checkDate(end[0], end[1], maxEnd[0], maxEnd[1])){
                break;
            }
            // 가능한 꽃이 없음. 답이 존재하지 않음
            if(maxEnd[0] == prevMaxEnd[0] && maxEnd[1] == prevMaxEnd[1]){
                answerExist = false;
                break;
            }

            cur[0] = maxEnd[0];
            cur[1] = maxEnd[1];
            prevMaxEnd = maxEnd.clone();
        }

        if(!answerExist){
            System.out.print(0);
        }else{
            System.out.print(cnt);
        }
    }

    // 현재 날짜에 피어있는지
    private static boolean exist(Flower flower){
        return ( (flower.sMon == cur[0] && flower.sDay == cur[1]) || checkDate(flower.sMon, flower.sDay, cur[0], cur[1]))
                && checkDate(cur[0], cur[1], flower.eMon, flower.eDay);
    }

    private static boolean checkDate(int fromMon, int fromDay, int toMon, int toDay){
        if(fromMon < toMon){
            return true;
        }else return fromMon == toMon && fromDay < toDay;
    }
}
