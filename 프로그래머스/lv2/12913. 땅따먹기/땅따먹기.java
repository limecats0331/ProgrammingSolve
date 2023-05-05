import java.util.*;

class Solution { 
   	int[][] dp = new int[100001][4];
    
    int findDpMax(int h, int w){
        int answer = -1;
        for(int i=0;i<4;i++){
            if(i==w) continue;
           	answer = Math.max(answer, dp[h][i]);
        }
        return answer;
    }
    
    int solution(int[][] land) {
       	for(int i=0;i<4;i++){
            dp[0][i] = land[0][i];
        } 
        
       	for(int i=1;i<land.length;i++){
            for(int j=0;j<4;j++){
                dp[i][j] = land[i][j] + findDpMax(i-1,j);
            }
        }
        
        
        
        return findDpMax(land.length-1, -1);
    }
}