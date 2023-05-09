#include "bits/stdc++.h"

using namespace std;

int n, m;
int dist[101][101];
int nxt[101][101];
int INF = 9999999;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    fill(&dist[0][0], &dist[0][0] + 10201, INF);

    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int from, to, cost;
        cin >> from >> to >> cost;
        if (cost < dist[from][to]) {
            dist[from][to] = cost;
            nxt[from][to] = to;
        }
    }

    for (int k = 1; k <= n; ++k) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (dist[i][j] > dist[i][k] + dist[k][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                    nxt[i][j] = nxt[i][k];
                }
            }
        }
    }

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (dist[i][j] == INF || i == j) dist[i][j] = 0;
            cout << dist[i][j] << " ";
        }
        cout << "\n";
    }

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (dist[i][j] == 0) {
                cout << "0\n";
                continue;
            }
            vector<int> path;
            int next = i;
            while (next != j) {
                path.push_back(next);
                next = nxt[next][j];
            }
            path.push_back(j);
            cout << path.size() << " ";
            for (auto each: path) cout << each << " ";
            cout << "\n";
        }
    }
    return 0;
}
