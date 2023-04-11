#include "bits/stdc++.h"

using namespace std;

vector<string> split(string input, char delimiter) {
    vector<string> answer;
    stringstream ss(input);
    string temp;
 
    while (getline(ss, temp, delimiter)) {
        answer.push_back(temp);
    }
 
    return answer;
}

string solution(string s) {
    string answer = "";
    
    vector<string> sp = split(s,' ');
    vector<int> nums;
    for(int i =0;i<sp.size();i++){
       nums.push_back(stoi(sp[i]));
    }
    
    int max = *max_element(nums.begin(),nums.end());
    int min = *min_element(nums.begin(),nums.end());
    
    answer = to_string(min) + " "+ to_string(max);
    return answer;
}