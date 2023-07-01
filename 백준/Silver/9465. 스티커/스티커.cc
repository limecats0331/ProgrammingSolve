#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int max_three(int n1, int n2, int n3) {
    return max(n1, max(n2, n3));
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    int T;
    cin >> T;
    for (int t = 0; t < T; ++t) {
        int n;
        cin >> n;

        vector<vector<int>> board = vector<vector<int>>(2, vector<int>(n, 0));
        vector<vector<int>> dp = vector<vector<int>>(3, vector<int>(n, 0));

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < n; ++j) {
                cin >> board[i][j];
            }
        }

        dp[0][0] = 0;
        dp[1][0] = board[0][0];
        dp[2][0] = board[1][0];

        for (int i = 1; i < n; ++i) {
            dp[0][i] = max_three(dp[0][i-1], dp[1][i - 1], dp[2][i - 1]);
            dp[1][i] = max(dp[0][i-1] + board[0][i], dp[2][i - 1] + board[0][i]);
            dp[2][i] = max(dp[0][i-1] + board[1][i], dp[1][i - 1] + board[1][i]);
        }

        cout << max_three(dp[0][n-1], dp[1][n-1], dp[2][n-1]) << "\n";

    }

    return 0;
}

