#include "bits/stdc++.h"

using namespace std;

int N;
int dp[12];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    for (int i = 4; i < 12; ++i) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    }

    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        cout << dp[tmp] << "\n";
    }

    return 0;
}
