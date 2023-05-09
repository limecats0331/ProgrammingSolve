#include "bits/stdc++.h"

using namespace std;

int v, e;
int dist[401][401];
const int INF = 99999999;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    fill(&dist[0][0], &dist[0][0] + 401 * 401, INF);
    cin >> v >> e;
    for (int i = 0; i < e; ++i) {
        int from, to, cost;
        cin >> from >> to >> cost;
        dist[from][to] = cost;
    }

    for (int k = 1; k <= v; ++k) {
        for (int i = 1; i <= v; ++i) {
            for (int j = 1; j <= v; ++j) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
            }
        }
    }

    int answer = INF;
    for (int i = 1; i <= v; ++i) {
        answer = min(answer, dist[i][i]);
    }

    if (answer == INF) answer = -1;
    cout << answer;

    return 0;
}
