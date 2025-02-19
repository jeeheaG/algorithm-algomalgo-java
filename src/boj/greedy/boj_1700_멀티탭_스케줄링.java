// v1 :

// subList(), indexOf()
/*
[해석]
기기 사용 순서가 주어질 때,
플러그 빼는 횟수 최소값

2초

[구상]
무슨 기준으로 뽑아야 최적이지?
꽂혀있는 것 중 가장 나중에 쓰이는 것? -> 이걸로
남은 횟수?? -> x

 */

package boj.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class boj_1700_멀티탭_스케줄링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> order = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());

        Set<Integer> multi = new HashSet<>();
        int len = order.size();

        int cnt = 0;
        for(int i=0; i<len; i++){
            int cur = order.get(i);

            // 존재함
            if(multi.contains(cur)) { continue; }
            // 남은 자리에 꽂음
            if(multi.size() < NK[0]) {
                multi.add(cur);
                continue;
            }

            // 뽑아야 함
            // 가장 나중에 등장하는 값 찾기
            int maxIdx = -1;
            int target = -1;
            for(Integer item : multi){
                List<Integer> remain = order.subList(i, len);
                // 다시 안나오는 게 있다면
                if(!remain.contains(item)) {
                    target = item;
                    break;
                }

                int first = remain.indexOf(item);
                if(maxIdx < first) {
                    maxIdx = first;
                    target = item;
                }
            }
            multi.remove(target);
            multi.add(cur);
            cnt++;
        }

        System.out.print(cnt);
    }
}
