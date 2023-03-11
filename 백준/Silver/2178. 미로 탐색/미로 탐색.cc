#include "bits/stdc++.h"

using namespace std;

int N, M;
int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};

int bfs(string map[]) {
    int dist[N][M];
    for (int i = 0; i < N; ++i) {
        fill(dist[i], dist[i] + M, -1);
    }

    queue<vector<int>> Q;
    Q.push(vector<int>{0, 0});
    dist[0][0] = 1;

    while (!Q.empty()) {
        vector<int> now = Q.front();
        Q.pop();

        for (int i = 0; i < 4; ++i) {
            int ny = now[0] + dy[i];
            int nx = now[1] + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            if (map[ny][nx] == '1' && dist[ny][nx] == -1) {
                Q.push(vector<int>{ny, nx});
                dist[ny][nx] = dist[now[0]][now[1]] + 1;
            }
        }
    }

    return dist[N-1][M-1];
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    cin >> N >> M;

    string map[N];


    for (int i = 0; i < N; ++i) {
        cin >> map[i];
    }

    cout << bfs(map);

    return 0;
}