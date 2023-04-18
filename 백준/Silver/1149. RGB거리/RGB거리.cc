#include "bits/stdc++.h"

using namespace std;

int N;
int house[1001][3];
int dp[1001][3];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < 3; ++j) {
            cin >> house[i][j];
        }
    }

    dp[0][0] = house[0][0];
    dp[0][1] = house[0][1];
    dp[0][2] = house[0][2];
    for (int i = 1; i < N; ++i) {
        dp[i][0] = house[i][0] + min(dp[i - 1][1], dp[i - 1][2]);
        dp[i][1] = house[i][1] + min(dp[i - 1][0], dp[i - 1][2]);
        dp[i][2] = house[i][2] + min(dp[i - 1][0], dp[i - 1][1]);
    }

    int result = *min_element(dp[N - 1], dp[N - 1] + 3);
    cout << result;

    return 0;
}
