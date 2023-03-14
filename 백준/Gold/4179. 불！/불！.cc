#include "bits/stdc++.h"

using namespace std;

struct cur {
    int Y;
    int X;
    int cnt;
};

int N, M;
int dy[4] = {-1, 1, 0, 0};
int dx[4] = {0, 0, -1, 1};
char board[1002][1002];
deque<cur> Q;

int bfs() {
    while (!Q.empty()) {
        auto now = Q.front();
        Q.pop_front();
        char def_now = board[now.Y][now.X];
//        cout << "now: " << board[now.Y][now.X] << ":" << now.Y << " " << now.X << ":" << now.cnt << "\n";

        for (int i = 0; i < 4; ++i) {
            int ny = now.Y + dy[i];
            int nx = now.X + dx[i];

            if (def_now == 'J') {
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) return now.cnt + 1;
                if (board[ny][nx] == '.') {
                    Q.push_back({ny, nx, now.cnt + 1});
                    board[ny][nx] = 'J';
                }
            } else if (def_now == 'F') {
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (board[ny][nx] == '.' || board[ny][nx] == 'J') {
                    Q.push_back({ny, nx, 0});
                    board[ny][nx] = 'F';
                }
            }
        }
    }

    return -1;
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < N; ++i) {
        string input;
        cin >> input;
        for (int j = 0; j < M; ++j) {
            board[i][j] = input[j];
            if (board[i][j] == 'J') {
                Q.push_front({i, j, 0});
            }
            if (board[i][j] == 'F') {
                Q.push_back({i, j, 0});
            }
        }
    }

    int result = bfs();
    if(result == -1){
        cout << "IMPOSSIBLE";
        return 0;
    }

    cout << result;
    return 0;
}