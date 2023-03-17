#include <string>
#include <vector>
#include "bits/stdc++.h"

using namespace std;

int solution(vector<int> common) {
    cin.tie(0);ios::sync_with_stdio(false);
    int answer = 0;
    
    auto start = common.begin();
    int first = *start;
    start++;
    int second = *start;
    start++;
    int third = *start;
    
    //등차수열이면 
    if(second - first == third - second){
        answer = common.back() + (second - first);
    } else {
        answer = common.back() * (second/first);    
    }
    
    return answer;
}