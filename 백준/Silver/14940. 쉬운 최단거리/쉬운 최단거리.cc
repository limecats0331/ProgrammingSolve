#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};

int n, m;
vector<vector<int>> board;
vector<vector<int>> answer;

void bfs(pair<int, int> start) {
    queue<pair<int, int>> que;
    que.push(start);

    while (!que.empty()) {
        auto now = que.front();
        que.pop();

        for (int i = 0; i < 4; ++i) {
            int ny = now.first + dy[i];
            int nx = now.second + dx[i];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
            if (board[ny][nx] == 0) continue;
            if (answer[ny][nx] != 0) continue;

            que.push({ny, nx});
            answer[ny][nx] = answer[now.first][now.second] + 1;
        }
    }

    answer[start.first][start.second] = 0;
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> n >> m;
    board = vector<vector<int>>(n, vector<int>(m, 0));
    answer = vector<vector<int>>(n, vector<int>(m, 0));

    pair<int, int> start;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> board[i][j];
            if (board[i][j] == 2) start = {i, j};
        }
    }

    bfs(start);

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (board[i][j] == 2){
                cout << "0 ";
            }else if (board[i][j] != 0 && answer[i][j] == 0) {
                cout << "-1 ";
            } else {
                cout << answer[i][j] << " ";
            }
        }
        cout << "\n";
    }


    return 0;
}

