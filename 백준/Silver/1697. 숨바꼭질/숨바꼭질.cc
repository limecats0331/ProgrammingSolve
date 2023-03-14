#include "bits/stdc++.h"

using namespace std;

int N, K;
bool is_visit[100002];

int bfs() {
    queue<pair<int, int>> Q;
    Q.push({N, 0});
    is_visit[N] = true;

    while (!Q.empty()) {
        auto now = Q.front();
        Q.pop();

        if (now.first == K) {
            return now.second;
        }

        int d[3] = {-1, 1, now.first};
        for (int i = 0; i < 3; ++i) {
            int next = now.first + d[i];

            if (next < 0 || next > 100000) continue;
            if (!is_visit[next]) {
                Q.push({next, now.second + 1});
                is_visit[next] = true;
            }
        }
    }
    return -1;
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    cin >> N >> K;

    cout << bfs();

    return 0;
}