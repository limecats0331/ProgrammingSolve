#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int n, m;
int isSelect[100001];
int root[100001];
priority_queue<tuple<int, int, int>,
        vector<tuple<int, int, int>>,
        greater<tuple<int, int, int>>> pq;

int find_root(int num) {
    if (root[num] == num) {
        return num;
    }
    return root[num] = find_root(root[num]);
}

bool union_set(int n1, int n2) {
    n1 = find_root(n1);
    n2 = find_root(n2);
    if (n1 == n2) {
        return false;
    }

    root[n2] = n1;
    return true;
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int h1, h2, cost;
        cin >> h1 >> h2 >> cost;

        pq.push({cost, h1, h2});
    }

    for (int i = 1; i <= n; ++i) {
        root[i] = i;
    }

    int cnt = 0;
    int answer = 0;
    while (!pq.empty()) {
        auto now = pq.top();
        pq.pop();

        if (isSelect[get<1>(now)] || isSelect[get<2>(now)]) continue;
        if (cnt == n - 2) break;

        if (union_set(get<1>(now), get<2>(now))) {
//        cout << "{" << get<1>(now) << ", " << get<2>(now) << "} = " << get<0>(now) << "\n";
            cnt++;
            answer += get<0>(now);
        }

    }

    cout << answer;

    return 0;
}

