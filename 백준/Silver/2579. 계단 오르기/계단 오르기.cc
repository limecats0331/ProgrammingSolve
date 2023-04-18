#include "bits/stdc++.h"

using namespace std;

int N;
int step[301];
int dp[301];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        cin >> step[i];
    }

    dp[0] = step[0];
    dp[1] = max(dp[0] + step[1], step[1]);
    dp[2] = max(step[2] + step[1], step[2] + dp[0]);
    for (int i = 3; i < N; ++i) {
        dp[i] = max(step[i] + step[i - 1] + dp[i - 3], step[i] + dp[i - 2]);
    }

    cout << dp[N - 1];

    return 0;
}
