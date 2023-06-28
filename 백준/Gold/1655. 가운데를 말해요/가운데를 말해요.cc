#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int N;
priority_queue<int> max_heap;
priority_queue<int, vector<int>, greater<int>> min_heap;

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;

        if (max_heap.size() == min_heap.size()) {
            max_heap.push(tmp);
        } else {
            min_heap.push(tmp);
        }

        if(max_heap.size() !=0 && min_heap.size() !=0 && max_heap.top() > min_heap.top()){
            int max = max_heap.top();max_heap.pop();
            int min = min_heap.top();min_heap.pop();

            max_heap.push(min);
            min_heap.push(max);
        }

        cout << max_heap.top() << "\n";
    }

    return 0;
}

