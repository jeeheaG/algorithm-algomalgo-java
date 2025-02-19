// v1 : greedy
// 서브테스크 : 52점 (두 개 틀렸습니다)

/*
[해석]
마을마다 보내는 박스가 있음
배송은 마을번호가 커지는 단방향으로만 진행함
마을에 도착할 때마다 박스를 원하는 만큰 싣거나 내릴 수 있음
트럭 용량 내에서 배송할 수 있는 최대 박스 수는?

2 <= N마을 <= 2000
1 <= M박스정보 <= 10^4

[구상]
1->N 방향 배송이라 경로가 짧은 박스를 많이 배송하는 게 이득..인가

그냥 가까운 순으로 최대한 담으면 되나? -> x
지금 위치에서 가까운 거 말고 거리가 짧은 걸 담을 수 있게 해야할 듯 -> 이걸로

버스 경로마다 박스 개수 셈. 거리가 짧은 것부터 경로마다 트럭용량 넘지 않는 만큼 실음

(솔루션은 받는 마을 번호 오름차순이라고 함. 보내는 마을은 상관x)

[서브테스크]
4,5 : N ≤ 100, M ≤ 1,000, C ≤ 2,000
틀렸습니다

 */

package boj.greedy;

import java.io.*;
import java.util.*;

public class boj_8980_ing_택배 {
    static class Deliver{
        int from, to, box, len;
        public Deliver(int from, int to, int box, int len){
            this.from = from;
            this.to = to;
            this.box = box;
            this.len = len;
        }
    }
    static Deliver[] delivers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NC = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        delivers = new Deliver[M];

        for(int i=0; i<M; i++){
            int[] d = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            delivers[i] = new Deliver(d[0], d[1], d[2], d[1]-d[0]);
        }

        // 경로 짧은 기준으로 정렬
        Arrays.sort(delivers, Comparator.comparing(c -> c.len));

        // 짧은 경로부터 돌면서 채울 수 있는 만큼 채움
        int totalBox = 0;
        int[] path = new int[NC[0]]; // 경로 1~N-1 별 여유공간
        Arrays.fill(path, NC[1]);
        for(Deliver deliver : delivers){
            // 박스가 실어질 경로들에 남은 여유공간 최소값
            int minBox = Integer.MAX_VALUE;
            for(int i=deliver.from; i<deliver.to; i++){
                if(path[i] < minBox){
                    minBox = path[i];
                }
            }

            if(minBox==0){ continue; }

            // path 에 반영
            int curBox = Math.min(minBox, deliver.box);
            for(int i=deliver.from; i<deliver.to; i++){
                path[i] -= curBox;
            }
            totalBox += curBox;
        }

        System.out.println(totalBox);
    }
}
