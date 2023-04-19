#include "bits/stdc++.h"

using namespace std;

int solution(string s) {
    vector<char> que;
    
    for(int i=0;i<s.length();++i){
        if(que.size() == 0 || que.back() != s[i]){
            que.push_back(s[i]);
        } else {
            que.pop_back();
        }
    }

    return que.size() == 0 ? 1 : 0;
}