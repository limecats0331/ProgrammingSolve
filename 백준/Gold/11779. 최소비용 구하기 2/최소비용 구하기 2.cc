#include "bits/stdc++.h"

using namespace std;

const int INF = 999999999;
int n, m;
int dist[1001];
int prev_node[1001];
//weight, to
vector<pair<int, int>> graph[1001];

int dijkstra(int start, int end) {
    fill(dist, dist + n + 1, INF);
    dist[start] = 0;
    priority_queue<pair<int, int>,
            vector<pair<int, int>>,
            greater<pair<int, int>>> pq;
    pq.push({0, start});

    while (!pq.empty()) {
        auto node = pq.top();
        pq.pop();

        if (dist[node.second] != node.first) continue;

        for (auto next: graph[node.second]) {
            if (dist[next.second] > dist[node.second] + next.first) {
                dist[next.second] = dist[node.second] + next.first;
                prev_node[next.second] = node.second;
                pq.push({dist[next.second], next.second});
            }
        }
    }

    return dist[end];
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int from, to, weight;
        cin >> from >> to >> weight;

        graph[from].push_back({weight, to});
    }
    for (int i = 1; i <= n; ++i) {
        prev_node[i] = i;
    }

    int start, end;
    cin >> start >> end;

    cout << dijkstra(start, end) << "\n";

    vector<int> answer;
    while (true) {
        answer.push_back(end);
        if (end == start) break;
        end = prev_node[end];
    }

    cout << answer.size() << "\n";
    for (int i = answer.size() - 1; i >= 0; --i) {
        cout << answer[i] << " ";
    }

    return 0;
}

