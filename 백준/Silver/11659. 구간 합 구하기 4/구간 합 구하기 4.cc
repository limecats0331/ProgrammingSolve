#include "bits/stdc++.h"

using namespace std;

int N, M;
int nums[100001];
int dp[100001];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    dp[0] = 0;
    cin >> N >> M;
    for (int i = 1; i <= N; ++i) {
        cin >> nums[i];
        dp[i] = nums[i] + dp[i - 1];
    }

    for (int i = 0; i < M; ++i) {
        int st, en;
        cin >> st >> en;
        cout << dp[en] - dp[st-1] << "\n";
    }

    return 0;
}
