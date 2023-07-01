#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

ll max_num = 100000000;
ll A, B;
set<ll> isVisit;

ll bfs() {
    queue<pair<ll, ll>> que;
    que.push({A, 1});

    while (!que.empty()) {
        auto now = que.front();
        que.pop();
        isVisit.insert(A);

        if (now.first == B) {
            return now.second;
        }

        if (now.first * 2 > B) continue;
        if (isVisit.find(now.first * 2) != isVisit.end()) continue;
        que.push({now.first * 2, now.second + 1});
        isVisit.insert(now.first * 2);

        if (now.first * 10 + 1 > B) continue;
        if (isVisit.find(now.first * 10 + 1) != isVisit.end()) continue;
        que.push({now.first * 10 + 1, now.second + 1});
        isVisit.insert(now.first * 10 + 1);
    }

    return -1;
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> A >> B;
    cout << bfs();

    return 0;
}

