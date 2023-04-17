#include "bits/stdc++.h"

using namespace std;

bool solution(string s){
    if(s[0] == ')' || s[s.length() - 1] == '(') return false;

    vector<char> tmp;
    for(int i = 0; i< s.length(); ++i){
        if(!tmp.empty() && s[i] == ')' && tmp.back() == '('){
           	tmp.pop_back(); 
        } else {
        	tmp.push_back(s[i]);    
        }
    }
    return tmp.empty();
}