// TS : Integer <-> String 변환 시 Integer 범위 넘어가는 경우 고려하기

/**
수들로 만들 수 있는 가장 큰 수 구하기
0 존재, 여러자리수

숫자를 문자열로 바꿔
정렬 시 앞뒤의 숫자를 순서를 바꾸어 붙여본 숫자 크기가 더 큰 쪽으로 정렬 (Comparator)

Comparator : 양수면 자리 스위치해서 정렬

**/

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] numStrs = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            numStrs[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(numStrs, (o1,o2) -> {
            int sum1 = Integer.parseInt(o1+o2);
            int sum2 = Integer.parseInt(o2+o1);
            return sum2 - sum1;
        });
        
        StringBuilder sb = new StringBuilder();
        for(String str : numStrs){
            sb.append(str);
        }
        
        // 모두 
        String res = sb.toString();
        if(res.charAt(0)=='0' && Integer.parseInt(res) == 0){
            return "0";
        }
        
        return res;
    }
}
