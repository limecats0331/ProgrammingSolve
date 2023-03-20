#include <string>
#include <vector>
#include "bits/stdc++.h"

using namespace std;

double solution(vector<int> arr) {
    double answer = 0;
    double sum = 0;
    for(auto num : arr){
       sum += num; 
    }
    return sum/arr.size();
}