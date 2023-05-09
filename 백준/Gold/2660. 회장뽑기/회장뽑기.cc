#include "bits/stdc++.h"

using namespace std;

int n;
int dist[51][51];
const int INF = 99999999;
int point[51];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    fill(&dist[0][0], &dist[0][0] + 51 * 51, INF);
    cin >> n;
    int p1, p2;
    while (true) {
        cin >> p1 >> p2;
        if (p1 == -1 && p2 == -1) break;
        dist[p1][p2] = 1;
        dist[p2][p1] = 1;
    }

    for (int i = 1; i <= n; ++i) {
        dist[i][i] = 0;
    }

    for (int k = 1; k <= n; ++k) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    int ans = INF;
    for (int i = 1; i <= n; ++i) {
        int score = 0;
        for (int j = 1; j <= n; ++j) {
            if (dist[i][j] == 0) continue;
            score = max(score, dist[i][j]);
        }
        point[i] = score;
        ans = min(ans, score);
    }

    int cnt = 0;
    vector<int> person;
    for (int i = 1; i <= n; ++i) {
        if (point[i] == ans) {
            person.push_back(i);
            cnt++;
        }
    }

    cout << ans << " " << cnt << "\n";
    for (int each: person) {
        cout << each << " ";
    }


    return 0;
}
