#include "bits/stdc++.h"

using namespace std;

int N, M;
int dy[4] = {-1, 1, 0, 0};
int dx[4] = {0, 0, -1, 1};
int board[1002][1002];
bool isVisit[1002][1002];
queue<pair<int, int>> Q;

void bfs() {
    while (!Q.empty()) {
        auto now = Q.front();
        Q.pop();

        for (int i = 0; i < 4; ++i) {
            int ny = now.first + dy[i];
            int nx = now.second + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            if (board[ny][nx] == 0 && !isVisit[ny][nx]) {
                Q.push({ny, nx});
                board[ny][nx] = board[now.first][now.second] + 1;
                isVisit[ny][nx] = true;
            }
        }
    }
}

int get_result() {
    int result = 0;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if (board[i][j] == 0) {
                return -1;
            }
            result = max(result, board[i][j]);
        }
    }
    return result - 1;
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    cin >> M >> N;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            cin >> board[i][j];
            if (board[i][j] == 1) {
                Q.push({i, j});
                isVisit[i][j] = true;
            }
        }
    }

    bfs();
    cout << get_result();
    return 0;
}