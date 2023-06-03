#include "bits/stdc++.h"

using namespace std;

const int MAX = 999999999;

int V, E;
int dist[200001];
vector<pair<int, int>> graph[200001];

void dijkstra(int start) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, start});

    dist[start] = 0;
    while (!pq.empty()) {
        auto now = pq.top();
        pq.pop();

        if (dist[now.second] != now.first) continue;

        for (auto node: graph[now.second]) {
            if (dist[node.second] <= dist[now.second] + node.first) continue;
            dist[node.second] = dist[now.second] + node.first;
            pq.push({dist[node.second], node.second});
        }
    }
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    int start;
    cin >> V >> E >> start;
    for (int i = 0; i < E; ++i) {
        int from, to, weight;
        cin >> from >> to >> weight;
        graph[from].push_back({weight, to});
    }

    fill(dist, dist + V + 1, MAX);
    dijkstra(start);
    for (int i = 1; i <= V; ++i) {
        int each = dist[i];
        if (each == MAX) {
            cout << "INF\n";
        } else {
            cout << each << "\n";
        }
    }

    return 0;
}

