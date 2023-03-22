#include <string>
#include <vector>
#include "bits/stdc++.h"

using namespace std;

vector<int> solution(vector<string> keymap, vector<string> targets) {
    vector<int> answer;
    int alpabet[26];
    fill_n(alpabet,26,INT_MAX);
    
    for(string s : keymap){
        for(int i = 0;i<s.size();++i){
            char c = s[i];
            alpabet[c-'A'] = min(alpabet[c-'A'],i+1);
        }
    }
    
    for(string s : targets){
        int sum = 0;
        for(char c : s){
           	int cnt = alpabet[c-'A'];
            if(cnt == INT_MAX){
                sum = -1;
                break;
            } else {
               sum += cnt; 
            }
        }
        answer.push_back(sum);
    }
    
    return answer;
}