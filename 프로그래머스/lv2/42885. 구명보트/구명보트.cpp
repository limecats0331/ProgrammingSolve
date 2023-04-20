#include "bits/stdc++.h"

using namespace std;

int solution(vector<int> people, int limit) {
    int answer = 0;
    sort(people.begin(), people.end());
   
    int st=0;
    int en=people.size() - 1;
    
    while(!people.empty()){
        //cout << "st = " << st << " en = " << en << "\n";
        if(st > en){
            break;
        } else if (st == en) {
        	st++;
            answer++;
        }else if(people[st] + people[en] <= limit){
            st++;
            en--;
            answer++;
        } else {
            en--;
            answer++;
        }
    }
    
    return answer;
}