import java.util.*;

class Solution {
    int max = -1;
    
    public int solution(int k, int[][] dungeons) {
        boolean[] choosen = new boolean[dungeons.length];
        dfs(k, 0, dungeons, choosen, 0);
        
        return max;
    }
    
    void dfs(int now, int answer,int[][] dungeons, boolean[] choosen, int depth){
        if(depth == dungeons.length){
            //System.out.println(answer);
            max = Math.max(max, answer);
            return;
        }
        
        for(int i=0;i<dungeons.length;i++){
            if(!choosen[i]){ //선택을 안한 경우
                choosen[i] = true;
             	int min = dungeons[i][0];   
             	int cost = dungeons[i][1];   
                if(now >= min){
                    dfs(now - cost, answer+1, dungeons, choosen, depth+1);
                } else {
                    dfs(now, answer, dungeons, choosen, depth+1);
                }
                choosen[i] = false;
            }
        }
    }
}