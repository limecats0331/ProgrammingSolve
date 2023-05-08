#include "bits/stdc++.h"

using namespace std;

int R, C;
int board[251][251];
bool is_visit[251][251];
int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};
int sheep = 0;
int wolf = 0;

void bfs(int y, int x) {
    queue<pair<int, int>> que;
    que.push({y, x});

    int sheep_tmp = 0;
    int wolf_tmp = 0;
    if (board[y][x] == 'v') wolf_tmp++;
    if (board[y][x] == 'o') sheep_tmp++;
    is_visit[y][x] = true;

    while (!que.empty()) {
        pair<int, int> now = que.front();
        que.pop();

        for (int i = 0; i < 4; ++i) {
            int ny = now.first + dy[i];
            int nx = now.second + dx[i];

            if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
            if (board[ny][nx] != '#' && !is_visit[ny][nx]) {
                if (board[ny][nx] == 'v') wolf_tmp++;
                if (board[ny][nx] == 'o') sheep_tmp++;
                que.push({ny, nx});
                is_visit[ny][nx] = true;
            }
        }
    }

    if (sheep_tmp > wolf_tmp) sheep += sheep_tmp;
    else wolf += wolf_tmp;
}


int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> R >> C;
    for (int i = 0; i < R; ++i) {
        string input;
        cin >> input;
        for (int j = 0; j < C; ++j) {
            board[i][j] = input[j];
        }
    }

    for (int i = 0; i < R; ++i) {
        for (int j = 0; j < C; ++j) {
            if (board[i][j] != '#' && !is_visit[i][j]) {
                bfs(i, j);
            }
        }
    }

    cout << sheep << " " << wolf;

    return 0;
}
