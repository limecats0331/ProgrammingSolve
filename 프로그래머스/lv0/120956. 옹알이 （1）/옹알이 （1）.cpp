#include <string>
#include <vector>
#include "bits/stdc++.h"

using namespace std;
string words[] = {"aya","ye","woo","ma"};

bool can_say(string word){
    for(int i = 0;i<word.size();i++){
        char now = word[i];
        int cnt = 0;
        if(now == 'a') cnt = 0;
        else if(now == 'y') cnt = 1;
        else if(now == 'w') cnt = 2;
        else if(now == 'm') cnt = 3;
        else return false;
        
        for(int j = 0;j<words[cnt].size();j++){
            if(word[i+j] != words[cnt][j]){
                return false;
            }
        }
        i += words[cnt].size()-1;
    }
    return true;
}

int solution(vector<string> babbling) {
    int answer = 0;
    for(string w : babbling){
        if(can_say(w)) answer++;
    }
    return answer;
}