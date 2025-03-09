// v1 : 해시 (100ms)

/*
[해석]
주어진 옷을 분류당 하나씩만 사용하여 만들 수 있는 모든 코드 개수는?

[구상]
분류당 옷 개수 카운트
분류당 옷 개수 + 1(해당 분류를 안입는 경우) 한 값을 모두 곱하고
모든 분류를 안입는 경우 1개를 제외하면 답

 */

package boj.hash;

import java.util.*;
import java.io.*;

public class boj_9375_패션왕_신해빈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> cnt = new HashMap<>();

            // 분류별 개수세기
            for(int n=0; n<N; n++){
                String[] com = br.readLine().split(" ");
                String key = com[1];

                if(cnt.containsKey(key)){
                    cnt.put(key, cnt.get(key)+1);
                }else{
                    cnt.put(key, 1);
                }
            }

            // 옷 개수 순회
            Iterator<String> it = cnt.keySet().iterator();
            int ans = 1;
            while(it.hasNext()){
                ans *= cnt.get(it.next())+1;
            }

            bw.write(String.valueOf(ans-1));
            bw.newLine();
        }

        //출력
        bw.flush();
        bw.close();
    }
}
