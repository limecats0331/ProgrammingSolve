#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int n, k, w;

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    int T;
    cin >> T;
    for (int t = 0; t < T; ++t) {
        cin >> n >> k;
        vector<int> cost = vector<int>(n + 1);
        for (int i = 1; i <= n; ++i) {
            cin >> cost[i];
        }

        vector<int> graph[1001];
        vector<int> cnt = vector<int>(n + 1);
        for (int i = 0; i < k; ++i) {
            int from, to;
            cin >> from >> to;
            cnt[to]++;
            graph[from].push_back(to);
        }
        cin >> w;

        queue<pair<int, int>> que;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] == 0) que.push({i, cost[i]});
        }

        vector<int> req_time = vector<int>(n + 1);
        while (!que.empty()) {
            auto now = que.front();
            que.pop();
            if (now.first == w) break;

            for (int each: graph[now.first]) {
                cnt[each]--;
                req_time[each] = max(now.second, req_time[each]);
                if (cnt[each] == 0) {
                    que.push({each, req_time[each] + cost[each]});
                }
            }
        }

        cout << req_time[w] + cost[w] << "\n";
    }

    return 0;
}

