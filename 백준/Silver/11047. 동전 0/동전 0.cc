#include "bits/stdc++.h"

using namespace std;

int N, K;
int coins[11];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> K;
    for (int i = 0; i < N; ++i) {
        cin >> coins[i];
    }

    int remain = K;
    int cnt = 0;
    while (remain > 0) {
        if (remain >= coins[N - 1]) {
            remain -= coins[N - 1];
            cnt++;
        } else {
            N--;
        }
    }

    cout << cnt;

    return 0;
}
