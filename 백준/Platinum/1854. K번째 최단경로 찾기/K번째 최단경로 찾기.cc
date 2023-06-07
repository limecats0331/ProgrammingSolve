#include "bits/stdc++.h"

using namespace std;
using ll = long long;

const ll INF = 1e18;

int n, m, k;
ll dist[1001];
vector<pair<ll, int>> graph[1001];
priority_queue<ll> k_th[1001];

void dijkstra() {
    fill(dist, dist + n + 1, INF);
    priority_queue<pair<ll, int>,
            vector<pair<ll, int>>,
            greater<pair<ll, int>>> pq;
    pq.push({0, 1});
    k_th[1].push(0);

    while (!pq.empty()) {
        auto node = pq.top();
        pq.pop();

        for (auto next: graph[node.second]) {
            if (k_th[next.second].size() < k) {
                k_th[next.second].push(node.first + next.first);
                pq.push({node.first + next.first, next.second});
            } else if (k_th[next.second].top() > node.first + next.first) {
                k_th[next.second].pop();
                k_th[next.second].push(node.first + next.first);
                pq.push({node.first + next.first, next.second});
            }
        }
    }
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> n >> m >> k;
    for (int i = 0; i < m; ++i) {
        int from, to, weight;
        cin >> from >> to >> weight;
        graph[from].push_back({weight, to});
    }

    dijkstra();

    for (int i = 1; i <= n; ++i) {
        if (k_th[i].size() != k) {
            cout << "-1\n";
        } else {
            cout << k_th[i].top() << "\n";
        }
    }

    return 0;
}

