// v1 : 구현 (2022 카카오 인턴 기출)
// TS : 문자목록에서 특정 문자의 위치와 특정 위치의 문자가 필요했음. 
    // String 의 .indexOf(), .charAt() 으로 해결
    // java string char 어렵다 . . .

/**
지표 4개, 각 2유형
3 2 1 점수
질문 n 개
점수 같으면 사전순

지표별 점수
[0, 0, 0, 0]
각 RT, CF, JM, AN
앞유형이면 -, 뒷유형이면+ -> 0이면 앞유형

선택지 1~7 : 1 2 3 / 4 / 5 6 7
-4 하면 : -3 -2 -1 / 0 / 1 2 3
이 값을 점수로 더함

이 때 유형코드가 뒤집어져 있으면 (앞자리가 T, F, M, N 중 하나이면) 부호 바꿔서 더함

*/

import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String frontType = "RCJA";
        String backType = "TFMN";
        
        int[] score = {0,0,0,0};
        int n = survey.length;
        
        for (int i=0; i<n; i++) {
            int curChoice = choices[i] - 4; // -3~3 으로 바꿈
            char[] surveyChar = survey[i].toCharArray();
            
            // survey 유형 코드가 뒤집어진 순서인지 검사
            char curFrontType = surveyChar[0];
            if (surveyChar[1] < surveyChar[0]) {
                curFrontType = surveyChar[1];
                curChoice *= -1;
            }
            
            // 지표 인덱스 가져와서 점수 계산
            int idx = frontType.indexOf(curFrontType);
            score[idx] += curChoice;
        }
        
        // 점수를 유형으로 변경
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<4; i++) {
            if(score[i] <= 0) {
                sb.append(frontType.charAt(i));
            } else {
                sb.append(backType.charAt(i));
            }
        }
        
        return sb.toString();
    }
}
