#include "bits/stdc++.h"

using namespace std;

vector<vector<int>> dp;

int solution(vector<vector<int>> triangle) {
    if(triangle.size() == 1){
        return triangle[0][0];
    }
    
    for(int i=0;i<triangle.size();++i){
        dp.push_back(vector<int>{});
        for(int j=0;j<i+1;j++){
           dp[i].push_back(0); 
        }
    }
    dp[0][0] = triangle[0][0];
    
   	for(int i=1;i<triangle.size();++i){
        for(int j=0;j<triangle[i].size();++j){
            int left = j-1;
            int right = j;
            
            if(left < 0 ) {
                dp[i][j] = triangle[i][j] + dp[i-1][right]; 
            }
            else if(right >= triangle[i-1].size() ) {
                dp[i][j] = triangle[i][j] + dp[i-1][left]; 
            }
            else {
                int max = dp[i-1][left] < dp[i-1][right] ? dp[i-1][right] : dp[i-1][left];
                dp[i][j] = triangle[i][j] + max;
           	}
        }
    }

    return *max_element(dp[dp.size()-1].begin(),dp[dp.size()-1].end());
}