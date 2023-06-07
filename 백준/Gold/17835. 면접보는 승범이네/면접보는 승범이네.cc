#include "bits/stdc++.h"

using namespace std;
using ll = long long;

const ll INF = 1e18;

int N, M, K;
ll dist[100001];
vector<pair<ll, int>> graph[100001];

void dijkstra(vector<ll> starts) {
    fill(dist, dist + N + 1, INF);
    priority_queue<pair<ll, int>,
            vector<pair<ll, int>>,
            greater<pair<ll, int>>> pq;
    for (int each: starts) {
        pq.push({0, each});
        dist[each] = 0;
    }

    while (!pq.empty()) {
        auto node = pq.top();
        pq.pop();

        if (dist[node.second] < node.first) continue;

        for (auto next: graph[node.second]) {
            if (dist[next.second] > dist[node.second] + next.first) {
                dist[next.second] = dist[node.second] + next.first;
                pq.push({dist[next.second], next.second});
            }
        }
    }
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N >> M >> K;
    for (int i = 0; i < M; ++i) {
        int from, to, weight;
        cin >> from >> to >> weight;
        graph[to].push_back({weight, from});
    }

    vector<ll> starts;
    for (int i = 0; i < K; ++i) {
        int tmp;
        cin >> tmp;
        starts.push_back(tmp);
    }

    dijkstra(starts);

    int node = 0;
    ll max = -1;
    for (int i = 1; i <= N; ++i) {
        if (dist[i] != INF && dist[i] > max) {
            max = dist[i];
            node = i;
        }
    }
    cout << node << "\n" << max;

    return 0;
}

