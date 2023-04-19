#include "bits/stdc++.h"

using namespace std;

int N;
int dp[1000001] = {0};
int point[1000001] = {0};


int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    dp[1] = 0;
    point[1] = 0;

    for (int i = 2; i <= N; ++i) {
        dp[i] = dp[i - 1] + 1;
        point[i] = i - 1;
        if (i % 2 == 0) {
            if (dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                point[i] = i / 2;
            }
        }
        if (i % 3 == 0) {
            if (dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                point[i] = i / 3;
            }
        }
    }

    cout << dp[N] << "\n";
    int pointer = N;
    while(true){
        cout << pointer << " ";
        pointer = point[pointer];
        if(pointer == 0){
            break;
        }
    }

    return 0;
}
