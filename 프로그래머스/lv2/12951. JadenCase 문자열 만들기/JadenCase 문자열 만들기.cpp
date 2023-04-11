#include "bits/stdc++.h"

using namespace std;

string solution(string s) {
    string answer = "";
    
	for(int i = s.length();i > 0; i--){
       	if(s[i-1] == ' '){
            s[i] = toupper(s[i]);
        } else {
            s[i] = tolower(s[i]);
        } 
    }
    s[0] = toupper(s[0]);
    
    return s;
}