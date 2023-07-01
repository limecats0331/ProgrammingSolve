#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int dy[4] = {-1, 1, 0, 0};
int dx[4] = {0, 0, -1, 1};
int n, m;
vector<vector<int>> board;
vector<vector<bool>> toDelete;
vector<vector<bool>> externalAir;

void showBoard() {
    cout << "==================================\n";
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cout << board[i][j] << " ";
        }
        cout << "\n";
    }
}

void showExtraAir() {
    cout << "==================================Air\n";
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cout << externalAir[i][j] << " ";
        }
        cout << "\n";
    }
}

void findExternalAir() {
    externalAir = vector<vector<bool>>(n, vector<bool>(m, false));
    vector<vector<bool>> isVisit = vector<vector<bool>>(n, vector<bool>(m, false));
    queue<pair<int, int>> que;

    for (int i = 0; i < n; ++i) {
        if (board[i][0] == 0) {
            que.push({i, 0});
            isVisit[i][0] = true;
            externalAir[i][0] = true;
        }

        if (board[i][m - 1] == 0) {
            que.push({i, m - 1});
            isVisit[i][m - 1] = true;
            externalAir[i][m - 1] = true;
        }
    }

    for (int i = 0; i < m; ++i) {
        if (board[0][i] == 0) {
            que.push({0, i});
            isVisit[0][i] = true;
            externalAir[0][i] = true;
        }

        if (board[n - 1][i] == 0) {
            que.push({n - 1, i});
            isVisit[n - 1][i] = true;
            externalAir[n - 1][i] = true;
        }
    }

    while (!que.empty()) {
        auto now = que.front();
        que.pop();

        for (int i = 0; i < 4; ++i) {
            int ny = now.first + dy[i];
            int nx = now.second + dx[i];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
            if (board[ny][nx] == 1) continue;
            if (isVisit[ny][nx]) continue;

            que.push({ny, nx});
            isVisit[ny][nx] = true;
            externalAir[ny][nx] = true;
        }
    }
}

void determine() {
    toDelete = vector<vector<bool>>(n, vector<bool>(m, false));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (board[i][j] == 1) {
                int cnt = 0;
                for (int k = 0; k < 4; ++k) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                    if (board[ny][nx] == 0 && externalAir[ny][nx]) cnt++;
                }
                if (cnt > 1) {
                    toDelete[i][j] = true;
                }
            }
        }
    }
}

void deleteCheese() {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (toDelete[i][j]) board[i][j] = 0;
        }
    }
}

bool isAllDelete() {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (board[i][j] == 1) return false;
        }
    }
    return true;
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> n >> m;
    board = vector<vector<int>>(n, vector<int>(m, 0));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> board[i][j];
        }
    }

    int answer = 0;
    while (!isAllDelete()) {
        findExternalAir();
//        showExtraAir();
        determine();
        deleteCheese();
//        showBoard();
        answer++;
    }

    cout << answer;

    return 0;
}

