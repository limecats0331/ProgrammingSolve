#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int N;
priority_queue<int, vector<int>, greater<int>> pq;

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;

        if (tmp == 0) {
            if (pq.empty()) {
                cout << "0\n";
            } else {
                cout << pq.top() << "\n";
                pq.pop();
            }
        } else {
            pq.push(tmp);
        }
    }


    return 0;
}

