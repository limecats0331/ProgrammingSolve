#include <string>
#include <vector>
#include "bits/stdc++.h"

#define ll long long

using namespace std;

long long solution(long long n) {
    long long answer = 0;
    
    ll sq = sqrt(n);
    if( n == pow(sq,2)){
        return pow(sq+1,2);
    } else {
        return -1;
    }
}