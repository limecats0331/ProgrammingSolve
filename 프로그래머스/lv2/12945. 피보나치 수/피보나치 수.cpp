#include "bits/stdc++.h"

using namespace std;

int fivo[100001];

int solution(int n) {
    fivo[0] = 0;
    fivo[1] = 1;
    for(int i = 2;i<=n;++i){
       	fivo[i] = fivo[i-1] + fivo[i-2]; 
        fivo[i] %= 1234567;
    }
    return fivo[n];
}