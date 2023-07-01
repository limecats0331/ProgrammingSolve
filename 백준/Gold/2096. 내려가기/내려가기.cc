#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int board[3];
int dp_max[3];
int dp_min[3];

int max_three(int n1, int n2, int n3) {
    return max(n1, max(n2, n3));
}

int min_three(int n1, int n2, int n3) {
    return min(n1, min(n2, n3));
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    int n;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 3; ++j) {
            cin >> board[j];
            if (i == 0) {
                dp_max[j] = board[j];
                dp_min[j] = board[j];
            }
        }

        if (i > 0) {
            int tmp_dp_max[3];
            copy(dp_max, dp_max + 3, tmp_dp_max);
            int tmp_dp_min[3];
            copy(dp_min, dp_min + 3, tmp_dp_min);

            dp_max[0] = max(tmp_dp_max[0] + board[0], tmp_dp_max[1] + board[0]);
            dp_max[1] = max_three(tmp_dp_max[0] + board[1], tmp_dp_max[1] + board[1],
                                  tmp_dp_max[2] + board[1]);
            dp_max[2] = max(tmp_dp_max[1] + board[2], tmp_dp_max[2] + board[2]);

            dp_min[0] = min(tmp_dp_min[0] + board[0], tmp_dp_min[1] + board[0]);
            dp_min[1] = min_three(tmp_dp_min[0] + board[1], tmp_dp_min[1] + board[1],
                                  tmp_dp_min[2] + board[1]);
            dp_min[2] = min(tmp_dp_min[1] + board[2], tmp_dp_min[2] + board[2]);
        }
    }

    cout << max_three(dp_max[0], dp_max[1], dp_max[2]) << " "
         << min_three(dp_min[0], dp_min[1], dp_min[2]);

    return 0;
}

