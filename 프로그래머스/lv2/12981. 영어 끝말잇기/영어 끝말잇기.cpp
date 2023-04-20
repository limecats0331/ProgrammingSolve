#include "bits/stdc++.h"

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> answer;
	set<string> word_set;
   	
    int end = -1;
    word_set.insert(words[0]);
    for(int i=1;i<words.size();i++){
        if(words[i].front() != words[i-1].back() ){
           	end = i; 
            break;
        }
        if( !word_set.insert(words[i]).second ){
            end = i;
            break;
        }
    }
    
    cout << end+1 << "\n";
    if(end == -1) answer = {0,0};
    else answer = { ((end) % n) + 1, ((end) / n) + 1 };
    return answer;
}