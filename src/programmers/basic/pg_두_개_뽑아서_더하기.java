package programmers.basic;

/**
 1. 가능한 경우(서로다른 인덱스)를 다 계산한다
 for 문 두개?
 100^2 = 10^4


 2. set에 담는다
 Set<Integer> set = new HashSet();


 **/

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        Set<Integer> set = new HashSet();

        for (int i=0; i<numbers.length; i++) {
            for (int j=i+1; j<numbers.length; j++) {
                set.add(numbers[i]+numbers[j]);
            }
        }

        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}


public class pg_두_개_뽑아서_더하기 {
}

