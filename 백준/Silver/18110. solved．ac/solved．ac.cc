#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int N;
vector<int> numbers;

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;

        numbers.push_back(tmp);
    }
    
    if(N == 0){
        cout << 0;
        return 0;
    }

    sort(numbers.begin(), numbers.end());

    int cut_size = round(numbers.size() * 0.15);
    float answer = 0;
    for (int i = cut_size; i < numbers.size() - cut_size; ++i) {
        answer += numbers[i];
    }

    cout << round(answer / (numbers.size() - cut_size*2));

    return 0;
}

