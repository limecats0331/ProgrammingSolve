#include <string>
#include <iostream>
using namespace std;

bool solution(string s)
{
    bool answer = true;

    int p_cnt = 0;
    int y_cnt = 0;
    for(char c : s){
       	if(tolower(c) == 'p') p_cnt++; 
       	if(tolower(c) == 'y') y_cnt++; 
    }

    return p_cnt == y_cnt;
}