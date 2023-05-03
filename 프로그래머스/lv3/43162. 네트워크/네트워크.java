import java.util.*;

class Solution {
    boolean[] isVisit;
    
    void bfs(int[][] board, int start){
       	Queue<Integer> que = new ArrayDeque<>(); 
        que.add(start);
        
        while(!que.isEmpty()){
            int now = que.poll();
            for(int i=0;i<board.length;i++){
                if(board[now][i] == 1 && now != i && !isVisit[i]){
                    que.add(i);
                    isVisit[i] = true;
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        isVisit = new boolean[n];
        
        int answer = 0;
        for(int i=0;i<n;i++){
           if(!isVisit[i]){
               bfs(computers, i);
               answer++;
           } 
        }
        
        return answer;
    }
}