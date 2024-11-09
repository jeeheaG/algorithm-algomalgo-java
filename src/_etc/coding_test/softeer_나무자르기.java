package _etc.coding_test;

import java.io.*;
import java.util.*;

/*
주어진대로 쏜 후 남은 파괴범의 수

행 별 파괴범 수 세고 행에 날아갈 화살 개수만큼 차감

[테케1]
6 8
0 0 1 0 0 0 1 0
0 0 0 1 0 0 0 0
0 0 1 0 0 1 1 0
0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0
1 5
2 6

[답1]
2

*/
public class softeer_나무자르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] badCnt = new int[n];

        // 행 별 입력받아 1개수 셈
        for(int i=0; i<n; i++) {
            int cnt = 0;
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<m; j++) {
                if(st.nextToken().equals("1")){
                    cnt++;
                }
            }
            badCnt[i] = cnt;
        }


        // 화살 2개 입력
        int[][] arrow = new int[2][2];
        for(int i=0; i<2; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++){
                arrow[i][j] = Integer.parseInt(st.nextToken()) - 1; // 인덱스로 바로 쓰기 위해 -1해줌
            }
        }

        // 파괴범 지우기
        for(int[] a : arrow){
            for(int i=a[0]; i<=a[1]; i++){
                if (badCnt[i] != 0) {
                    badCnt[i] -= 1;
                }
            }
        }

        // 남은 파괴범 카운트
        int total = 0;
        for(int i=0; i<badCnt.length; i++){
            total += badCnt[i];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(total));
        bw.flush();
        bw.close();

    }
}
