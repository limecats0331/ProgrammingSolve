#include "bits/stdc++.h"

using namespace std;

int change_cnt = 0;
int zero_cnt = 0;

string length_to_binary(string s){
   	if(s == "1"){
        return "1";
    }
    int len= 0;
    for(int i = 0;i<s.length();++i){
        if(s[i] == '1') {
            len++;
        } else {
            zero_cnt ++;
        }
    }
    
    string next = bitset<36>(len).to_string();
    
    int bin_len = (int)log2(len)+1;
    next = next.substr(36 - bin_len, bin_len+1);
    change_cnt++;
    
    return next;
}

vector<int> solution(string s) {
    while(s != "1"){
        s = length_to_binary(s);
    };
    
    return vector<int>{change_cnt, zero_cnt};
}