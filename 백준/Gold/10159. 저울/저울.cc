#include "bits/stdc++.h"

using namespace std;

int n, m;
int dist[101][101];
int dist2[101][101];
const int INF = 99999999;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    fill(&dist[0][0], &dist[0][0] + 101 * 101, INF);
    fill(&dist2[0][0], &dist2[0][0] + 101 * 101, INF);

    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int n1, n2;
        cin >> n1 >> n2;
        dist[n1][n2] = 1;
        dist2[n2][n1] = 1;
    }

    for (int i = 1; i <= n; ++i) {
        dist[i][i] = 0;
        dist2[i][i] = 0;
    }

    for (int k = 1; k <= n; ++k) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
                if (dist2[i][k] + dist2[k][j] < dist2[i][j]) {
                    dist2[i][j] = dist2[i][k] + dist2[k][j];
                }
            }
        }
    }

    for (int i = 1; i <= n; ++i) {
        int out = 0;
        for (int j = 1; j <= n; ++j) {
            if(dist[i][j] == INF && dist2[i][j] == INF){
                out++;
            }
        }
        cout << out << "\n";
    }

    return 0;
}
