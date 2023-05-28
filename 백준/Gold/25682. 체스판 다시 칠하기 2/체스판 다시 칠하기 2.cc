#include "bits/stdc++.h"

using namespace std;

int N, M, K;
vector<string> board;
int start_b[2001][2001];
int start_w[2001][2001];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N >> M >> K;
    for (int i = 0; i < N; ++i) {
        string input;
        cin >> input;
        board.push_back(input);
    }

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M; ++j) {
            start_b[i][j] = start_b[i - 1][j] + start_b[i][j - 1] - start_b[i - 1][j - 1];
            start_w[i][j] = start_w[i - 1][j] + start_w[i][j - 1] - start_w[i - 1][j - 1];
            if ((i + j) % 2 == 0) { //시작과 같은 색이여야함
                if (board[i - 1][j - 1] == 'B') {
                    start_w[i][j]++;
                } else {
                    start_b[i][j]++;
                }
            } else { //시작이랑은 다른 색이여야함
                if (board[i - 1][j - 1] == 'B') {
                    start_b[i][j]++;
                } else {
                    start_w[i][j]++;
                }
            }
        }
    }

    int answer = INT32_MAX;
    for (int i = 0; i <= N - K; ++i) {
        for (int j = 0; j <= M - K; ++j) {
            int b_num = start_b[i + K][j + K] - start_b[i][j + K] - start_b[i + K][j] + start_b[i][j];
            int w_num = start_w[i + K][j + K] - start_w[i][j + K] - start_w[i + K][j] + start_w[i][j];
            answer = min(answer, min(b_num, w_num));
        }
    }

    cout << answer;

    return 0;
}

