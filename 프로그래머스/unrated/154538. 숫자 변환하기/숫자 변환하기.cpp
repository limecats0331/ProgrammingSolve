#include "bits/stdc++.h"

using namespace std;
bool is_visit[1000001];

int bfs(int start, int end, int n){
    queue<pair<int,int>> que;
    que.push({start,0});
   	is_visit[start] = true;
    
    while(!que.empty()){
        auto now = que.front();que.pop();
        if(now.first == end	){
            return now.second;
        }
        
       	if(now.first + n <= 1000000 && !is_visit[now.first+n]){
			que.push({now.first+n, now.second + 1});
            is_visit[now.first+n] = true;
        }
        if(now.first * 2  <= 1000000 && !is_visit[now.first*2]){
			que.push({now.first*2, now.second + 1});
            is_visit[now.first*2] = true;
        }
        if(now.first * 3 <= 1000000 && !is_visit[now.first*3]){
			que.push({now.first*3, now.second + 1});
            is_visit[now.first*3] = true;
        }
    }
    return -1;
}

int solution(int x, int y, int n) {
    return bfs(x,y,n);
}