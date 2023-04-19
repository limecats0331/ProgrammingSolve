#include "bits/stdc++.h"

using namespace std;

int solution(int n) {
    int same = bitset<30>(n).count();
    while(true){
        int num = ++n;
        if(bitset<30>(num).count() == same){
            return num;
        }
    }
    
    return -1;
}