#include "bits/stdc++.h"

using namespace std;

int N, M;
int board[1001][1001];
bool is_visit[1001];

void bfs(int start) {
    queue<int> que;
    que.push(start);
    is_visit[start] = true;

    while (!que.empty()) {
        int now = que.front();
        que.pop();

        for (int i = 1; i <= N; ++i) {
            if (board[now][i] == 1 && !is_visit[i]) {
                que.push(i);
                is_visit[i] = true;
            }
        }
    }
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M;
    for (int i = 0; i < M; ++i) {
        int n1, n2;
        cin >> n1 >> n2;
        board[n1][n2] = 1;
        board[n2][n1] = 1;
    }

    int answer = 0;
    for (int i = 1; i <= N; ++i) {
        if (!is_visit[i]) {
            bfs(i);
            answer++;
        }
    }

    cout << answer;

    return 0;
}
