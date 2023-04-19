#include "bits/stdc++.h"

using namespace std;

int solution(int n) {
    int answer = 0;
    
    for(int i =1;i<=n;++i){
        int top = n-( ((i-1)*i)/2 );
        if(top > 0 && (top%i)==0) {
            cout << "i : " << i << "\n";
            answer ++;
        }
    }
    
    return answer;
}