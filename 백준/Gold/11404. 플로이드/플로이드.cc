#include "bits/stdc++.h"

using namespace std;

int n, m;
int dist[101][101];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    fill(&dist[0][0], &dist[0][0] + 10201, 9999999);

    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int from, to, cost;
        cin >> from >> to >> cost;
        dist[from][to] = min(cost, dist[from][to]);
    }

    for (int k = 1; k <= n; ++k) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }


    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (dist[i][j] == 9999999 || i == j) dist[i][j] = 0;
            cout << dist[i][j] << " ";
        }
        cout << "\n";
    }
    return 0;
}
