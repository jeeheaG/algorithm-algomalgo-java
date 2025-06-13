/**
진영에 최대한 빠르게 도착
도착할 수 없을 수도 있음
최단거리 구하기. 못가면 -1 반환

bfs 큐로 구현

**/

import java.util.*;

class Solution {
    class Item{
        int x;
        int y;
        int sum;
        
        Item(int x, int y, int sum){
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }
    
    public int solution(int[][] maps) {
        Queue<Item> que = new LinkedList<Item>();
        que.add(new Item(0,0,1));
        
        int mapX = maps.length-1;
        int mapY = maps[0].length-1;
        
        int[][] dirArr = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        boolean[][] visited = new boolean[mapX+1][mapY+1];
        visited[0][0] = true;
        
        while(que.size()!=0){
            Item cur = que.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curSum = cur.sum;
            
            // bfs - 가장 처음 만난 도착지점이 최단거리 
            if(curX==mapX && curY==mapY){
                return curSum;
            }
            
            for(int[] dir : dirArr){
                int nxtX = curX + dir[0];
                int nxtY = curY + dir[1];
                
                if(0<=nxtX && nxtX<=mapX && 0<=nxtY && nxtY<=mapY
                   && maps[nxtX][nxtY]==1 && visited[nxtX][nxtY]==false){
                    que.add(new Item(nxtX, nxtY, curSum+1));
                    visited[nxtX][nxtY] = true;
                }
            }
            
        }
        return -1;
    }
}
