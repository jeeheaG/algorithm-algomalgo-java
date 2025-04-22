// TS : i와 cur 인덱스 사용 헷갈림

/**
[해석]
네트워크 개수세기


[구상]
노드 돌면서 이어진거 방문 BFS 큐
방문한 거 visited체크
이어진 거 더 없어서 다른 노드로 넘어갈 때 네트워크 번호 +1

-> O(v)



// 의사코드
모든 노드 돌기

for (모든 노드)
다음노드 방문전이면 넣음

    while(que) 
    꺼냄
    처리함
    주변노드 넣음

que empty
net++


**/

import java.util.*;


class Solution {
    public int solution(int n, int[][] computers) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int net = 0;
        
        // 모든 노드 돌기
        for(int i=0; i<n; i++){
            if(visited[i]){
                continue;
            }
            que.add(i);
                        
            while(que.size() != 0){
                int cur = que.poll();
                
                visited[cur] = true;
                
                for(int j=0; j<n; j++){
                    if(computers[cur][j] == 1 && cur!=j && visited[j] == false){
                        que.add(j);
                    }
                }
            }
            
            net++;
        }
        return net;
    }
}
