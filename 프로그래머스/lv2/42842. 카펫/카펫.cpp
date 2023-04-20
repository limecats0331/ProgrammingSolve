#include "bits/stdc++.h"

using namespace std;

vector<int> solution(int brown, int yellow) {
    int h,w;
   	for(int i=1;i<=(int)sqrt(yellow);++i) {
       	if(yellow % i == 0) {
            if( (2*(i + yellow/i) + 4) == brown ) {
                h = i+2;
                w = (yellow/i)+2;
            }
		}
	} 
    
    return vector<int>{w,h};
}