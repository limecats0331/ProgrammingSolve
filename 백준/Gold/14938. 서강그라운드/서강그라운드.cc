#include "bits/stdc++.h"

using namespace std;

int n, m, r;
int dist[101][101];
int nxt[101][101];
int cnt[101];
const int INF = 99999999;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    fill(&dist[0][0], &dist[0][0] + 101 * 101, INF);

    cin >> n >> m >> r;
    for (int i = 1; i <= n; ++i) {
        cin >> cnt[i];
    }

    for (int i = 0; i < r; ++i) {
        int from, to, cost;
        cin >> from >> to >> cost;
        dist[from][to] = cost;
        dist[to][from] = cost;
        nxt[from][to] = to;
        nxt[to][from] = from;
    }

    for (int k = 1; k <= n; ++k) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                    nxt[i][j] = nxt[i][k];
                }
            }
        }
    }

    for (int i = 1; i <= n; ++i) {
        dist[i][i] = 0;
    }

    int answer = 0;
    for (int i = 1; i <= n; ++i) {
        int sum = 0;
        for (int j = 1; j <= n; ++j) {
            if (dist[i][j] <= m) sum += cnt[j];
        }
        answer = max(answer, sum);
    }

    cout << answer;

    return 0;
}
